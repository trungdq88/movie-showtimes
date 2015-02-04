/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.xml.hth.crawler.crawlentities;

import com.fpt.xml.hth.crawler.entities.Session;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class CrawlSession extends Session {

    private String id;
    private ArrayList<CrawlDate> dates = new ArrayList<CrawlDate>();

    public CrawlSession() {
    }

    public ArrayList<CrawlDate> getDate() {
        return dates;
    }

    public void setDate(ArrayList<CrawlDate> dates) {
        this.dates = dates;
    }

    public void addDate(CrawlDate date) {
        this.dates.add(date);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Session toParent() {
        return new Session(showDateTime, movie, theater);
    }

    public boolean isValid() {
        if (dates == null) {
            return false;
        } else if (dates.isEmpty()) {
            return false;
        }
        return true;
    }
}
