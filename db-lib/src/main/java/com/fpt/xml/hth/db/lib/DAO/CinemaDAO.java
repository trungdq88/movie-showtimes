/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.xml.hth.db.lib.DAO;

import com.fpt.xml.hth.db.lib.DTO.CinemaDTO;
import com.fpt.xml.hth.db.lib.converter.CinemaConverter;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.net.UnknownHostException;
import java.util.AbstractList;
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
        try {
            this.mongoClient = new MongoClient("localhost", 27017);
            this.cinemaDB = mongoClient.getDB("MOVIE");
            this.cinemaCollection = cinemaDB.getCollection("Cinema");
            this.converter = new CinemaConverter();
        } catch (UnknownHostException ex) {
            Logger.getLogger(CinemaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void insert(CinemaDTO object) {
        BasicDBObject obj = converter.convertModelToBasicObject(object);
        this.cinemaCollection.insert(obj);
    }
    
    public CinemaDTO readItem(DBObject object) {
        return converter.convertBasicObjectToModel((BasicDBObject) object);
    }
    
    public void update(CinemaDTO oldObj, CinemaDTO newObj) {
        BasicDBObject oldBasicObject = new BasicDBObject("_id", oldObj.getId());
        BasicDBObject newBasicObj = converter.convertModelToBasicObject(newObj);
        BasicDBObject query = new BasicDBObject().append("$set", newBasicObj.append("_id", newBasicObj.get("_id")));
        cinemaCollection.update(oldBasicObject, query, true, false);
        
    }
    
    public void delete(CinemaDTO object) {
        BasicDBObject query = new BasicDBObject("_id", new ObjectId(object.getId().toString()));
        cinemaCollection.remove(query);
    }
    
    public CinemaDTO findDocumentById(String _id) {
        BasicDBObject obj = (BasicDBObject) cinemaCollection.find(new BasicDBObject("_id", new ObjectId(_id))).next();
        return converter.convertBasicObjectToModel(obj);
    }
    
    public DBCollection getDBCollection() {
        return cinemaCollection;
    }
    
    public List<CinemaDTO> getAll(){
        List<CinemaDTO> lst = new ArrayList<CinemaDTO>();
        DBCollection collection = this.getDBCollection();
        DBCursor cursor = collection.find();
        while (cursor.hasNext()){
            CinemaDTO dto = (CinemaDTO) cursor.next();
            lst.add(dto);
        }
        return lst;
    }
}
