/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.xml.hth.crawler.validation;

import com.fpt.xml.hth.crawler.crawlentities.CrawlDate;
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
        for (CrawlDate date : element.getDates()) {
            ValidSessionTrack track = new ValidSessionTrack(date);
            track.start();
            sessionsTrack.add(track);
            if (!track.isValid()) {
                invalidNum++;
            }
            this.valid = isValidData();
        }
    }

    @Override
    public void log() {
        String message = element.getName();
        message += this.valid ? " is valid \n" : " is not valid \n";
        message += invalidNum + " sessions are not valid \n";
        System.out.println(message);
    }

    private boolean isValidData() {
        if (invalidNum != 0) {
            return false;
        }
        return element.isValid();
    }
}
