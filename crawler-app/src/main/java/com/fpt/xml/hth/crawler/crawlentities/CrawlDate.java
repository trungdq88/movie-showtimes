/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fpt.xml.hth.crawler.crawlentities;

import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class CrawlDate {
    private String id;
    private String date;
    private ArrayList<CrawlTime> times = new ArrayList<CrawlTime>();
    
    public CrawlDate(){}
    
    public CrawlDate(String id, String date){
        this.id = id;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<CrawlTime> getTimes() {
        return times;
    }

    public void setTimes(ArrayList<CrawlTime> times) {
        this.times = times;
    }
    
    public void addTime(CrawlTime time){
        this.times.add(time);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
}
