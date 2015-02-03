/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.xml.hth.db.lib.DAO;

import com.fpt.xml.hth.db.lib.Config;
import com.fpt.xml.hth.db.lib.DTO.CinemaDTO;
import com.fpt.xml.hth.db.lib.converter.CinemaConverter;
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
public class CinemaDAO implements IMongoDAO<CinemaDTO> {

    private MongoClient mongoClient;
    private DB cinemaDB;
    private DBCollection cinemaCollection;
    private CinemaConverter converter;

    public CinemaDAO() {
    }

    private void connection() {
        try {
            this.mongoClient = new MongoClient(Config.DATABASE, Config.PORT);
            this.cinemaDB = mongoClient.getDB(Config.DATABASE_NAME);
            this.cinemaCollection = cinemaDB.getCollection(Config.CINEMA_COLLECTION);
            this.converter = new CinemaConverter();
        } catch (UnknownHostException ex) {
            Logger.getLogger(CinemaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insert(CinemaDTO object) {
        connection();
        BasicDBObject obj = converter.convertModelToBasicObject(object);
        this.cinemaCollection.insert(obj);
        mongoClient.close();
    }

    public CinemaDTO readItem(DBObject object) {
        return converter.convertBasicObjectToModel((BasicDBObject) object);
    }

    public void update(CinemaDTO oldObj, CinemaDTO newObj) {
        connection();
        BasicDBObject oldBasicObject = new BasicDBObject("_id", oldObj.getId());
        BasicDBObject newBasicObj = converter.convertModelToBasicObject(newObj);
        BasicDBObject query = new BasicDBObject().append("$set", newBasicObj.append("_id", newBasicObj.get("_id")));
        cinemaCollection.update(oldBasicObject, query, true, false);
        mongoClient.close();

    }

    public void delete(CinemaDTO object) {
        connection();
        BasicDBObject query = new BasicDBObject("_id", new ObjectId(object.getId().toString()));
        cinemaCollection.remove(query);
        mongoClient.close();
    }

    public CinemaDTO findDocumentById(String _id) {
        connection();
        BasicDBObject obj = (BasicDBObject) cinemaCollection.find(new BasicDBObject("_id", new ObjectId(_id))).next();
        mongoClient.close();
        return converter.convertBasicObjectToModel(obj);
    }

    public DBCollection getDBCollection() {
        DBCollection collection = this.cinemaCollection;
        return collection;
    }

    public List<CinemaDTO> getAll() {
        connection();
        List<CinemaDTO> lst = new ArrayList<CinemaDTO>();
        DBCollection collection = this.cinemaCollection;
        DBCursor cursor = collection.find();
        while (cursor.hasNext()) {
            BasicDBObject dto = (BasicDBObject) cursor.next();
            CinemaDTO cinemaDTO = converter.convertBasicObjectToModel(dto);
            lst.add(cinemaDTO);
        }
        cursor.close();
        mongoClient.close();
        return lst;
    }
}
