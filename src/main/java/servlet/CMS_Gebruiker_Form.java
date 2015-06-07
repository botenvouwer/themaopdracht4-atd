/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import domain.Person;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.PersonService;

/**
 *
 * @author william
 */
public class CMS_Gebruiker_Form extends HttpServlet {

    @Inject
    PersonService persons; //Injecteer de persoon service waarmee we personen kunnen aanmaken, updaten, verwijderen en opzoeken
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        //Controleren of het formulier is verzonden
        if(request.getParameter("send") != null){
            
        }
        
        //Controleren of we een bestaande gebruiker moeten inladen
        String pid = request.getParameter("id");
        if(pid != null){
            
            Long id = 0l;
            
            try{
                id = Long.parseLong(pid);
            }
            catch(NumberFormatException e){}
            
            Person p = persons.find(id);
            request.setAttribute("user", p);
        }
        
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pageParts/CMS_Gebruiker_Form.jsp");
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
