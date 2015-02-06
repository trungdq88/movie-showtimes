/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.xml.hth.db.lib;

/**
 *
 * @author Thu Hoa
 */
public class Config {

    public static  String USER_NAME = "admin";
    public static final String PASS_WORD = "dFhW3D78NpS4";
 //   public static final String DATABASE_NAME = "jbossews";
     public static final String DATABASE_NAME = "MOVIE";
    public static final String CINEMA_COLLECTION = "Cinema";
    public static final String MOVIE_COLLECTION = "Movie";

    public static String getHost() {
      String host = System.getenv("OPENSHIFT_MONGODB_DB_HOST");
      if (host == null || host.isEmpty()) {
          host = "localhost";
      }
        return host;
    }

    public static int getPort() {
     //   String port =  System.getenv("OPENSHIFT_MONGODB_DB_PORT");
      //  return Integer.parseInt(port);
        return 27017;
    }
}
