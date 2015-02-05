/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.xml.hth.db.lib.transfer;

import com.fpt.xml.hth.db.lib.DTO.MovieTheaterSessionDTO;
import com.fpt.xml.hth.db.lib.DTO.TheaterSessionDTO;
import com.fpt.xml.hth.db.lib.entities.MovieDB;
import com.fpt.xml.hth.db.lib.entities.TheaterDB;
import com.fpt.xml.hth.db.lib.resource.Movie;
import com.fpt.xml.hth.db.lib.resource.Movie.Sessions;
import com.fpt.xml.hth.db.lib.resource.Session;
import com.fpt.xml.hth.db.lib.resource.Theater;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author Thu Hoa
 */
public class TransferdEntities {

    /**
     * this function transfer from Entities that generated from xsd file to
     * Entities that mapping with DB
     *
     * @param dto
     * @return movies
     */
    public Movie transferFromDBEntitiesToGeneratedEntities(MovieTheaterSessionDTO dto) {
        Movie movie = new Movie();
        MovieDB movieDB = dto.getMovie();
        //1.Set value for movie
        movie.setActor(movieDB.getActor());
        movie.setAgeRestriction(movieDB.getAge_restriction());
        movie.setAudioType(movieDB.getAudio_type());
        movie.setDescription(movieDB.getDescription());
        movie.setDirector(movieDB.getDirector());
        movie.setGenre(movieDB.getGenre());
        movie.setId(dto.getId().toString());
        movie.setLength(movieDB.getLength());
        movie.setName(movieDB.getName());
        movie.setPoster(movieDB.getPoster());
        movie.setTrailer(movieDB.getTrailer());
        movie.setVideoType(movieDB.getVideo_type());        //1.get all theater in dto
        List<TheaterSessionDTO> lstTheaterSession = dto.getTheaters();
        //2.create sessions
        Sessions sessions = new Movie.Sessions();
        for (int i = 0; i < lstTheaterSession.size(); i++) {
            TheaterSessionDTO dtoTS = lstTheaterSession.get(i);
            List<String> sessionsString = dtoTS.getLstSession();
            TheaterDB theaterDB = dtoTS.getTheater();
            // parse each dto to movies (number of movies depend on number of session in dto)
            for (int j = 0; j < sessionsString.size(); j++) {
                //set value for theater
                Theater theater = new Theater();
                // use id of theatersession to set for id of theater
                theater.setId(dtoTS.getId());
                theater.setAddress(theaterDB.getAddress());
                theater.setCity(theaterDB.getCity());
                theater.setDescription(theaterDB.getImage());
                theater.setImage(theaterDB.getImage());
                theater.setMapLink(theaterDB.getMap_link());
                theater.setName(theaterDB.getName());
                // get value for session
                Session session = new Session();
                session.setTheater(theater);
                session.setMovie(movieDB.getName());
                BigInteger showtime = BigInteger.ZERO;
                try {
                    showtime = new BigInteger(sessionsString.get(i));
                } catch (NumberFormatException e) {
                    e.getMessage();
                }
                session.setShowTime(showtime);
                sessions.getSession().add(session);
            }
        }
        movie.setSessions(sessions);
        return movie;
    }
}
