/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.xml.hth.crawler.validation;

import com.fpt.xml.hth.crawler.crawlentities.CrawlMovie;
import com.fpt.xml.hth.crawler.crawlentities.CrawlTheater;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class ValidTheaterTrack extends ValidTrack<CrawlTheater> {

    public ArrayList<ValidMovieTrack> moviessTrack;

    public ValidTheaterTrack(CrawlTheater theater) {
        this.moviessTrack = new ArrayList<ValidMovieTrack>();
        this.invalidNum = 0;
        this.valid = false;
        this.element = theater;
    }

    @Override
    public void start() {
        for (CrawlMovie movie : element.getMovies()) {
            ValidMovieTrack track = new ValidMovieTrack(movie);
            track.start();
            moviessTrack.add(track);
            if (!track.isValid()) {
                invalidNum++;
            }
        }
        this.valid = isValidData();
    }

    @Override
    public void log() {
        String message = element.getName();
        message += this.valid ? " is valid \n" : " is not valid \n";
        message += invalidNum + " movies are not valid \n";
        System.out.println(message);
    }

    private boolean isValidData() {
        if (invalidNum != 0) {
            return false;
        }
        return element.isValid();
    }

}
