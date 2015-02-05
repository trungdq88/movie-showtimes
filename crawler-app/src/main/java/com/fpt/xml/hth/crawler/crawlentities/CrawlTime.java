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

    public static ArrayList<CrawlTime> getList(ArrayList<String> times) {
        ArrayList<CrawlTime> ctimes = new ArrayList<CrawlTime>();
        for (String item : times) {
            CrawlTime time = new CrawlTime("", item);
            ctimes.add(time);
        }
        return ctimes;
    }

    public boolean isValid() {
        if (!StringUtil.validStringFormat(time, StringUtil.REGEX_TIME)) {
            System.out.println("Invalid time: " + time + "\n");
            return false;
        }
        return true;
    }
}
