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
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Administrator
 */
public class BHDCrawler {

    private String url;
    private CrawlCinema cinema = new CrawlCinema();
    ArrayList<CrawlMovie> cmovies = new ArrayList<CrawlMovie>();

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

    public void start() {
        System.out.println("start");
        crawlTheater();
        System.out.println("end");
    }

    /**
     * Crawl theaters
     */
    private void crawlTheater() {
        System.out.println("crawlTheaterBegin");
        try {
            url = "http://bhdstar.vn/vn/movie-booking/ajax-response?mode=ajax"
                    + "&type=cinema&reponse_type=option_tag&opt_chain=cinema"
                    + "&opt_tech=*&opt_cinema=101&opt_movie=*&opt_time=*&opt_date=*";
//            doc = Jsoup.parse(new URL(url).openStream(), "UTF-8", url);
            Document doc = JsoupConnect.getHTML(url);
            Elements elements = doc.select("option");
            for (Element element : elements) {
                String id = element.val();
                String name = element.text();
                CrawlTheater theater = new CrawlTheater(id);
                theater.setName(name);
                theater.setMovies(crawlMovie(theater.getId()));
                cinema.addTheater(theater);
            }
            crawlTheaterInfo();
            System.out.println("crawlTheaterEnd");
        } catch (IOException ex) {
            Logger.getLogger(CrawlerManager.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Get theater address
     */
    private void crawlTheaterInfo() {
        System.out.println("crawlTheaterInfoBegin");
        try {
            url = "http://bhdstar.vn/vn/content/tat-ca-rap-4184";
            Document doc = JsoupConnect.getHTML(url);
            Elements elements = doc.select("#news-list > div");
            for (Element element : elements) {
                String name = element.select(".blackbox h2").text();
                String address = element.select(".blackbox .right").get(0).text();
                String city = "Hồ Chí Minh";
                String image = "http://bhdstar.vn"
                        + element.select(".main-image").attr("src");
                for (CrawlTheater theater : cinema.getTheaters()) {
                    if (theater.getName().equals(name)) {
                        theater.setAddress(address);
                        theater.setCity(city);
                        theater.setImage(image);
                    }
                }
            }
            System.out.println("crawlTheaterInfoEnd");
        } catch (IOException ex) {
            Logger.getLogger(CrawlerManager.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Get movies for a theater
     *
     * @param theaterId
     * @return
     */
    private ArrayList<CrawlMovie> crawlMovie(String theaterId) {
        System.out.println("setMovieBegin");
        crawlMovie();
        ArrayList<CrawlMovie> movies = new ArrayList<CrawlMovie>();
        try {
            String url = "http://bhdstar.vn/vn/movie-booking/ajax-response?mode=ajax"
                    + "&type=movie&reponse_type=option_tag&opt_chain=cinema"
                    + "&opt_tech=*&opt_cinema=" + theaterId
                    + "&opt_movie=*&opt_time=*&opt_date=*";
            Document doc = JsoupConnect.getHTML(url);
            Elements elements = doc.select("option");
            for (Element element : elements) {
                String id = element.val();
                String name = element.text();
                CrawlMovie movie = new CrawlMovie();
                for (CrawlMovie item : cmovies) {
                    if (item.getName().equals(name)) {
                        movie = item;
                        break;
                    }
                }
                movie.setId(id);
                movie.setDates(crawlDate(movie.getId(), theaterId));
                movies.add(movie);
            }
            System.out.println("setMovieEnd");
            return movies;
        } catch (IOException ex) {
            Logger.getLogger(CrawlerManager.class.getName())
                    .log(Level.SEVERE, null, ex);
            return movies;
        }
    }

    /**
     * Crawl all movies
     */
    private void crawlMovie() {
        System.out.println("crawlMovieBegin");
        try {
            String url = "http://bhdstar.vn/vn/movie/browse/dang-chieu-4200";
            Document doc = JsoupConnect.getHTML(url);
            Elements elements = doc.select("#movie-browse > div");
            for (Element element : elements) {
                String id = "";
                String poster = "";
                String name = "";
                String category = "";
                String showDate = "";
                String length = "";
                String actor = "";
                String director = "";
                String description = "";
                String videoType = "";
                String trailer = "";
                String ageRestriction = "";
                String audioType = "";

                poster = "http://bhdstar.vn"
                        + element.select("div > a img").attr("src");
                name = element.select(".title a").text();
                videoType
                        = category = element.select(".categorized")
                        .text().replace("Thể loại:", "").trim();
                showDate = element.select(".launch-date")
                        .text().replace("Khởi chiếu:", "").trim();
                length = element.select(".duration")
                        .text().replaceAll("\\D", "").trim();
                actor = element.select(".movie-stars")
                        .text().replace("Diễn viên:", "").trim();
                description = element.select(".summary").text();

                id = element.select(".title a").attr("href");
                id = id.substring(id.length() - 5);
                String url2 = "http://bhdstar.vn/vn/movie/movie-detail-popup?item_id=";
                Document doc2 = JsoupConnect.getHTML(url2 + id);
                trailer = doc2.select(".video-container iframe").attr("src")
                        .replace("embed/", "watch?v=").replace("//", "");
                for (Element e : doc2.select(".outline-container div.row")) {
                    if (e.select(".left").text().equalsIgnoreCase("Đạo diễn")) {
                        director = e.select(".right").text();
                    } else if (e.select(".left").text().equalsIgnoreCase("Định dạng")) {
                        videoType = e.select(".right").text();
                    } else if (e.select(".left").text().equalsIgnoreCase("Giới hạn độ tuổi")){
                        ageRestriction = e.select(".right").text();
                    } else if (e.select(".left").text().equalsIgnoreCase("Ngôn ngữ phim")){
                        audioType = e.select(".right").text();
                    }
                }

                CrawlMovie movie
                        = new CrawlMovie(name, description, poster, trailer, 
                                showDate, length, category, director, actor, 
                                ageRestriction, audioType, videoType);
                cmovies.add(movie);
            }
            System.out.println("crawlMovieEnd");
        } catch (IOException ex) {
            Logger.getLogger(CrawlerManager.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Get dates for a movie
     *
     * @param movieId
     * @param theaterId
     * @return
     */
    private ArrayList<CrawlDate> crawlDate(String movieId, String theaterId) {
        System.out.println("crawlDateBegin");
        ArrayList<CrawlDate> dates = new ArrayList<CrawlDate>();
        try {
            String url = "http://bhdstar.vn/vn/movie-booking/ajax-response?"
                    + "mode=ajax&type=date&reponse_type=option_tag&"
                    + "opt_chain=cinema&opt_tech=*&opt_cinema=" + theaterId
                    + "&opt_movie=" + movieId + "&opt_time=*&opt_date=*";
            Document doc = JsoupConnect.getHTML(url);
            Elements elements = doc.select("option");
            for (Element element : elements) {
                String id = element.val();
                String date = element.text();
                CrawlDate cdate = new CrawlDate(id, date);
                cdate.setTimes(crawlTime(cdate.getId(), movieId, theaterId));
                dates.add(cdate);
            }
            System.out.println("crawlDateEnd");
            return dates;

        } catch (IOException ex) {
            Logger.getLogger(CrawlerManager.class
                    .getName())
                    .log(Level.SEVERE, null, ex);
            return dates;
        }
    }

    /**
     * Get time for a movie
     *
     * @param dateId
     * @param movieId
     * @param theaterId
     * @return
     */
    private ArrayList<CrawlTime> crawlTime(String dateId, String movieId, String theaterId) {
        System.out.println("crawlTimeBegin");
        ArrayList<CrawlTime> times = new ArrayList<CrawlTime>();
        try {
            String url = "http://bhdstar.vn/vn/movie-booking/ajax-response?"
                    + "mode=ajax&type=time&reponse_type=option_tag&"
                    + "opt_chain=cinema&opt_tech=*&opt_cinema=" + theaterId
                    + "&opt_movie=" + movieId + "&opt_time=*&opt_date=" + dateId;
            Document doc = JsoupConnect.getHTML(url);
            Elements elements = doc.select("option");
            for (Element element : elements) {
                String id = element.val();
                String time = element.text().substring(0, 4);
                CrawlTime ctime = new CrawlTime(id, time);
                times.add(ctime);
            }
            System.out.println("crawlTimeEnd");
            return times;

        } catch (IOException ex) {
            Logger.getLogger(CrawlerManager.class
                    .getName())
                    .log(Level.SEVERE, null, ex);
            return times;
        }
    }

}
