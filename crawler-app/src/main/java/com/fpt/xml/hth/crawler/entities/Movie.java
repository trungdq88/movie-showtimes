/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fpt.xml.hth.crawler.entities;

/**
 *
 * @author Administrator
 */
public class Movie {
    protected String name = "";
    protected String description = "";
    protected String poster = "";
    protected String trailer = "";
    protected String showDate = "";
    protected String length = "";
    protected String genre = "";
    protected String director = "";
    protected String actor = "";
    protected String ageRestriction = "";
    protected String audioType = "";
    protected String videoType = "";
    
    public Movie(){}

    public Movie(String name, String description, String poster, String trailer, String showDate, String length, String genre, String director, String actor, String ageRestriction, String audioType, String videoType) {
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
    
}
