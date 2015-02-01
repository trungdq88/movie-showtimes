package com.fpt.xml.hth.web.client;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.LinkOption;
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
            NetworkUtils net = new NetworkUtils();
            String xml = net.sendGetRequest("http://jbossews-trungdq88.rhcloud.com/API/APIServlet");
            Files.write(Paths.get(path), xml.getBytes(), StandardOpenOption.CREATE);
            System.out.println("Write file: " + path);
        }



        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}