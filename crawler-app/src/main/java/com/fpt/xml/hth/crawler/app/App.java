package com.fpt.xml.hth.crawler.app;

import com.fpt.xml.hth.crawler.services.CrawlerManager;

/**
 * Hello world!
 *
 */
public class App 
{
    /**
     * TODO: make the output log beautiful
     * TODO: validate data after crawl
     * TODO: push to database after validate
     * @param args
     */
    public static void main( String[] args )
    {
        CrawlerManager manager = new CrawlerManager();
        System.out.println("Start crawling " + args.length + " cinema(s)");
        manager.crawl(args);
    }
}
