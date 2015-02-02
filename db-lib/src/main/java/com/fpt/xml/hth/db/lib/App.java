package com.fpt.xml.hth.db.lib;

import com.fpt.xml.hth.db.lib.DAO.MovieDAO;
import com.fpt.xml.hth.db.lib.DTO.MovieTheaterSessionDTO;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        //test select
//        CinemaDAO dao = new CinemaDAO();
//        DBCursor cursor = dao.getDBCollection().find();
//        while (cursor.hasNext()) {
//            DBObject obj = cursor.next();
//            Cinema model = dao.readItem(obj);
//            System.out.println("Model: " + model.getName());
//            System.out.println("theater: " + model.getLstTheater().get(0).getDescription());
//        }
//        //test insert
//         CinemaDAO dao = new CinemaDAO();
//        DBCursor cursor = dao.getDBCollection().find();
//            DBObject obj = cursor.next();
//            Cinema model = dao.readItem(obj);
//            Cinema insertObj = new Cinema();
//            insertObj.setLstTheater(model.getLstTheater());
//            insertObj.setName(model.getName());
//            insertObj.setWebsite_link(model.getWebsite_link());
//            dao.insert(insertObj);
//            System.out.println("insert");
        //test update
//        CinemaDAO dao = new CinemaDAO();
//        DBCursor cursor = dao.getDBCollection().find();
//        DBObject obj = cursor.next();
//        CinemaDTO oldCinema = dao.readItem(obj);
//        CinemaDTO newCinema = new CinemaDTO(oldCinema);
//        newCinema.setName("THUHOA");
//        newCinema.getLstTheater().get(0).setName("THUHOA");
//        dao.update(oldCinema, newCinema);
//        System.out.println("update");
        // test delete
//        CinemaDAO dao = new CinemaDAO();
//        DBCursor cursor = dao.getDBCollection().find();
//        DBObject obj = cursor.next();
//        CinemaDTO model = dao.readItem(obj);
//        dao.delete(model);
//         System.out.println("delete");
        //test select Movie
            MovieDAO dao = new MovieDAO();
            DBCursor cursor = dao.getDBCollection().find();
            DBObject obj = cursor.next();
            MovieTheaterSessionDTO model = dao.readItem(obj);
            System.out.println("Model: tiếng việt" + model.getMovie().getName());
            System.out.println("theater: " + model.getTheaters().get(0).getTheater().getName());
//            //test insert movie
//            MovieDAO dao = new MovieDAO();
//            DBCursor cursor = dao.getDBCollection().find();
//            DBObject obj = cursor.next();
//            MovieTheaterSessionDTO model = dao.readItem(obj);
//            MovieTheaterSessionDTO model1 = new MovieTheaterSessionDTO(model);
//            model1.setId(null);
//            dao.insert(model1);
//            System.out.println("insert");
      //test update Movie
//        MovieDAO dao = new MovieDAO();
//        DBCursor cursor = dao.getDBCollection().find();
//        DBObject obj = cursor.next();
//        MovieTheaterSessionDTO model = dao.readItem(obj);
//        MovieTheaterSessionDTO newObj = new MovieTheaterSessionDTO(model);
//        newObj.getMovie().setName("THUHOA");
//        dao.update(model, newObj);
           //test delete Movie
//        MovieDAO dao = new MovieDAO();
//        DBCursor cursor = dao.getDBCollection().find();
//        DBObject obj = cursor.next();
//        MovieTheaterSessionDTO model = dao.readItem(obj);
//        dao.delete(model);

    }
}
