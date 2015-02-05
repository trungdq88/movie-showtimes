package com.fpt.xml.hth.web.client;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Created by dinhquangtrung on 2/1/15.
 */
@WebServlet(name = "HomeServlet")
public class HomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext servletContext = getServletContext();
        String path = servletContext.getRealPath("/WEB-INF/") + "/data.xml";

        File f = new File(path);
        if(!f.exists() || f.isDirectory()) {
            System.out.println("Start file: " + path);
            NetworkUtils net = new NetworkUtils();
            String xml = net.sendGetRequest("http://jbossews-trungdq88.rhcloud.com/API/getMovies?city=");
            Files.write(Paths.get(path), xml.getBytes(), StandardOpenOption.CREATE);
        }
        
        System.out.println("File should be existed: " + path);
        request.getRequestDispatcher("WEB-INF/movie.jsp").forward(request, response);
    }
}