/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.xml.hth.db.lib.DAO;

import com.fpt.xml.hth.db.lib.DTO.MovieTheaterSessionDTO;
import com.fpt.xml.hth.db.lib.converter.MovieTheaterSessionConverter;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.types.ObjectId;

/**
 *
 * @author Thu Hoa
 */
public class MovieDAO implements IMongoDAO<MovieTheaterSessionDTO> {

    private MongoClient mongoClient;
    private DB cinemaDB;
    private DBCollection movieCollection;
    private MovieTheaterSessionConverter converter;

    public MovieDAO() {
        try {
            this.mongoClient = new MongoClient("localhost", 27017);
            this.cinemaDB = mongoClient.getDB("MOVIE");
            this.movieCollection = cinemaDB.getCollection("Movie");
            this.converter = new MovieTheaterSessionConverter();
        } catch (UnknownHostException ex) {
            Logger.getLogger(CinemaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void insert(MovieTheaterSessionDTO object) {
        BasicDBObject obj = converter.convertModelToBasicObject(object);
        this.movieCollection.insert(obj);
    }

    public MovieTheaterSessionDTO readItem(DBObject object) {
        return converter.convertBasicObjectToModel((BasicDBObject) object);
    }

    public void update(MovieTheaterSessionDTO oldObj, MovieTheaterSessionDTO newObj) {
        BasicDBObject oldBasicObject = new BasicDBObject("_id", oldObj.getId());
        BasicDBObject newObject = converter.convertModelToBasicObject(newObj);
        BasicDBObject query = new BasicDBObject().append("$set", newObject);
        movieCollection.update(oldBasicObject, query, true, false);
    }

    public void delete(MovieTheaterSessionDTO object) {
        BasicDBObject query = new BasicDBObject("_id", new ObjectId(object.getId().toString()));
        movieCollection.remove(query);
    }

    public MovieTheaterSessionDTO findDocumentById(String _id) {
        BasicDBObject obj = (BasicDBObject) movieCollection.find(new BasicDBObject("_id", new ObjectId(_id))).next();
        return converter.convertBasicObjectToModel(obj);
    }

    public DBCollection getDBCollection() {
        return movieCollection;
    }
   public List<MovieTheaterSessionDTO> getAll(){
        List<MovieTheaterSessionDTO> lst = new ArrayList<MovieTheaterSessionDTO>();
        DBCollection collection = this.getDBCollection();
        DBCursor cursor = collection.find();
        while (cursor.hasNext()){
            MovieTheaterSessionDTO dto = (MovieTheaterSessionDTO) cursor.next();
            lst.add(dto);
        }
        return lst;
    }
}
