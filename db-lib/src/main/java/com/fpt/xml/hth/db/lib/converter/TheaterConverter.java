/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.xml.hth.db.lib.converter;

import com.fpt.xml.hth.db.lib.entities.Theater;
import com.mongodb.BasicDBObject;

/**
 *
 * @author Thu Hoa
 */
public class TheaterConverter implements IModelConverter<Theater> {

    /**
     * To convert from BasicDBObject object to Theater object
     *
     * @param object
     * @return Theater
     */
    public Theater convertBasicObjectToModel(BasicDBObject object) {
        // mongod not generated ObjectID for Theater
        // ObjectId id = object.getObjectId("_id");
        String name = object.getString("name");
        String address = object.getString("address");
        String description = object.getString("description");
        String city = object.getString("city");
        String map_link = object.getString("map_link");
        String image = object.getString("image");
        //set values for theater
        Theater theater = new Theater();
        // theater.setId(id);
        theater.setName(name);
        theater.setAddress(address);
        theater.setDescription(description);
        theater.setCity(city);
        theater.setMap_link(map_link);
        theater.setImage(image);
        return theater;
    }

    /**
     * To convert from Theater object to BasicDBObject object
     *
     * @param model
     * @return BasicDBObject
     */
    public BasicDBObject convertModelToBasicObject(Theater model) {
        // mongod not generated ObjectID for Theater
//        ObjectId id = model.getId();
        String name = model.getName();
        String address = model.getAddress();
        String description = model.getDescription();
        String city = model.getCity();
        String map_link = model.getMap_link();
        String image = model.getImage();
        // set value for basic object
        BasicDBObject object = new BasicDBObject();
        // object.append("_id", id);
        object.append("name", name);
        object.append("address", address);
        object.append("description", description);
        object.append("city", city);
        object.append("map_link", map_link);
        object.append("image", image);
        return object;
    }
}
