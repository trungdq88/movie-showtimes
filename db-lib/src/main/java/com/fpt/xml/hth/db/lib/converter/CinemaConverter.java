/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.xml.hth.db.lib.converter;

import com.fpt.xml.hth.db.lib.DTO.CinemaDTO;
import com.fpt.xml.hth.db.lib.entities.Theater;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Thu Hoa
 */
public class CinemaConverter implements IModelConverter<CinemaDTO> {

    /**
     * To convert from BasicDBObject object to Cinema object
     *
     * @param object
     * @return Cinema
     */
    public CinemaDTO convertBasicObjectToModel(BasicDBObject object) {
        // get name
        String name = object.getString("name");
        // get website_link
        String website_link = object.getString("website_link");
        // get theater
        BasicDBList lst = (BasicDBList) object.get("theaters");
        //convert theather
        List<Theater> lstTheaters = new ArrayList<Theater>();
        for (int i = 0; i < lst.size(); i++) {
            BasicDBObject obj = (BasicDBObject) lst.get(i);
            TheaterConverter converter = new TheaterConverter();
            Theater theater = converter.convertBasicObjectToModel(obj);
            lstTheaters.add(theater);
        }
        // set values for object cinema
        CinemaDTO cinema = new CinemaDTO();
        cinema.setId(object.getObjectId("_id"));
        cinema.setName(name);
        cinema.setWebsite_link(website_link);
        cinema.setLstTheater(lstTheaters);
        return cinema;
    }

    /**
     * To convert from Cinema object to BasicDBObject object
     *
     * @param model
     * @return BasicDBObject
     */
    public BasicDBObject convertModelToBasicObject(CinemaDTO model) {
        ObjectId id = model.getId();
        String name = model.getName();
        System.out.println("Convert name: " + name);
        String website_link = model.getWebsite_link();
        List<Theater> lstTheater = model.getLstTheater();
        List<BasicDBObject> lstTheaterBasic = new ArrayList<BasicDBObject>();
        //convert each theater
        for (int i = 0; i < lstTheater.size(); i++) {
            Theater theater = lstTheater.get(i);
            TheaterConverter converter = new TheaterConverter();
            BasicDBObject obj = converter.convertModelToBasicObject(theater);
            lstTheaterBasic.add(obj);
        }
        // set values for basic object cinema
        BasicDBObject cinemaBasic = new BasicDBObject();
        cinemaBasic.put("_id", id);
        cinemaBasic.put("name", name);
        cinemaBasic.put("website_link", website_link);
        cinemaBasic.put("theaters", lstTheaterBasic);
        return cinemaBasic;
    }
}
