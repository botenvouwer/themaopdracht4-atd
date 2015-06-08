/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import domain.Person;
import domain.Person.Role;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.PersonService;

/**
 *
 * @author Arnoud
 */
public class WEB_Login extends HttpServlet {

    @Inject
    PersonService persons;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession(true);
        
        //TODO: Verwijs de gebruiker naar zijn homepage als hij al is ingelogd
        
        //Als gebruiker het formulier verzend dan is deze parameter niet null en kunnen we de actie achter het formulier uitvoeren
        if (request.getParameter("send") != null) {
            
            boolean gonogo = true;
            
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            
            if(username.equals("")){
                gonogo = false;
                request.setAttribute("usernameError", "Vul je gebruikernaam in <br>");
            }
            
            if(password.equals("")){
                gonogo = false;
                request.setAttribute("passwordError", "Vul je wachtwoord in <br>");
            }
            
            if(gonogo){
                
                Person person = persons.getPerson(username, password);
                
                if(person != null){
                    session.setAttribute("user", person);
                    
                    //TODO: gebruiker verwijzen naar pagina waar hij vandaan kwam
                    //verwijs de klant naar zijn klant pagina en werknemers naar het cms
                    if(person.getRole() == Role.CUSTOMER){
                        
                        //TODO: url met filter maken voor kalnt pagina
                        response.sendRedirect("/");
                    }
                    else{
                        response.sendRedirect("/cms");
                    }
                    return;
                }
                else{
                    request.setAttribute("usernameError", "Foute inlog");
                }
                
            }
            
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pageParts/WEB_Login.jsp");
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
