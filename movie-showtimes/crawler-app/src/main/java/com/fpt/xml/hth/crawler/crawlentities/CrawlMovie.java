/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.xml.hth.crawler.crawlentities;

import com.fpt.xml.hth.crawler.entities.Cinema;
import com.fpt.xml.hth.crawler.entities.Movie;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class CrawlMovie extends Movie {

    private String id;
    private ArrayList<CrawlDate> dates = new ArrayList<CrawlDate>();
    private String url;

    public CrawlMovie() {
    }

    public CrawlMovie(String name, String description, String poster, String trailer, String showDate, String length, String genre, String director, String actor, String ageRestriction, String audioType, String videoType) {
        super(name, description, poster, trailer, showDate, length, genre, director, actor, ageRestriction, audioType, videoType);

    }

    public ArrayList<CrawlDate> getDates() {
        return dates;
    }

    public void setDates(ArrayList<CrawlDate> dates) {
        this.dates = dates;
    }

    public void addDate(CrawlDate date) {
        dates.add(date);
    }

    public CrawlMovie(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    

    public Movie toParent() {
        return new Movie(name, description, poster, trailer, showDate, length, genre, director, actor, ageRestriction, audioType, videoType);
    }

    public CrawlMovie getClone() {
        return new CrawlMovie(
                this.name,
                this.description,
                this.poster,
                this.trailer,
                this.showDate,
                this.length,
                this.genre,
                this.director,
                this.actor,
                this.ageRestriction,
                this.audioType,
                this.videoType
        );
    }
}