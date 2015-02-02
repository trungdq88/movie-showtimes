/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.xml.hth.db.lib.converter;

import com.fpt.xml.hth.db.lib.entities.Movie;
import com.mongodb.BasicDBObject;
import org.bson.types.ObjectId;

/**
 *
 * @author Thu Hoa
 */
public class MovieConverter implements IModelConverter<Movie> {

    public Movie convertBasicObjectToModel(BasicDBObject object) {
        String name = object.getString("name");
        String description = object.getString("description");
        String length = object.getString("length");
        String actor = object.getString("actor");
        String director = object.getString("director");
        String poster = object.getString("poster");
        String trailer = object.getString("trailer");
        String show_date = object.getString("show_date");
        String genre = object.getString("genre");
        String age_restriction = object.getString("age_restriction");
        String audio_type = object.getString("audio_type");
        String video_type = object.getString("video_type");
        // set values to object movie
        Movie movie = new Movie();
        movie.setName(name);
        movie.setActor(actor);
        movie.setAge_restriction(age_restriction);
        movie.setAudio_type(audio_type);
        movie.setDescription(description);
        movie.setDirector(director);
        movie.setGenre(genre);
        movie.setLength(length);
        movie.setName(name);
        movie.setPoster(poster);
        movie.setShow_date(show_date);
        movie.setTrailer(trailer);
        movie.setVideo_type(video_type);
        return movie;
    }

    public BasicDBObject convertModelToBasicObject(Movie model) {
        String name = model.getName();
        String description = model.getDescription();
        String length = model.getLength();
        String actor = model.getActor();
        String director = model.getDirector();
        String poster = model.getPoster();
        String trailer = model.getTrailer();
        String show_date = model.getShow_date();
        String genre = model.getGenre();
        String age_restriction = model.getAge_restriction();
        String audio_type = model.getAudio_type();
        String video_type = model.getVideo_type();
        //set value for basicObj
        BasicDBObject basicObj = new BasicDBObject();
        basicObj.append("name", name);
        basicObj.append("description", description);
        basicObj.append("length", length);
        basicObj.append("actor", actor);
        basicObj.append("director", director);
        basicObj.append("poster", poster);
        basicObj.append("trailer", trailer);
        basicObj.append("show_date", show_date);
        basicObj.append("genre", genre);
        basicObj.append("age_restriction", age_restriction);
        basicObj.append("audio_type", audio_type);
        basicObj.append("video_type", video_type);
        return basicObj;

    }

}
