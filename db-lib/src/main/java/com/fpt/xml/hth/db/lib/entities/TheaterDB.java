/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.xml.hth.db.lib.entities;

import java.io.Serializable;
import org.bson.types.ObjectId;

/**
 *
 * @author Thu Hoa
 */
public class TheaterDB {

    private String id;
    private String name;
    private String address;
    private String description;
    private String city;
    private String map_link;
    private String image;

    public TheaterDB() {}
    
    public TheaterDB(
            String id, 
            String name, 
            String address, 
            String description, 
            String city, 
            String map_link, 
            String image
    ) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.city = city;
        this.map_link = map_link;
        this.image = image;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getMap_link() {
        return map_link;
    }

    public void setMap_link(String map_link) {
        this.map_link = map_link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
