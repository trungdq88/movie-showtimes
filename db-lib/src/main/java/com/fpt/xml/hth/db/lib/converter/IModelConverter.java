/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.xml.hth.db.lib.converter;

import com.mongodb.BasicDBObject;

/**
 *
 * @author Thu Hoa
 */
public interface IModelConverter<T> {
     /**
     * To convert from BasicDBObject object to <T> object
     *
     * @param object
     * @return T
     */
    public T convertBasicObjectToModel(BasicDBObject object);
     /**
     * To convert from <T> object to BasicDBObject object
     *
     * @param model
     * @return BasicDBObject
     */
    public BasicDBObject convertModelToBasicObject(T model);
}
