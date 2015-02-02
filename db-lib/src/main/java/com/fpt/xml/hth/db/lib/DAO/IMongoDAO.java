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
 */
public interface IMongoDAO<T> {

    //CREATE
    void insert(T object);

    //READ
    T readItem(DBObject object);

    //UPDATE
    void update(T oldObj, T newObj);

    //DELETE
    void delete(T object);

    //SELECT by ID
    T findDocumentById(String _id);

    //SELECT ALL
    DBCollection getDBCollection();
}
