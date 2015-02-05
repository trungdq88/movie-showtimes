/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.xml.hth.db.lib.entities;

/**
 *
 * @author Thu Hoa
 */
public class MovieDB {

    private String name;
    private String poster;
    private String description;
    private String trailer;
    private String show_date;
    private String length;
    private String genre;
    private String director;
    private String actor;
    private String age_restriction;
    private String audio_type;
    private String video_type;

    public MovieDB() {}

    public MovieDB(
            String name,
            String poster,
            String description,
            String trailer,
            String show_date,
            String length,
            String genre,
            String director,
            String actor,
            String age_restriction,
            String audio_type,
            String video_type
    ) {
        this.name = name;
        this.poster = poster;
        this.description = description;
        this.trailer = trailer;
        this.show_date = show_date;
        this.length = length;
        this.genre = genre;
        this.director = director;
        this.actor = actor;
        this.age_restriction = age_restriction;
        this.audio_type = audio_type;
        this.video_type = video_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getShow_date() {
        return show_date;
    }

    public void setShow_date(String show_date) {
        this.show_date = show_date;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getAge_restriction() {
        return age_restriction;
    }

    public void setAge_restriction(String age_restriction) {
        this.age_restriction = age_restriction;
    }

    public String getAudio_type() {
        return audio_type;
    }

    public void setAudio_type(String audio_type) {
        this.audio_type = audio_type;
    }

    public String getVideo_type() {
        return video_type;
    }

    public void setVideo_type(String video_type) {
        this.video_type = video_type;
    }
}
