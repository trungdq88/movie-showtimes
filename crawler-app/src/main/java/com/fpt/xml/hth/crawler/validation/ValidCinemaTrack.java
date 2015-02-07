/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.xml.hth.crawler.validation;

import com.fpt.xml.hth.crawler.crawlentities.CrawlCinema;
import com.fpt.xml.hth.crawler.crawlentities.CrawlTheater;
import com.fpt.xml.hth.crawler.utils.StringUtil;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class ValidCinemaTrack extends ValidTrack<CrawlCinema, ValidTheaterTrack> {

    public ValidCinemaTrack(CrawlCinema cinema) {
        this.tracks = new ArrayList<ValidTheaterTrack>();
        this.invalidNum = 0;
        this.valid = false;
        this.element = cinema;
    }

    @Override
    public void start() {
        for (CrawlTheater theater : element.getTheaters()) {
            ValidTheaterTrack track = new ValidTheaterTrack(theater);
            track.start();
            tracks.add(track);
            if (!track.isValid()) {
                invalidNum++;
            }
        }
        this.valid = isValidData();
    }

    @Override
    public void log() {
        invalidTheaters += invalidNum;
        theaters += element.getTheaters().size();
        if (!isValid()) {
            String message = element.getName();
            message += this.valid ? " is valid \n" : " is not valid \n";
            message += invalidNum + " theaters are not valid \n";
            System.out.println(message);
        }
        for (ValidTheaterTrack track : tracks) {
            track.log();
        }
        System.out.println(invalidTheaters + "/" + theaters + " theaters invalid.\n");
        System.out.println(invalidMovies.size() + "/" + movies.size() + " movies invalid.\n");
        System.out.println(invalidDates + "/" + dates + " dates invalid.\n");
        System.out.println(invalidTimes + "/" + times + " times invalid.\n");
        System.out.println(computeValid() + "");
    }

    @Override
    protected boolean isValidData() {
        return StringUtil.notEmpty(element.getName());
    }
    
    /**
     * Check valid with percents of invalid data
     * 
     * @return invalid with any 30% of theater or movie or date or time
     */
    private boolean computeValid() {
        try {
            int theater = invalidCinemas * 100 / theaters;
            int movie = invalidMovies.size() * 100 / movies.size();
            int date = invalidDates * 100 / dates;
            int time = invalidTimes * 100 / times;
            return theater <= 30 && movie <= 30 && date <= 30 && time <= 30;
        } catch (Exception e) {
            return false;
        }
    }

}
