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
import com.fpt.xml.hth.crawler.utils.ListUtil;
import com.fpt.xml.hth.crawler.utils.StringUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
    private CrawlCinema cinema = new CrawlCinema();
//    private JSONObject objJSON = new JSONObject();
    private ArrayList<CrawlMovie> cmovies = new ArrayList<CrawlMovie>();

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
                JSONObject objJSON = JsoupConnect.getJSON("https://www.cgv.vn/vn/theaters/cinema/ajaxview/id/" + i + "?form_key=1htcwNFhJGmxZ3BT");
                JSONObject jsonTheater = objJSON.getJSONObject("content");

                String id = jsonTheater.optString("theater_id");
                String name = jsonTheater.optString("title");
                String address = jsonTheater.optString("address");
                String city = jsonTheater.optString("city");
//                String description = jsonTheater.optString("description");
                String image = "https://www.cgv.vn/media/cinema"
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
        ArrayList<CrawlMovie> movies = new ArrayList<CrawlMovie>();
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
                    ArrayList<String> times = new ArrayList<String>(Arrays.asList(JSONObject.getNames(objType)));
                    int index = ListUtil.indexOfItem(movies, name);
                    if (ListUtil.indexOfItem(movies, name) != -1) {
                        CrawlMovie movie = movies.get(index);
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
                        movies.add(movie);
                    }
                }

            }
        }
        System.out.println("crawlMovieEnd");
        return movies;
    }

    private void crawlMovieInfo() {
        System.out.println("crawlMovieInfoBegin");
        ArrayList<CrawlMovie> list = new ArrayList<CrawlMovie>();
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
                    int index = ListUtil.indexOfItem(list, movie.getName());

                    if (index == -1) {
                        list.add(movie);
                        String url = movie.getUrl();
                        Document doc;
                        try {
                            doc = JsoupConnect.getHTML(url);
                        } catch (HttpStatusException e) {
                            url = StringUtil.makeCGVMovieUrl(url);
                            doc = JsoupConnect.getHTML(url);
                        }
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
                    } else {
                        CrawlMovie m = list.get(index);
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
