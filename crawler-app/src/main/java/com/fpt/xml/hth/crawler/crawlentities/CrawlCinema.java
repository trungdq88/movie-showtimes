/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.xml.hth.crawler.crawlentities;

import com.fpt.xml.hth.crawler.entities.Cinema;
import com.fpt.xml.hth.crawler.utils.StringUtil;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@XmlRootElement
public class CrawlCinema extends Cinema {

    private String id;
    private ArrayList<CrawlTheater> theaters = new ArrayList<CrawlTheater>();

    public CrawlCinema(){};
    
    public CrawlCinema(String name) {
        this.name = name;
    }

    public ArrayList<CrawlTheater> getTheaters() {
        return theaters;
    }

    public void setTheaters(ArrayList<CrawlTheater> theaters) {
        this.theaters = theaters;
    }

    public void addTheater(CrawlTheater theater) {
        theaters.add(theater);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Cinema toParent() {
        return new Cinema(name, webUrl);
    }

    public boolean isValid() {
        if(!StringUtil.notEmpty(name)){
            System.out.println("Empty cinema name!");
            return false;
        }
        return true;
    }

}
