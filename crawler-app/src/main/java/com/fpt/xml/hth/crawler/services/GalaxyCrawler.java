/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.xml.hth.crawler.services;

import com.fpt.xml.hth.crawler.crawlentities.*;
import com.fpt.xml.hth.crawler.utils.JsoupConnect;
import com.fpt.xml.hth.crawler.utils.StringUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

/**
 *
 * @author dinhquangtrung
 */
public class GalaxyCrawler extends AbstractCrawler {

    private String url;
    private CrawlCinema cinema = new CrawlCinema("Galaxy Cinema", "https://www.galaxycine.vn");
    private String DEFAULT_CITY = "Hồ Chí Minh";
    private String HOST = "https://www.galaxycine.vn";

    public CrawlCinema getCinema() {
        return cinema;
    }

    public void setCinema(CrawlCinema cinema) {
        this.cinema = cinema;
    }

    public void start() {
        System.out.println("start");
        crawlTheater();
        crawlMovie();
        System.out.println("end");
    }

    public static void main(String[] args) {
        GalaxyCrawler crawler = new GalaxyCrawler();
        crawler.start();
    }

    private void crawlTheater() {
        System.out.println("crawlTheaterBegin");
        try {
            url = "https://www.galaxycine.vn/vi/rap-gia-ve-lich-chieu-phim";
            Document doc = JsoupConnect.getHTML(url);
            Elements elements = doc.select(".cinema .cinema-items li a");
            for (Element element : elements) {
                String id = element.attr("value");
                String name = element.text();
                CrawlTheater theater = new CrawlTheater(id);
                theater.setName(name);

                System.out.println("Name: " + name.toUpperCase() + "; ID: " + id);
                crawlTheaterInfo(theater);
                cinema.addTheater(theater);
            }
            // crawlTheaterInfo();
            System.out.println("crawlTheaterEnd");
        } catch (IOException ex) {
            Logger.getLogger(CrawlerManager.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    private void crawlTheaterInfo(CrawlTheater theater) throws IOException {
        String theaterDetailUrl = "https://www.galaxycine.vn/actionServlet";

        Document theaterDoc = Jsoup.connect(theaterDetailUrl)
                .data("jp", "YWpheA==")
                .data("ja", "cGFnZS02")
                .data("jt", "cGFnZQ==")
                .data("je", "Y2luZW1h")
                .data("id", theater.getId())
                .post();
        Elements script = theaterDoc.select("script");
        String scriptStr = script.get(0).html();

        Pattern pattern = Pattern.compile("cinemas\\.loadCinema\\((\\{.+?\\})\\)");
        Matcher matcher = pattern.matcher(scriptStr);
        if (matcher.find()) {
            String json = matcher.group(1);
            JSONObject obj = new JSONObject(json);
//            System.out.println("Name: " + obj.getString("name"));
//            System.out.println("Address: " + obj.getString("address") + " " + obj.getString("tel"));
//            System.out.println("Image: " + "https://www.galaxycine.vn" + obj.getString("media"));
//            System.out.println("Map: " + "https://www.galaxycine.vn" + obj.getString("gmap"));
            if (!obj.isNull("address")) {
                theater.setAddress(obj.getString("address"));
            }
            theater.setCity(DEFAULT_CITY);
            theater.setDescription("");

            if (!obj.isNull("media")) {
                theater.setImage(HOST + obj.getString("media"));
            }
            if (!obj.isNull("gmap")) {
                theater.setMapLink(HOST + obj.getString("gmap"));
            }
        } else {
            System.out.println("Something wrong, result have no script tag");
        }

    }

    private ArrayList<CrawlMovie> crawlMovie() {
        try {
            ArrayList<CrawlMovie> movies = new ArrayList<CrawlMovie>();
            ArrayList<Document> mDocs = new ArrayList<Document>();
            Document doc = JsoupConnect.getHTML("https://www.galaxycine.vn/vi/phim/nowshowing");
            Elements elements = doc.select(".box-content .item .item-title");

            for (Element element : elements) {
                String movieDetailUrl = HOST + element.attr("href");
                System.out.println("Indexing: " + movieDetailUrl);
                Document mDoc = JsoupConnect.getHTML(movieDetailUrl);
                CrawlMovie movie = getCrawlMovie(mDoc);

                movies.add(movie);
                mDocs.add(mDoc);
            }

            // Processing duplicate movie name
            processingDuplicateMovieName(movies);

            // Add movies and session to theater
            addMovieSessionsToTheaters(movies, mDocs);

            // System.out.println("Total: " + movies.size() + " movie(s)");
        } catch (IOException ex) {
            Logger.getLogger(GalaxyCrawler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    private void processingDuplicateMovieName(ArrayList<CrawlMovie> movies) {
        ArrayList<String> names = new ArrayList<String>();
        for (int i = 0; i < movies.size(); i++) {
            CrawlMovie movie = movies.get(i);
            int found = names.indexOf(movie.getName());
            if (found == -1) {
                names.add(movie.getName());
            } else {
                CrawlMovie foundMovie = movies.get(found);
                foundMovie.setName(foundMovie.getName()
                        + (foundMovie.getVideoType() == null ? "" : " " + foundMovie.getVideoType()));
                movie.setName(movie.getName()
                        + (movie.getVideoType() == null ? "" : " " + movie.getVideoType()));
            }
        }
    }

    private void addMovieSessionsToTheaters(ArrayList<CrawlMovie> movies, ArrayList<Document> mDocs) {
        for (int k = 0; k < movies.size(); k++) {
            Document mDoc = mDocs.get(k);
            CrawlMovie movie = movies.get(k);

            // Add sessions
            int theaterCount = mDoc.select(".schedule-title").size();
            for (int i = 0; i < theaterCount; i++) {
                String theaterName = mDoc.select(".schedule-title").get(i).text().trim();
                // Clone current movie to add to each theater
                CrawlMovie _movie = movie.getClone();

                System.out.println("TheaterName: " + theaterName);
                CrawlTheater foundTheater = null;
                for (int find = 0; find < cinema.getTheaters().size(); find++) {
                    if (cinema.getTheaters().get(find).getName().equalsIgnoreCase(theaterName)) {
                        foundTheater = cinema.getTheaters().get(find);
                        break;
                    }
                }
                if (foundTheater != null) {
//                        if (foundTheater.getName().equalsIgnoreCase("GALAXY NGUYỄN DU")
//                                && movie.getName().equalsIgnoreCase("I FINE THANK YOU LOVE YOU/ NỮ GIA SƯ")) {
//                            System.out.println("Debugger;");
//                        }
                    // Get dates and times
                    Element showTime = mDoc.select(".showtime").get(i);
                    Elements dates = showTime.select(".showtime-title");
                    // Get dates
                    for (int index = 0; index < dates.size(); index++) {
                        CrawlDate date = new CrawlDate();
                        ArrayList<CrawlTime> _times = new ArrayList<CrawlTime>();
                        String tmpDate = StringUtil.getFirstMatch(
                                "(\\d{2}-\\d{2}-\\d{4})", dates.get(index).text());
                        date.setDate(StringUtil
                                .formatDate(tmpDate, "dd-mm-yyyy", "yyyy-mm-dd"));

                        // Get times
                        Elements times = showTime.select(".showtime-items").get(index).select(".item");
                        for (Element time1 : times) {
                            CrawlTime time = new CrawlTime();
                            time.setTime(time1.text());
                            _times.add(time);
                        }

                        date.setTimes(_times);
                        _movie.addDate(date);
                    }

                    foundTheater.addMovie(_movie);
                } else {
                    System.out.println("Something wrong, could not match theater");
                }
            }
        }
    }

    private CrawlMovie getCrawlMovie(Document mDoc) {
        CrawlMovie movie = new CrawlMovie();
        Elements _tmp = null;
        System.out.println("Title: " + mDoc.select(".movie-title").get(0).text());
        if ((_tmp = mDoc.select(".movie-title")).size() > 0) {
            movie.setName(_tmp.get(0).text());
        }
        if ((_tmp = mDoc.select(".movie-description")).size() > 0) {
            movie.setDescription(_tmp.get(0).text());
        }

        if ((_tmp = mDoc.select(".movie-detail-left img")).size() > 0) {
            movie.setPoster(HOST + _tmp.get(0).attr("src"));
        }
        if ((_tmp = mDoc.select(".btn.trailer")).size() > 0) {
            movie.setTrailer(_tmp.get(0).attr("value"));
        }
        if ((_tmp = mDoc.select(".lbl")).size() > 0) {
            movie.setShowDate(StringUtil.getFirstMatch("(\\d{2}\\.\\d{2}\\.\\d{4})",
                    _tmp.get(0).text()));
        }
        movie.setLength(""); // length
        if ((_tmp = mDoc.select(".lbl")).size() > 0) {
            movie.setGenre(((TextNode) _tmp.get(1).childNode(2)).text());
        }
        if ((_tmp = mDoc.select(".lbl")).size() > 2) {
            movie.setDirector(((TextNode) _tmp.get(3).childNode(2)).text());
        }
        if ((_tmp = mDoc.select(".lbl")).size() > 1) {
            movie.setActor(((TextNode) _tmp.get(2).childNode(2)).text());
        }
        if ((_tmp = mDoc.select(".waring")).size() > 0) {
            movie.setAgeRestriction(_tmp.get(0).text());
        }
        if ((_tmp = mDoc.select(".caption")).size() > 0) {
            movie.setAudioType(_tmp.get(0).text());
        }
        if ((_tmp = mDoc.select(".type")).size() > 0) {
            movie.setVideoType(_tmp.get(0).text());
        }
        return movie;
    }
}
