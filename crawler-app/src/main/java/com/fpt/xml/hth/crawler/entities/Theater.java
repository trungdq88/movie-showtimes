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
public class Theater {
    protected String name = "";
    protected String description = "";
    protected String city = "";
    protected String address = "";
    protected String mapLink = "";
    protected String image = "";
    
    public Theater(){}

    public Theater(String name, String description, String city, String address, String mapLink, String image) {
        this.name = name;
        this.description = description;
        this.city = city;
        this.address = address;
        this.mapLink = mapLink;
        this.image = image;
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
    
}
