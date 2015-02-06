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
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dinhquangtrung on 2/1/15.
 */
@WebServlet(name = "HomeServlet")
public class HomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext servletContext = getServletContext();
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-mm-dd");
        Date d = new Date();
        String today = dt1.format(d);
        String path = EnvUtils.getDataPath(servletContext) + "/data-" + today +".xml";
        String xsdPath = EnvUtils.getDataPath(servletContext) + "/schema.xsd";

        File f = new File(path);
        if(!f.exists() || f.isDirectory()) {
            System.out.println("Start file: " + path);
            NetworkUtils net = new NetworkUtils();
            String xml = net.sendGetRequest("http://jbossews-trungdq88.rhcloud.com/API/getMovies?city=");
            Files.write(Paths.get(path), xml.getBytes(), StandardOpenOption.CREATE);
            String xsd = net.sendGetRequest("http://jbossews-trungdq88.rhcloud.com/API/APISchema.xsd");
            Files.write(Paths.get(xsdPath), xsd.getBytes(), StandardOpenOption.CREATE);
        }
        
        System.out.println("File should be existed: " + path);
        // Validate xml
        String valid = Validator.validate(path, xsdPath);
        System.out.println("XML validation: " + valid);
        
        if (valid.equals("true")) {
            String xml = FileUtils.readFile(path);
            request.setAttribute("xml", xml);
            request.getRequestDispatcher("WEB-INF/movie.jsp").forward(request, response);
        } else {
            PrintWriter out = response.getWriter();
            out.write("XML is invalid!\n" + valid);
        }
    }
}