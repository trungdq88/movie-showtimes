package com.fpt.xml.hth.web.client;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.Cookie;

/**
 * Created by dinhquangtrung on 2/1/15.
 */
@WebServlet(name = "HomeServlet")
public class HomeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean ignoreValidate = true;

        Map cookieMap = new HashMap();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie.getValue());
            }
        }
        String city = (String) cookieMap.get("city");
        if (city == null || city.isEmpty()) {
            city = "";
        }

        String paramCity = request.getParameter("city");
        if (paramCity != null) {
            // Create cookies for first and last names.      
            Cookie cookie = new Cookie("city", URLEncoder.encode(paramCity, "UTF-8"));

            // Set expiry date after 6 months for the cookies.
            cookie.setMaxAge(6 * 30 * 60 * 60 * 24);

            // Add both the cookies in the response header.
            response.addCookie(cookie);
            city = paramCity;
        }

        ServletContext servletContext = getServletContext();
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        String today = dt1.format(d);
        String path = EnvUtils.getDataPath(servletContext) + "/data-" + today + ".xml";
        String xsdPath = EnvUtils.getDataPath(servletContext) + "/schema.xsd";

        String xsdUrl = "http://jbossews-trungdq88.rhcloud.com/API/APISchema.xsd";
        String xmlUrl = "http://jbossews-trungdq88.rhcloud.com/API/getMovies?city=" + city;

        File f = new File(path);
        if (!f.exists() || f.isDirectory()) {
            System.out.println("Start file: " + path);
            NetworkUtils net = new NetworkUtils();
            String xml = net.sendGetRequest(xmlUrl);
            Files.write(Paths.get(path), xml.getBytes(), StandardOpenOption.CREATE);
            String xsd = net.sendGetRequest(xsdUrl);
            Files.write(Paths.get(xsdPath), xsd.getBytes(), StandardOpenOption.CREATE);
        }

        System.out.println("File should be existed: " + path);
        // Validate xml
        String valid = Validator.validate(path, xsdPath);
        System.out.println("XML validation: " + valid);

        if (valid.equals("true") || ignoreValidate) {
            String xml = FileUtils.readFile(path);
            request.setAttribute("xml", xml);
            request.setAttribute("date", today);
            request.setAttribute("url", xmlUrl);
            request.setAttribute("city", city);
            request.getRequestDispatcher("WEB-INF/movie.jsp").forward(request, response);
        } else {
            PrintWriter out = response.getWriter();
            out.write("XML is invalid!\n" + valid);
        }
    }
}
