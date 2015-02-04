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

    public void crawl(String[] targets) {

        for (String target : targets) {

            AbstractCrawler crawler = null;
            try {
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
                JAXBContext jaxbContext = JAXBContext.newInstance(CrawlCinema.class);
                Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
                StringWriter sw = new StringWriter();
                jaxbMarshaller.marshal(crawler.getCinema(), sw);
                String xmlString = sw.toString();

                PrintWriter writer = new PrintWriter("output_" + target + ".xml", "UTF-8");
                writer.println(xmlString);
                writer.close();

            } catch (JAXBException ex) {
                Logger.getLogger(CrawlerManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(CrawlerManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(CrawlerManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
