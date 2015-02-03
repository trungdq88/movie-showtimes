/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.xml.hth.db.lib.converter;

import com.fpt.xml.hth.db.lib.entities.MovieDB;
import com.fpt.xml.hth.db.lib.DTO.MovieTheaterSessionDTO;
import com.fpt.xml.hth.db.lib.DTO.TheaterSessionDTO;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Thu Hoa
 */
public class MovieTheaterSessionConverter implements IModelConverter<MovieTheaterSessionDTO> {

    private final MovieConverter movieConverter;
    private final TheaterSessionConverter tsConverter;

    public MovieTheaterSessionConverter() {
        this.movieConverter = new MovieConverter();
        this.tsConverter = new TheaterSessionConverter();
    }

    /**
     * To convert from BasicDBObject object to MovieDBTheaterSessionDTO object
     *
     * @param object
     * @return MovieDBTheaterSessionDTO
     */
    //TODO: remove
    public MovieTheaterSessionDTO convertBasicObjectToModel(BasicDBObject object) {
        MovieTheaterSessionDTO dto = new MovieTheaterSessionDTO();
        ObjectId id = object.getObjectId("_id");
        BasicDBObject basicMovie = (BasicDBObject) object.get("movie");
        BasicDBList basicLstSession = (BasicDBList) object.get("theaters");
        //convert basicMovie object movie
        MovieDB movie = movieConverter.convertBasicObjectToModel(basicMovie);
        //convert basicLstSession to theaters
        List<TheaterSessionDTO> theaters = new ArrayList<TheaterSessionDTO>();;
        if (basicLstSession != null && !basicLstSession.isEmpty()) {
            for (int i = 0; i < basicLstSession.size(); i++) {
                BasicDBObject basic = (BasicDBObject) basicLstSession.get(i);
                TheaterSessionDTO theaterSessionDTO = tsConverter.convertBasicObjectToModel(basic);
                theaters.add(theaterSessionDTO);
            }
        }

        //set value for object MovieTheaterSessionDTO
        dto.setId(id);
        dto.setMovie(movie);
        dto.setTheaters(theaters);
        return dto;
    }

    /**
     * To convert from MovieDBTheaterSessionDTO object to BasicDBObject object
     *
     * @param model
     * @return BasicDBObject
     */
    public BasicDBObject convertModelToBasicObject(MovieTheaterSessionDTO model) {
        ObjectId id = model.getId();
        MovieDB movie = model.getMovie();
        List<TheaterSessionDTO> theaters = model.getTheaters();
        //convert movie to BasicDBObject
        BasicDBObject basicMovie = movieConverter.convertModelToBasicObject(movie);
        //convert lstTheaters to BasicDBList
        BasicDBList basicLst = new BasicDBList();
        for (int i = 0; i < theaters.size(); i++) {
            TheaterSessionDTO theaterSessionDTO = (TheaterSessionDTO) theaters.get(i);
            BasicDBObject basic = tsConverter.convertModelToBasicObject(theaterSessionDTO);
            basicLst.add(basic);
        }
        // set value for basicMovieTheaterSession
        BasicDBObject basicMovieTheaterSession = new BasicDBObject();
        basicMovieTheaterSession.append("_id", id);
        basicMovieTheaterSession.append("movie", basicMovie);
        basicMovieTheaterSession.append("theaters", basicLst);

        return basicMovieTheaterSession;
    }

}
