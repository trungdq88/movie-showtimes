/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.xml.hth.crawler.services;

import com.fpt.xml.hth.crawler.crawlentities.CrawlCinema;
import com.fpt.xml.hth.crawler.transformation.Transformation;
import com.fpt.xml.hth.crawler.utils.MarshalUtil;
import com.fpt.xml.hth.crawler.validation.ValidCinemaTrack;
import com.fpt.xml.hth.db.lib.DAO.CinemaDAO;
import com.fpt.xml.hth.db.lib.DAO.MovieDAO;
import com.fpt.xml.hth.db.lib.DTO.CinemaDTO;
import com.fpt.xml.hth.db.lib.DTO.MovieTheaterSessionDTO;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class CrawlerManager {

    @SuppressWarnings("empty-statement")
    public void crawl(String[] targets) {
        if (targets == null) {
            targets = new String[]{"cgv"};
        } else if (targets.length == 0) {
            targets = new String[]{"cgv"};
        }
        ArrayList<CrawlCinema> crawlCinemas = new ArrayList<CrawlCinema>();
        for (String target : targets) {

            AbstractCrawler crawler = null;

            if (target.equals("cgv")) {
                crawler = new CGVCrawler();
            } else if (target.equals("bhd")) {
                crawler = new BHDCrawler();
            } else if (target.equals("galaxy")) {
                crawler = new GalaxyCrawler();
            }

            if (crawler == null) {
                System.out.println("Error: cinema code is not correct: " + target);
                break;
            }

            System.out.println("Crawl: " + target);

            crawler.start();
            CrawlCinema crawlCinema = crawler.getCinema();
            MarshalUtil.marshalXML(crawlCinema, target);
            ValidCinemaTrack track = new ValidCinemaTrack(crawlCinema);
            track.start();
            track.log();
            if (track.isValid()) {
                crawlCinemas.add(crawlCinema);
            } else {
                System.out.println(crawlCinema.getName().toUpperCase() + " IS NOT VALID");
            }
        }
        try {
            Transformation trans = new Transformation();
            trans.setCrawlCinemas(crawlCinemas);
            trans.convertCrawlEntitiesToDTO();
            CinemaDAO cinemaDAO = new CinemaDAO();
            MovieDAO movieDAO = new MovieDAO();
            if (cinemaDAO.dropCollection() && movieDAO.dropCollection()) {
                for (CinemaDTO dto : trans.getCinemas()) {
                    cinemaDAO.insert(dto);
                }

                for (MovieTheaterSessionDTO movieDTO : trans.getMovies().values()) {
                    movieDAO.insert(movieDTO);
                }
            }
            System.out.println("Import data success");
        } catch (Exception e) {
            System.out.println("Import data fail");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }
}
