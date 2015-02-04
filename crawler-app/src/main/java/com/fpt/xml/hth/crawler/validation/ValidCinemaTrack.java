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
public class ValidCinemaTrack extends ValidTrack<CrawlCinema> {

    public ArrayList<ValidTheaterTrack> theatersTrack;

    public ValidCinemaTrack(CrawlCinema cinema) {
        this.theatersTrack = new ArrayList<ValidTheaterTrack>();
        this.invalidNum = 0;
        this.valid = false;
        this.element = cinema;
    }

    @Override
    public void start() {
        for (CrawlTheater theater : element.getTheaters()) {
            ValidTheaterTrack track = new ValidTheaterTrack(theater);
            track.start();
            theatersTrack.add(track);
            if (!track.isValid()) {
                invalidNum++;
            }
        }
        this.valid = invalidNum == 0 && StringUtil.notEmpty(element.getName());
    }

    @Override
    public void log() {
        String message = element.getName();
        message += this.valid ? " is valid \n" : " is not valid \n";
        message += invalidNum + " theaters are not valid \n";
        System.out.println(message);
    }

}
