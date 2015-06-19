/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import domain.Person;
import domain.Reservation;
import domain.validate.ErrorList;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.ReservationService;

/**
 *
 * @author Nigel
 */
public class WEB_parkeren extends HttpServlet {
    @Inject
    private ReservationService reservations;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.text.ParseException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        if(request.getParameter("placeReservation") != null){
        Reservation newR = new Reservation();
        Scanner f = null;
        try {
            f = new Scanner(request.getParameter("arrivalDate"));
            f.useDelimiter("-");
            Date arrival = new Date();
            f.nextInt();
            arrival.setMonth(f.nextInt());
            arrival.setDate(f.nextInt());
            newR.setArrivalDate(arrival);
            f.close();
        } catch(Exception e) {
        }
        
        try {
            f = new Scanner(request.getParameter("pickupDate"));
            f.useDelimiter("-");
            Date pickup = new Date();
            f.nextInt();
            pickup.setMonth(f.nextInt());
            pickup.setDate(f.nextInt());
            newR.setPickupDate(pickup);
            f.close();
        } catch(Exception e) {
        }
        newR.setThePerson((Person)request.getSession().getAttribute("user"));
        
        ErrorList eL = newR.validate();
        
        if(eL.hasError()) {
            eL.setAttributes(request);
        }
        
        if(eL.isValid()) {
            reservations.create(newR);
        }} 
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pageParts/WEB_Parkeren.jsp");
        rd.forward(request, response);
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
