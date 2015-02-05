/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.xml.hth.db.lib.DTO;

import com.fpt.xml.hth.db.lib.entities.MovieDB;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 * class MovieDBTheater Session mapping with collection Session in db
 *
 * @author Thu Hoa
 */
// TODO: remove
public class MovieTheaterSessionDTO {

    private ObjectId id;
    private MovieDB movie;
    private List<TheaterSessionDTO> theaters;

    public MovieTheaterSessionDTO() {
        this.theaters = new ArrayList<TheaterSessionDTO>();
    }

    public MovieTheaterSessionDTO(MovieTheaterSessionDTO movie) {
        this.id = movie.getId();
        this.movie = movie.getMovie();
        this.theaters = movie.getTheaters();
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public MovieDB getMovie() {
        return movie;
    }

    public void setMovie(MovieDB movie) {
        this.movie = movie;
    }

    public List<TheaterSessionDTO> getTheaters() {
        return theaters;
    }

    public void setTheaters(List<TheaterSessionDTO> theaters) {
        this.theaters = theaters;
    }
    
    public void addTheater(TheaterSessionDTO theater){
        theaters.add(theater);
    }

}
