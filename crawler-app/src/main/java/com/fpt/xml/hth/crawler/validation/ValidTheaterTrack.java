/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.xml.hth.crawler.validation;

import com.fpt.xml.hth.crawler.crawlentities.CrawlMovie;
import com.fpt.xml.hth.crawler.crawlentities.CrawlTheater;
import com.fpt.xml.hth.crawler.utils.ListUtil;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class ValidTheaterTrack extends ValidTrack<CrawlTheater, ValidMovieTrack> {

    public ValidTheaterTrack(CrawlTheater theater) {
        this.tracks = new ArrayList<ValidMovieTrack>();
        this.invalidNum = 0;
        this.valid = false;
        this.element = theater;
    }

    @Override
    public void start() {
        for (CrawlMovie movie : element.getMovies()) {
            if (!movies.containsKey(movie.getName())) {
                movies.put(movie.getName(), movie);
            }
            ValidMovieTrack track = new ValidMovieTrack(movie);
            track.start();
            tracks.add(track);
            if (!track.isValid()) {
                if (!invalidMovies.containsKey(movie.getName())) {
                    invalidMovies.put(movie.getName(), movie);
                }
                invalidNum++;
            }
        }
        this.valid = isValidData();
    }

    @Override
    public void log() {
//        invalidMovies += invalidNum;
//        movies += element.getMovies().size();
        if (!isValid()) {
            String str = "    ";
            String message = str;
            message += element.getName();
            message += this.valid ? " is valid \n" : " is not valid \n";
            message += str;
            message += invalidNum + " movies are not valid \n";
            System.out.println(message);
            for (ValidMovieTrack track : tracks) {
                track.log();
            }
        }
    }

    @Override
    protected boolean isValidData() {
        return element.isValid();
    }

}
