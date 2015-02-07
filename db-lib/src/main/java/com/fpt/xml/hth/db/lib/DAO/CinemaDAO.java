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
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
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
            MongoCredential credential = MongoCredential.createMongoCRCredential
        (Config.USER_NAME, Config.DATABASE_NAME, Config.PASS_WORD.toCharArray());
            ServerAddress address = new ServerAddress(Config.getHost(), Config.getPort());
            List<MongoCredential> lst = new ArrayList<MongoCredential>();
            lst.add(credential);
            this.mongoClient = new MongoClient(address, lst);
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

    /**
     * select all city in database
     *
     * @return List<String>
     */
    public List<String> getCities() {
        connection();
        Set<String> lst = new HashSet<String>();
        List<BasicDBObject> collection = cinemaCollection.distinct("theaters");
        for (int i = 0; i < collection.size(); i++) {
            BasicDBObject basic = collection.get(i);
            String city = basic.getString("city");
            lst.add(city);
        }
        List<String> lstCity = new ArrayList<String>();
        Iterator<String> iter = lst.iterator();
        while (iter.hasNext()) {
            lstCity.add(iter.next());
        }
        mongoClient.close();
        return lstCity;
    }
}
