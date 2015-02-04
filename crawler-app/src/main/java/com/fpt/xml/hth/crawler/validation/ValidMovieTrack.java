/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.xml.hth.crawler.validation;

import com.fpt.xml.hth.crawler.crawlentities.CrawlMovie;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class ValidMovieTrack extends ValidTrack<CrawlMovie> {

    public ArrayList<ValidSessionTrack> sessionsTrack;

    public ValidMovieTrack(CrawlMovie movie) {
        this.sessionsTrack = new ArrayList<ValidSessionTrack>();
        this.invalidNum = 0;
        this.valid = false;
        this.element = movie;
    }

    @Override
    public void start() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void log() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
