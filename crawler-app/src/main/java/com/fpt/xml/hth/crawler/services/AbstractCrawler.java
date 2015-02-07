package com.fpt.xml.hth.crawler.services;

import com.fpt.xml.hth.crawler.crawlentities.CrawlCinema;

/**
 * Created by dinhquangtrung on 2/3/15.
 */
public abstract class AbstractCrawler {
    public abstract void start();

    public abstract CrawlCinema getCinema();
}
