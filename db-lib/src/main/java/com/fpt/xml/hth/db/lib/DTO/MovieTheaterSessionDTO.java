/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.xml.hth.db.lib.DTO;

import com.fpt.xml.hth.db.lib.entities.Movie;
import com.fpt.xml.hth.db.lib.entities.Theater;
import java.util.List;
import org.bson.types.ObjectId;

/**
 * class MovieTheater Session mapping with collection Session in db
 *
 * @author Thu Hoa
 */
// TODO: remove
public class MovieTheaterSessionDTO {

    private ObjectId id;
    private Movie movie;
    private List<TheaterSessionDTO> theaters;

    public MovieTheaterSessionDTO() {
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

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public List<TheaterSessionDTO> getTheaters() {
        return theaters;
    }

    public void setTheaters(List<TheaterSessionDTO> theaters) {
        this.theaters = theaters;
    }

}
