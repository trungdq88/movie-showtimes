/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.xml.hth.crawler.crawlentities;

import com.fpt.xml.hth.crawler.entities.Theater;
import com.fpt.xml.hth.crawler.utils.ProvineUtil;
import com.fpt.xml.hth.crawler.utils.StringUtil;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class CrawlTheater extends Theater {

    private String id;
    private ArrayList<CrawlMovie> movies = new ArrayList<CrawlMovie>();

    public CrawlTheater() {
    }

    public CrawlTheater(
            String id,
            String name,
            String description,
            String city,
            String address,
            String mapLink,
            String image
    ) {
        super(name, description, city, address, mapLink, image);
        this.id = id;
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

    public Theater toTheater() {
        return new Theater(name, description, city, address, mapLink, image);
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
