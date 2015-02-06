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
        for (String target : targets) {

            AbstractCrawler crawler = null;
//            try {
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
            if(track.isValid()){
                Transformation trans = new Transformation();
                trans.setCrawlCinema(crawlCinema);
                trans.getCinema();
            }else{
                System.out.println("NOT VALID CINEMA");
            }
        }
    }
}
