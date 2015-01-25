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
public class Session {
    protected String showDateTime = "";
    protected String movie = "";
    protected String theater = "";
    
    public Session(){}

    public Session(String showDateTime, String movie, String theater) {
        this.showDateTime = showDateTime;
        this.movie = movie;
        this.theater = theater;
    }

    public String getShowDateTime() {
        return showDateTime;
    }

    public void setShowDateTime(String showDateTime) {
        this.showDateTime = showDateTime;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getTheater() {
        return theater;
    }

    public void setTheater(String theater) {
        this.theater = theater;
    }       
    
}
