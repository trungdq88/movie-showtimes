/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.xml.hth.crawler.crawlentities;

import com.fpt.xml.hth.crawler.utils.ProvineUtil;
import com.fpt.xml.hth.crawler.utils.StringUtil;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class CrawlTheater {

    private String id;
    private ArrayList<CrawlMovie> movies = new ArrayList<CrawlMovie>();
    private String name;
    private String description;
    private String city;
    private String address;
    private String mapLink;
    private String image;

    public CrawlTheater() {
        this.id="";
        this.name="";
        this.description="";
        this.city="";
        this.address="";
        this.mapLink="";
        this.image="";
    }

    public CrawlTheater(String id, String name, String description, String city, String address, String mapLink, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.city = city;
        this.address = address;
        this.mapLink = mapLink;
        this.image = image;
    }

    public ArrayList<CrawlMovie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<CrawlMovie> movies) {
        this.movies = movies;
    }

    public void addMovie(CrawlMovie movie) {
        movies.add(movie);
    }

    public CrawlTheater(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMapLink() {
        return mapLink;
    }

    public void setMapLink(String mapLink) {
        this.mapLink = mapLink;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    public boolean isValid() {
        int check = 0;

        if (!StringUtil.notEmpty(name)) {
            System.out.println("Empty thater name!");
            check++;
        }
        if (!ProvineUtil.provines.containsValue(city)) {
            System.out.println(name + " invalid provine name: " + city + "\n");
            check++;
        }
        if (!StringUtil.notEmpty(image)) {
            System.out.println(name + " has empty image url!");
            check++;
        }
        return check == 0;
    }
}
