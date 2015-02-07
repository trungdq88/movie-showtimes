/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.xml.hth.crawler.validation;

import com.fpt.xml.hth.crawler.crawlentities.CrawlMovie;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Administrator
 * @param <G>
 * @param <T>
 */
public abstract class ValidTrack<G, T> {

    protected static int invalidDates = 0;
    protected static int invalidTimes = 0;
//    protected static int invalidMovies = 0;
    protected static int invalidTheaters = 0;
    protected static int invalidCinemas = 0;
    protected static int dates = 0;
    protected static int times = 0;
//    protected static int movies = 0;
    protected static int theaters = 0;
    protected static int cinemas = 0;
    protected static Map<String, CrawlMovie> movies = new HashMap<String, CrawlMovie>();
    protected static Map<String, CrawlMovie> invalidMovies = new HashMap<String, CrawlMovie>();

    protected ArrayList<T> tracks;
    protected G element;
    protected int invalidNum;
    protected boolean valid;

    /**
     * Start validation crawled data
     */
    public abstract void start();

    /**
     * Check validation after scrawler data Return 'true' mean that data can be
     * imported into database Return 'false' mean that data cannot be imported
     * into database
     *
     * @return
     */
    public boolean isValid() {
        return valid;
    }

    /**
     * Get number of invalid data
     *
     * @return
     */
    public int getInvalidNum() {
        return invalidNum;
    }

    /**
     * Print out invalid log
     */
    public abstract void log();

    /**
     * Get element
     *
     * @return
     */
    public G getElement() {
        return element;
    }

    /**
     * Check valid element data
     *
     * @return
     */
    protected abstract boolean isValidData();

}
