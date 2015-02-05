/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.xml.hth.db.lib.DTO;

import com.fpt.xml.hth.db.lib.entities.TheaterDB;
import java.util.List;

/**
 * class TheaterDBSessionDTO mapping with one element in list theaters in Movie collection
 * @author Thu Hoa
 */
public class TheaterSessionDTO {
private String id;
    private TheaterDB theater;
    private String cinemaName;
    private List<String> lstSession;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public TheaterDB getTheater() {
        return theater;
    }

    public void setTheater(TheaterDB theater) {
        this.theater = theater;
    }

    public List<String> getLstSession() {
        return lstSession;
    }

    public void setLstSession(List<String> lstSession) {
        this.lstSession = lstSession;
    }

}
