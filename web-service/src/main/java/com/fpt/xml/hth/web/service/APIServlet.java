/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.xml.hth.web.service;

import com.fpt.xml.hth.db.lib.DAO.CinemaDAO;
import com.fpt.xml.hth.db.lib.DAO.MovieDAO;
import com.fpt.xml.hth.db.lib.DTO.CinemaDTO;
import com.fpt.xml.hth.db.lib.DTO.MovieTheaterSessionDTO;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        // /request.getRequestDispatcher("WEB-INF/sample.jsp").forward(request, response);
        // 1. Get data from db-lib
        // 2. Create XML string
        // out.write("<movies>asda</movies>");
        // 1.get list all movie
        MovieDAO movieDAO = new MovieDAO();
        List<MovieTheaterSessionDTO> lstMovie = movieDAO.getAll();
        // 2. Create XML string
        response.setContentType("text/xml");
        // 3. Return
        PrintWriter out = response.getWriter();
        for (int i = 0; i < lstMovie.size(); i++) {
            MovieTheaterSessionDTO dto = lstMovie.get(i);
        }
        out.print("<movie>");
        
        out.print("</movie");

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
