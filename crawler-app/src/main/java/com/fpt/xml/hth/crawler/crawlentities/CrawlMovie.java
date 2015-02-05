/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.xml.hth.crawler.crawlentities;

import com.fpt.xml.hth.crawler.utils.StringUtil;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class CrawlMovie {

    private String id;
    private ArrayList<CrawlDate> dates = new ArrayList<CrawlDate>();
    private String url;
    private String name;
    private String description;
    private String poster;
    private String trailer;
    private String showDate;
    private String length;
    private String genre;
    private String director;
    private String actor;
    private String ageRestriction;
    private String audioType;
    private String videoType;

    public CrawlMovie() {
        this.id = "";
        this.url = "";
        this.name = "";
        this.description = "";
        this.poster = "";
        this.trailer = "";
        this.showDate = "";
        this.length = "";
        this.genre = "";
        this.director = "";
        this.actor = "";
        this.ageRestriction = "";
        this.audioType = "";
        this.videoType = "";
    }

    public CrawlMovie(String id) {
        this.id = id;
        this.url = "";
        this.name = "";
        this.description = "";
        this.poster = "";
        this.trailer = "";
        this.showDate = "";
        this.length = "";
        this.genre = "";
        this.director = "";
        this.actor = "";
        this.ageRestriction = "";
        this.audioType = "";
        this.videoType = "";
    }

    public CrawlMovie(String name, String description, String poster, String trailer, String showDate, String length, String genre, String director, String actor, String ageRestriction, String audioType, String videoType) {
        this.id = "";
        this.name = name;
        this.description = description;
        this.poster = poster;
        this.trailer = trailer;
        this.showDate = showDate;
        this.length = length;
        this.genre = genre;
        this.director = director;
        this.actor = actor;
        this.ageRestriction = ageRestriction;
        this.audioType = audioType;
        this.videoType = videoType;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getShowDate() {
        return showDate;
    }

    public void setShowDate(String showDate) {
        this.showDate = showDate;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(String ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public String getAudioType() {
        return audioType;
    }

    public void setAudioType(String audioType) {
        this.audioType = audioType;
    }

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
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

    public boolean isValid() {
        int check = 0;

        if (!StringUtil.notEmpty(name)) {
            System.out.println("Empty movie name!");
            check++;
        }
        if (!StringUtil.validStringFormat(trailer, StringUtil.REGEX_YOUTUBE_URL)) {
            System.out.println(name + " invalid trailer url: " + trailer + "\n");
            check++;
        }
        if (!StringUtil.validStringFormat(length, StringUtil.REGEX_LENGTH)) {
            System.out.println(name + " invalid movie length: " + length + "\n");
            check++;
        }
        if (!StringUtil.notEmpty(poster)) {
            System.out.println(name + " has empty poster url!");
            check++;
        }
        if (!StringUtil.notEmpty(description)) {
            System.out.println(name + " has empty description!");
            check++;
        }

        return check == 0;
    }
}
