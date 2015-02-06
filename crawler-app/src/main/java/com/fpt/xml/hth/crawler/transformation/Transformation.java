/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.xml.hth.crawler.transformation;

import com.fpt.xml.hth.crawler.crawlentities.CrawlCinema;
import com.fpt.xml.hth.crawler.crawlentities.CrawlDate;
import com.fpt.xml.hth.crawler.crawlentities.CrawlMovie;
import com.fpt.xml.hth.crawler.crawlentities.CrawlTheater;
import com.fpt.xml.hth.crawler.crawlentities.CrawlTime;
import com.fpt.xml.hth.crawler.utils.StringUtil;
import com.fpt.xml.hth.db.lib.DTO.CinemaDTO;
import com.fpt.xml.hth.db.lib.DTO.MovieTheaterSessionDTO;
import com.fpt.xml.hth.db.lib.DTO.TheaterSessionDTO;
import com.fpt.xml.hth.db.lib.entities.MovieDB;
import com.fpt.xml.hth.db.lib.entities.TheaterDB;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import org.bson.types.ObjectId;

/**
 *
 * @author Administrator
 */
public class Transformation {

    private CrawlCinema crawlCinema;
    private CinemaDTO cinema;
    private Map<String, MovieTheaterSessionDTO> movies;

    public Transformation() {
        this.crawlCinema = new CrawlCinema();
        this.cinema = new CinemaDTO();
        this.movies = new HashMap<String, MovieTheaterSessionDTO>();
    }

    public CrawlCinema getCrawlCinema() {
        return crawlCinema;
    }

    public void setCrawlCinema(CrawlCinema crawlCinema) {
        this.crawlCinema = crawlCinema;
    }

    public CinemaDTO getCinema() {
        return cinema;
    }

    public void setCinema(CinemaDTO cinema) {
        this.cinema = cinema;
    }

    public Map<String, MovieTheaterSessionDTO> getMovies() {
        return movies;
    }

    public void setMovies(Map<String, MovieTheaterSessionDTO> movies) {
        this.movies = movies;
    }
    
    /**
     * Convert CrawlEntotoes to DTO Entities
     */
    public void convertCrawlEntitiesToDTO() {
        cinema.setName(crawlCinema.getName());
        cinema.setWebsite_link(crawlCinema.getWebUrl());

        for (CrawlTheater crawlTheater : crawlCinema.getTheaters()) {
            //Add theater for CinemaDTO
            String id = ObjectId.get().toHexString();
            TheaterDB theaterDB = new TheaterDB(
                    id,
                    crawlTheater.getName(),
                    crawlTheater.getAddress(),
                    crawlTheater.getDescription(),
                    crawlTheater.getCity(),
                    crawlTheater.getMapLink(),
                    crawlTheater.getImage()
            );
            cinema.addTheater(theaterDB);
            
            for (CrawlMovie crawlMovie : crawlTheater.getMovies()) {
                MovieDB movieDB = new MovieDB(
                        crawlMovie.getName(),
                        crawlMovie.getPoster(),
                        crawlMovie.getDescription(),
                        crawlMovie.getTrailer(),
                        crawlMovie.getShowDate(),
                        crawlMovie.getLength(),
                        crawlMovie.getGenre(),
                        crawlMovie.getDirector(),
                        crawlMovie.getActor(),
                        crawlMovie.getAgeRestriction(),
                        crawlMovie.getAudioType(),
                        crawlMovie.getVideoType()
                );
                
                //Add theater for exist movie
                if (movies.containsKey(crawlMovie.getName())) {
                    TheaterSessionDTO theater = new TheaterSessionDTO();
                    theater.setId(id);
                    theater.setTheater(theaterDB);
                    theater.setCinemaName(cinema.getName());
                    theater.setLstSession(buildSessions(crawlMovie.getDates()));
                    movies.get(crawlMovie.getName()).addTheater(theater);
                } else {
                    //Add new movie
                    MovieTheaterSessionDTO movie = new MovieTheaterSessionDTO();
                    movie.setMovie(movieDB);
                    TheaterSessionDTO theater = new TheaterSessionDTO();
                    theater.setId(id);
                    theater.setTheater(theaterDB);
                    theater.setCinemaName(cinema.getName());
                    theater.setLstSession(buildSessions(crawlMovie.getDates()));
                    movie.addTheater(theater);
                    movies.put(crawlMovie.getName(), movie);
                }
            }
        }

    }

    /**
     * Build list sessions from list crawlDates
     * @param crawlDates
     * @return 
     */
    private ArrayList<String> buildSessions(ArrayList<CrawlDate> crawlDates) {
        ArrayList<String> sessions = new ArrayList<String>();
        for (CrawlDate crawlDate : crawlDates) {
            int year = Integer.parseInt(StringUtil.subDate(crawlDate.getDate(), "yyyy-mm-dd", 'y'));
            int month = Integer.parseInt(StringUtil.subDate(crawlDate.getDate(), "yyyy-mm-dd", 'y'));
            int dayOfMonth = Integer.parseInt(StringUtil.subDate(crawlDate.getDate(), "yyyy-mm-dd", 'y'));
            for (CrawlTime crawlTime : crawlDate.getTimes()) {
                int hourOfDay = Integer.parseInt(StringUtil.subDate(crawlTime.getTime(), "hh:mm", 'h'));
                int minute = Integer.parseInt(StringUtil.subDate(crawlTime.getTime(), "hh:mm", 'm'));

                Calendar date = new GregorianCalendar(year, month, dayOfMonth, hourOfDay, minute);
                sessions.add(date.getTimeInMillis() + "");
            }
        }
        return sessions;
    }
}
