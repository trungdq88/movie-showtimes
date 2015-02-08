package com.fpt.xml.hth.db.lib;

import com.fpt.xml.hth.db.lib.DAO.MovieDAO;
import com.fpt.xml.hth.db.lib.DTO.MovieTheaterSessionDTO;
import com.fpt.xml.hth.db.lib.resource.Movie;
import com.fpt.xml.hth.db.lib.transfer.TransferdEntities;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        //test select
        //CinemaDAO dao = new CinemaDAO();
//        DBCursor cursor = dao.getDBCollection().find();
//        while (cursor.hasNext()) {
//            DBObject obj = cursor.next();
//            CinemaDTO model = dao.readItem(obj);
//            System.out.println("Model: " + model.getName());
//            System.out.println("theater: " + model.getLstTheater().get(0).getDescription());
//        }
//        List<CinemaDTO> lst = dao.getAll();
//        if (lst.size() >0 ) {
//            System.out.println("success");
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
        //test select MovieDB
//            MovieDAO dao = new MovieDAO();
//           List<MovieTheaterSessionDTO> lst = dao.getAll();
//            System.out.println("Model: tiếng việt" + lst.get(0).getMovie().getName());
//            //test insert movie
//            MovieDAO dao = new MovieDAO();
//            DBCursor cursor = dao.getDBCollection().find();
//            DBObject obj = cursor.next();
//            MovieTheaterSessionDTO model = dao.readItem(obj);
//            MovieTheaterSessionDTO model1 = new MovieTheaterSessionDTO(model);
//            model1.setId(null);
//            dao.insert(model1);
//            System.out.println("insert");
        //test update MovieDB
//        MovieDAO dao = new MovieDAO();
//        DBCursor cursor = dao.getDBCollection().find();
//        DBObject obj = cursor.next();
//        MovieTheaterSessionDTO model = dao.readItem(obj);
//        MovieTheaterSessionDTO newObj = new MovieTheaterSessionDTO(model);
//        newObj.getMovie().setName("THUHOA");
//        dao.update(model, newObj);
        //test delete MovieDB
//        MovieDAO dao = new MovieDAO();
//        DBCursor cursor = dao.getDBCollection().find();
//        DBObject obj = cursor.next();
//        MovieTheaterSessionDTO model = dao.readItem(obj);
//        dao.delete(model);
//        MongoClient mongoClient;
//        DB cinemaDB;
//        DBCollection movieCollection;
//        try {
//            mongoClient = new MongoClient("localhost", 27017);
//            cinemaDB = mongoClient.getDB("MOVIE");
//            movieCollection = cinemaDB.getCollection("Cinema");
//            if (movieCollection != null) {
//                System.out.println("success");
//            }
//        } catch (UnknownHostException ex) {
//            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
//        }
        // test select City
//        CinemaDAO dao = new CinemaDAO();
//        Set<String> lst = dao.getCities();
//        Iterator<String> iter = lst.iterator();
//        while (iter.hasNext()) {
//            System.out.println("cities:" + iter.next());
//        }
        //test select MovieDB
        MovieDAO dao = new MovieDAO();
        String city = "Hai Phòng";
        List<MovieTheaterSessionDTO> lst = dao.getAllByCity(city);
        TransferdEntities entitiy = new TransferdEntities();
        for (MovieTheaterSessionDTO dto : lst) {
            Movie movie = entitiy.transferFromDBEntitiesToGeneratedEntities(dto);
            System.out.println(movie.getName());
        }
//        System.out.println("end1");
//        for (MovieTheaterSessionDTO dto : lst) {
//            System.out.println("end2");
//            System.out.println(dto.getTheaters().get(0).getTheater().getCity());
//            System.out.println("end3");
////            System.out.println(dto.getTheaters().get(1).getTheater().getCity());
////            System.out.println(dto.getTheaters().get(2).getTheater().getCity());
//        }
    }
}
