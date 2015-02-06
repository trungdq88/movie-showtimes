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
 * @author HaiNNT
 */
public class ValidMovieTrack extends ValidTrack<CrawlMovie, ValidSessionTrack> {

    public ValidMovieTrack(CrawlMovie movie) {
        this.tracks = new ArrayList<ValidSessionTrack>();
        this.invalidNum = 0;
        this.valid = false;
        this.element = movie;
    }

    /**
     * Start validate
     */
    @Override
    public void start() {
        for (CrawlDate date : element.getDates()) {
            ValidSessionTrack track = new ValidSessionTrack(date);
            track.start();
            tracks.add(track);
            if (!track.isValid()) {
                invalidNum++;
            }
            this.valid = isValidData();
        }
    }

    @Override
    public void log() {
        invalidDates += invalidNum;
        dates += element.getDates().size();
        if (!isValid()) {
            String str = "        ";
            String message = str;
            message += element.getName();
            message += this.valid ? " is valid \n" : " is not valid \n";
            message += str;
            message += invalidNum + " sessions are not valid \n";
            System.out.println(message);
        }
        for (ValidSessionTrack track : tracks) {
            track.log();
        }
    }

    @Override
    protected boolean isValidData() {
        return element.isValid();
    }
}
