package com.fpt.xml.hth.crawler.app;

import com.fpt.xml.hth.crawler.services.CrawlerManager;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        CrawlerManager manager = new CrawlerManager();
        manager.crawl();
    }
}
