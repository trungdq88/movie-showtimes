/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.xml.hth.db.lib.DAO;

import com.mongodb.DBCollection;
import com.mongodb.DBObject;

/**
 *
 * @author Thu Hoa
 * @modifier HaiNNT 08-02-2015
 */
public interface IMongoDAO<T> {

    /**
     * Insert an object into collection
     * @param object 
     */
    void insert(T object);

    /**
     * Select from collection
     * @param object
     * @return 
     */
    T readItem(DBObject object);

    /**
     * Update an object in collection
     * @param oldObj
     * @param newObj 
     */
    void update(T oldObj, T newObj);

    /**
     * Delete an object in collection
     * @param object 
     */
    void delete(T object);

    /**
     * Select from collection by Id
     * @param _id
     * @return 
     */
    T findDocumentById(String _id);

    /**
     * Select all object from collection
     * @return 
     */
    DBCollection getDBCollection();
    
    /**
     * Drop collection
     * @return 
     */
    boolean dropCollection();
}
