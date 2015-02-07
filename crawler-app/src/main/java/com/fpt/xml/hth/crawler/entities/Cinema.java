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
public class Cinema {
    protected String name = "";
    protected String webUrl = "";
    
    public Cinema(){}

    public Cinema(String name, String webUrl) {
        this.name = name;
        this.webUrl = webUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }
   
}
