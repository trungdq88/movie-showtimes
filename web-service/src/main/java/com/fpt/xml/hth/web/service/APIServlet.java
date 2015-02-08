/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.xml.hth.web.service;

import com.fpt.xml.hth.db.lib.DAO.MovieDAO;
import com.fpt.xml.hth.db.lib.DTO.MovieTheaterSessionDTO;
import com.fpt.xml.hth.db.lib.resource.Movie;
import com.fpt.xml.hth.db.lib.resource.Movies;
import com.fpt.xml.hth.db.lib.transfer.TransferdEntities;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author dinhquangtrung
 */
public class APIServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        MovieDAO movieDAO = new MovieDAO();
        String city = request.getParameter("city");
        //1.get movies by city
        List<MovieTheaterSessionDTO> lstMovie = movieDAO.getAllByCity(city);
        Movies movies = new Movies();
        TransferdEntities transfer = new TransferdEntities();
        for (int i = 0; i < lstMovie.size(); i++) {
            MovieTheaterSessionDTO dto = lstMovie.get(i);
            Movie movie = transfer.transferFromDBEntitiesToGeneratedEntities(dto);
            movies.getMovie().add(movie);
        }
        StringWriter writer = new StringWriter();
        //2. apply xsd to create xml
        try {
            JAXBContext context = JAXBContext.newInstance(Movies.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION,
                    "http://jbossews-trungdq88.rhcloud.com/API/movieSchema http://jbossews-trungdq88.rhcloud.com/API/APISchema.xsd");
            marshaller.marshal(movies, writer);
        } catch (JAXBException ex) {
            Logger.getLogger(APIServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        //3. output
        String xml = writer.toString();
        response.setContentType("text/xml; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write(xml);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
