/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.xml.hth.crawler.services;

import com.fpt.xml.hth.crawler.crawlentities.CrawlCinema;
import com.fpt.xml.hth.crawler.crawlentities.CrawlDate;
import com.fpt.xml.hth.crawler.crawlentities.CrawlMovie;
import com.fpt.xml.hth.crawler.crawlentities.CrawlTheater;
import com.fpt.xml.hth.crawler.crawlentities.CrawlTime;
import com.fpt.xml.hth.crawler.utils.JsoupConnect;
import com.fpt.xml.hth.crawler.utils.ProvineUtil;
import com.fpt.xml.hth.crawler.utils.StringUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
import org.jsoup.HttpStatusException;
import org.jsoup.nodes.Document;

/**
 *
 * @author Administrator
 */
public class CGVCrawler extends AbstractCrawler {

    private String url;
    private CrawlCinema cinema = new CrawlCinema("CGV Cinema", "https://www.cgv.vn/");
    private ArrayList<CrawlMovie> cmovies = new ArrayList<CrawlMovie>();
    private static final String formKey = "E6lFkGb374KeiuwG";

    public CGVCrawler() {
    }

    public CGVCrawler(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public CrawlCinema getCinema() {
        return cinema;
    }

    public void setCinema(CrawlCinema cinema) {
        this.cinema = cinema;
    }

    public ArrayList<CrawlMovie> getCmovies() {
        return cmovies;
    }

    public void setCmovies(ArrayList<CrawlMovie> cmovies) {
        this.cmovies = cmovies;
    }

    public void start() {
        System.out.println("start");
        crawlTheater();
        crawlMovieInfo();
        System.out.println("end");
    }

    /**
     * Crawl theaters
     */
    private void crawlTheater() {
        System.out.println("crawlTheaterBegin");
        for (int i = 1; i < 24; i++) {
            System.out.println("crawlTheater" + i);
            try {
                JSONObject objJSON = JsoupConnect.getJSON(cinema.getWebUrl() + "vn/theaters/cinema/ajaxview/id/" + i + "?form_key=" + formKey);
                JSONObject jsonTheater = objJSON.getJSONObject("content");

                String id = jsonTheater.optString("theater_id");
                String name = jsonTheater.optString("title");
                String address = jsonTheater.optString("address");
                String tmpCity = StringUtil
                        .convertUTF8ToASCII(jsonTheater.optString("city"))
                        .replace(" ", "").toLowerCase();
                String city = ProvineUtil.mapping.get(tmpCity);
//                String description = jsonTheater.optString("description");
                String image = cinema.getWebUrl() + "media/cinema"
                        + jsonTheater.getJSONArray("image")
                        .getJSONObject(0).optString("file");
                String map = jsonTheater.optString("map");

                CrawlTheater theater = new CrawlTheater(id, name, "", city, address, map, image);
                theater.setMovies(crawlMovie(objJSON.getJSONObject("session")));
                cinema.addTheater(theater);
            } catch (IOException ex) {
                Logger.getLogger(CGVCrawler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    /**
     * Crawl movies with full dates and times
     *
     * @param objJSON
     * @return
     */
    private ArrayList<CrawlMovie> crawlMovie(JSONObject objJSON) {
        System.out.println("crawlMovieBegin");
        Map<String, CrawlMovie> movies = new HashMap<String, CrawlMovie>();
        for (String date : JSONObject.getNames(objJSON)) {
            JSONObject objDate = objJSON.getJSONObject(date);
            for (String name : JSONObject.getNames(objDate)) {
                JSONObject objMovie = objDate.getJSONObject(name);
                String image = objMovie.getString("image");
                String url = objMovie.getString("url");
                String age = objMovie.getString("rating");
                age = age.equalsIgnoreCase("NC16") ? "16+"
                        : age.equalsIgnoreCase("NC18") ? "18+" : "";
                JSONObject objTime = objMovie.getJSONObject("session");
                for (String type : JSONObject.getNames(objTime)) {
                    JSONObject objType = objTime.getJSONObject(type);
                    ArrayList<String> times = new ArrayList<String>(
                            Arrays.asList(JSONObject.getNames(objType)));
                    if (movies.containsKey(name)) {
                        CrawlMovie movie = movies.get(name);
                        movie.setPoster(image);
                        movie.setUrl(url);
                        movie.setAgeRestriction(age);
                        movie.setName(name);
                        movie.setVideoType(type);
                        CrawlDate cdate = new CrawlDate("", date);
                        cdate.setTimes(CrawlTime.getList(times));
                        movie.addDate(cdate);
                    } else {
                        CrawlMovie movie = new CrawlMovie();
                        movie.setPoster(image);
                        movie.setUrl(url);
                        movie.setAgeRestriction(age);
                        movie.setName(name);
                        CrawlDate cdate = new CrawlDate("", date);
                        cdate.setTimes(CrawlTime.getList(times));
                        movie.addDate(cdate);
                        movies.put(name, movie);
                    }
                }

            }
        }
        System.out.println("crawlMovieEnd");
        return new ArrayList<CrawlMovie>(movies.values());
    }

    private void crawlMovieInfo() {
        System.out.println("crawlMovieInfoBegin");
        Map<String, CrawlMovie> list = new HashMap<String, CrawlMovie>();
        int j = 0;
        try {
            for (CrawlTheater theater : cinema.getTheaters()) {
                int i = 0;
                int r = 0;
                ++j;
                System.out.println("crawlMovieInfoTheater" + j);
                for (CrawlMovie movie : theater.getMovies()) {
                    ++i;
                    System.out.println("crawlMovieInfo" + i);
                    if (!list.containsKey(movie.getName())) {
                        String url = movie.getUrl();
                        Document doc = null;
                        try {
                            doc = JsoupConnect.getHTML(url);
                        } catch (HttpStatusException e) {
                            try {
                                url = StringUtil.makeCGVMovieUrl(url);
                                doc = JsoupConnect.getHTML(url);
                            } catch (HttpStatusException exs) {
                                doc = null;
                            }
                        }
                        if (doc != null) {
                            ++r;
                            System.out.println("request" + r);
                            String director = doc.select(".movie-director > div")
                                    .text().trim();
                            String actor = doc.select(".movie-actress > div")
                                    .first().text().trim();
                            String genre = doc.select(".movie-genre > div")
                                    .text().trim();
                            String length = doc.select(".movie-actress > div")
                                    .last().text().replaceAll("\\D", "").trim();
                            String description
                                    = doc.select("#collateral-tabs dd:nth-child(2) .std")
                                    .text().trim();
                            String trailer = doc.select(".product_view_trailer iframe")
                                    .attr("src").replace("embed/", "watch?v=")
                                    .replace("//", "");
                            String showDate = doc.select(".movie-release > div")
                                    .text().trim();
                            String audioType = doc.select(".movie-language > div")
                                    .text().trim();
                            movie.setActor(actor);
                            movie.setDirector(director);
                            movie.setGenre(genre);
                            movie.setLength(length);
                            movie.setDescription(description);
                            movie.setTrailer(trailer);
                            movie.setShowDate(showDate);
                            movie.setAudioType(audioType);
                            list.put(movie.getName(), movie);
                        }
                    } else {
                        CrawlMovie m = list.get(movie.getName());
                        movie.setUrl(m.getUrl());
                        movie.setActor(m.getActor());
                        movie.setDirector(m.getDirector());
                        movie.setGenre(m.getGenre());
                        movie.setLength(m.getLength());
                        movie.setDescription(m.getDescription());
                        movie.setTrailer(m.getTrailer());
                        movie.setAudioType(m.getAudioType());
                        movie.setShowDate(m.getShowDate());
                    }

                }
            }

        } catch (IOException ex) {
            Logger.getLogger(CGVCrawler.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("crawlMovieInfoEnd");
    }

}
