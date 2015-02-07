/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.xml.hth.db.lib.converter;

import com.fpt.xml.hth.db.lib.entities.TheaterDB;
import com.fpt.xml.hth.db.lib.DTO.TheaterSessionDTO;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thu Hoa
 */
public class TheaterSessionConverter implements IModelConverter<TheaterSessionDTO> {

    private final TheaterConverter conveter;

    public TheaterSessionConverter() {
        conveter = new TheaterConverter();
    }

    /**
     * To convert from BasicDBObject object to TheaterDBSessionDTO object
     *
     * @param object
     * @return TheaterDBSessionDTO
     */
    public TheaterSessionDTO convertBasicObjectToModel(BasicDBObject object) {
        String cinemaName = object.getString("cinemaName");
        String id = object.getString("id");
        BasicDBObject basicTheater = (BasicDBObject) object.get("theater");
        BasicDBList basicSessions = (BasicDBList) object.get("sessions");
        // convert basicTheater to theater
        TheaterDB theater = conveter.convertBasicObjectToModel(basicTheater);
        // convert basicSessions to session
        List<String> sessions = new ArrayList<String>();
        for (int i = 0; i < basicSessions.size(); i++) {
            String session = basicSessions.get(i).toString();
            sessions.add(session);
        }
        TheaterSessionDTO theaterSessionDTO = new TheaterSessionDTO();
        theaterSessionDTO.setId(id);
        theaterSessionDTO.setCinemaName(cinemaName);
        theaterSessionDTO.setLstSession(sessions);
        theaterSessionDTO.setTheater(theater);
        return theaterSessionDTO;
    }

    /**
     * To convert from TheaterDBSessionDTO object to BasicDBObject object
     *
     * @param model
     * @return BasicDBObject
     */
    public BasicDBObject convertModelToBasicObject(TheaterSessionDTO model) {
        String cinemaName = model.getCinemaName();
        String id = model.getId();
        TheaterDB theater = model.getTheater();
        List<String> lstSessions = model.getLstSession();
        // convert theather to basicTheater
        BasicDBObject basicTheater = conveter.convertModelToBasicObject(theater);
        // convert lstSessions to 
        BasicDBList basicLst = new BasicDBList();
        for (int i = 0; i < lstSessions.size(); i++) {
            basicLst.add(lstSessions.get(i));
        }
        // convert object theater
        BasicDBObject basicTheaterSession = new BasicDBObject();
        basicTheaterSession.append("cinemaName", cinemaName);
        basicTheaterSession.append("theater", basicTheater);
        basicTheaterSession.append("sessions", basicLst);
        basicTheaterSession.append("id", id);
        return basicTheaterSession;
    }

}
