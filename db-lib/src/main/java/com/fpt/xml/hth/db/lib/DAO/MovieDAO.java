/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.xml.hth.db.lib.DAO;

import com.fpt.xml.hth.db.lib.Config;
import com.fpt.xml.hth.db.lib.DTO.MovieTheaterSessionDTO;
import com.fpt.xml.hth.db.lib.DTO.TheaterSessionDTO;
import com.fpt.xml.hth.db.lib.converter.MovieTheaterSessionConverter;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
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

    }

    private void connection() {
        try {
            MongoCredential credential = MongoCredential.createMongoCRCredential(Config.USER_NAME, Config.DATABASE_NAME, Config.PASS_WORD.toCharArray());
            ServerAddress address = new ServerAddress(Config.getHost(), Config.getPort());
            List<MongoCredential> lst = new ArrayList<MongoCredential>();
            lst.add(credential);
            this.mongoClient = new MongoClient(address, lst);
            this.cinemaDB = mongoClient.getDB(Config.DATABASE_NAME);
            this.movieCollection = cinemaDB.getCollection(Config.MOVIE_COLLECTION);
            this.converter = new MovieTheaterSessionConverter();
        } catch (UnknownHostException ex) {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insert(MovieTheaterSessionDTO object) {
        connection();
        BasicDBObject obj = converter.convertModelToBasicObject(object);
        this.movieCollection.insert(obj);
        mongoClient.close();
    }

    public MovieTheaterSessionDTO readItem(DBObject object) {
        return converter.convertBasicObjectToModel((BasicDBObject) object);
    }

    public void update(MovieTheaterSessionDTO oldObj, MovieTheaterSessionDTO newObj) {
        connection();
        BasicDBObject oldBasicObject = new BasicDBObject("_id", oldObj.getId());
        BasicDBObject newObject = converter.convertModelToBasicObject(newObj);
        BasicDBObject query = new BasicDBObject().append("$set", newObject);
        movieCollection.update(oldBasicObject, query, true, false);
        mongoClient.close();
    }

    public void delete(MovieTheaterSessionDTO object) {
        connection();
        BasicDBObject query = new BasicDBObject("_id", new ObjectId(object.getId().toString()));
        movieCollection.remove(query);
        mongoClient.close();
    }

    public MovieTheaterSessionDTO findDocumentById(String _id) {
        connection();
        BasicDBObject obj = (BasicDBObject) movieCollection.find(new BasicDBObject("_id", new ObjectId(_id))).next();
        mongoClient.close();
        return converter.convertBasicObjectToModel(obj);
    }

    public DBCollection getDBCollection() {
        connection();
        DBCollection collection = this.movieCollection;
        mongoClient.close();
        return collection;
    }

    public List<MovieTheaterSessionDTO> getAll() {
        connection();
        List<MovieTheaterSessionDTO> lst = new ArrayList<MovieTheaterSessionDTO>();
        DBCollection collection = movieCollection;
        DBCursor cursor = collection.find();
        while (cursor.hasNext()) {
            BasicDBObject basic = (BasicDBObject) cursor.next();
            MovieTheaterSessionDTO movieDto = converter.convertBasicObjectToModel(basic);
            lst.add(movieDto);
        }
        cursor.close();
        mongoClient.close();
        return lst;
    }

    /**
     * get list movies by param city
     *
     * @param city
     * @return List<MovieTheaterSessionDTO>
     */
    public List<MovieTheaterSessionDTO> getAllByCity(String city) {
        connection();
        List<MovieTheaterSessionDTO> lst = new ArrayList<MovieTheaterSessionDTO>();
        DBCollection collection = movieCollection;

//        BasicDBObject dbObject = new BasicDBObject();
//        dbObject.append("theaters", new BasicDBObject(
//                "$elemMatch", new BasicDBObject(
//                        "theater.city", city
//                )));

        DBCursor cursor = collection.find();

        while (cursor.hasNext()) {
            BasicDBObject basic = (BasicDBObject) cursor.next();
            MovieTheaterSessionDTO movieDto = converter.convertBasicObjectToModel(basic);
            List<TheaterSessionDTO> lstTheaterSession = new ArrayList<TheaterSessionDTO>();
            if (city != null && !city.isEmpty()) {
                // check name of city
                for (TheaterSessionDTO theaterDTO : movieDto.getTheaters()) {
                    if (theaterDTO.getTheater().getCity().equals(city)) {
                        lstTheaterSession.add(theaterDTO);
                        break;
                    }
                }
            }
            movieDto.setTheaters(lstTheaterSession);
            lst.add(movieDto);
        }
        cursor.close();
        mongoClient.close();
        return lst;
    }

    public boolean dropCollection() {
        try {
            connection();
            movieCollection.drop();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            mongoClient.close();
        }

    }
}
