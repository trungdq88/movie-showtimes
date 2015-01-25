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
public class CrawlTime {

    private String id;
    private String time;

    public CrawlTime() {
    }
    public CrawlTime(String id, String time) {
        this.id = id;
        this.time = time;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
