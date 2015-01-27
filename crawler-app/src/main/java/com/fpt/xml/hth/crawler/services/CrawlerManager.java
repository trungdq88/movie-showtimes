/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.xml.hth.crawler.services;

import com.fpt.xml.hth.crawler.crawlentities.CrawlCinema;
import com.fpt.xml.hth.crawler.crawlentities.CrawlDate;
import com.fpt.xml.hth.crawler.crawlentities.CrawlMovie;
import com.fpt.xml.hth.crawler.crawlentities.CrawlTheater;
import com.fpt.xml.hth.crawler.crawlentities.CrawlTime;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author Administrator
 */
public class CrawlerManager {

    private String url;

    public void crawl() {
        try {
            HTMLParser parser = new HTMLParser();
            parser.start();
            JAXBContext jaxbContext = JAXBContext.newInstance(CrawlCinema.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(parser.getCinema(), sw);
            String xmlString = sw.toString();

            PrintWriter writer = new PrintWriter("xml.xml", "UTF-8");
            writer.println(xmlString);
            writer.close();
//        for (CrawlTheater theater : parser.getCinema().getTheaters()) {
//            System.out.println("Theater");
//            System.out.println(theater.getId());
//            System.out.println(theater.getName());
//            System.out.println(theater.getAddress());
//            for (CrawlMovie movie : theater.getMovies()) {
//                System.out.println("Movie");
//                System.out.println(movie.getName());
//                System.out.println(movie.getDirector());
//                System.out.println(movie.getTrailer());
//                for(CrawlDate date : movie.getDates()){                   
//                    System.out.println("Date");
//                    System.out.println(date.getDate());                   
//                    for(CrawlTime time : date.getTimes()){
//                        System.out.println("Time");
//                        System.out.println(time.getTime());
//                    }
//                }
//            }
//        }
        } catch (JAXBException ex) {
            Logger.getLogger(CrawlerManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CrawlerManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(CrawlerManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
