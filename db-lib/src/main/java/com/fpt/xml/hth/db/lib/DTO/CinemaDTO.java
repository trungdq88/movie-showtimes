/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.xml.hth.db.lib.DTO;

import com.fpt.xml.hth.db.lib.entities.Theater;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 * class Cinema mapping with collection Cinema
 *
 * @author Thu Hoa
 */
public class CinemaDTO {

    //private String id;
    private ObjectId id;
    private String name;
    private String website_link;
    private List<Theater> lstTheater;

    public CinemaDTO() {
    }

    public CinemaDTO(CinemaDTO cinema) {
        this.id = cinema.getId();
        this.name = cinema.getName();
        this.website_link = cinema.getWebsite_link();
        List<Theater> lst = cinema.getLstTheater();
        this.lstTheater = new ArrayList<Theater>(lst);
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public List<Theater> getLstTheater() {
        return lstTheater;
    }

    public void setLstTheater(List<Theater> lstTheater) {
        this.lstTheater = lstTheater;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite_link() {
        return website_link;
    }

    public void setWebsite_link(String website_link) {
        this.website_link = website_link;
    }

}
