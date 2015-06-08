/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import domain.Person;
import domain.validate.DomainError;
import domain.validate.ErrorList;
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
        
        //Controleren of we een bestaande gebruiker moeten inladen
        String pid = request.getParameter("id");
        Person p = null;
        if(pid != null){
            
            Long id = 0l;
            
            try{
                id = Long.parseLong(pid);
            }
            catch(NumberFormatException e){}
            
            p = persons.find(id);
            request.setAttribute("person", p);
        }
        
        //Controleren of het formulier is verzonden
        if(request.getParameter("send") != null){
            
            if(p == null){
                
                //gebruiker bestaad niet dus aanmaken
                Person newPerson = new Person();
                newPerson.setActivation("");
                newPerson.setActive(true);
                newPerson.setAdress(request.getParameter("adress"));
                newPerson.setEmail(request.getParameter("email"));
                newPerson.setName(request.getParameter("name"));
                newPerson.setPassword(request.getParameter("password"));
                newPerson.setPlace(request.getParameter("place"));
                newPerson.setRole(Person.Role.valueOf(request.getParameter("role"))); //dit kan fout gaan!
                newPerson.setZipcode(request.getParameter("zipcode"));
                
                ErrorList result = newPerson.validate();
            
                if(request.getParameter("repeat").equals("")){
                    result.setError(new DomainError("passwordError", "Herhaal wachtwoord"));
                }
                else if(!newPerson.getPassword().equals(request.getParameter("repeat"))){
                    result.setError(new DomainError("passwordError", "Wachtwoorden komen niet overeen"));
                }
                
                if(persons.exists(newPerson.getEmail())){
                    result.setError(new DomainError("emailError", "Er is al een gebruiker met dit email adres"));            
                }

                if(result.hasError()){
                    result.setAttributes(request);
                }
                else{
                    persons.create(newPerson);
                    response.sendRedirect("/cms/gebruiker");
                    return;
                }
                
            }
            else{
                //gebruiker bestaad al dus updaten
                String oldEmail = p.getEmail();
                
                p.setAdress(request.getParameter("adress"));
                p.setEmail(request.getParameter("email"));
                p.setName(request.getParameter("name"));
                p.setPlace(request.getParameter("place"));
                p.setRole(Person.Role.valueOf(request.getParameter("role"))); //dit kan fout gaan!
                p.setZipcode(request.getParameter("zipcode"));
                
                ErrorList result = p.validate();
                
                //Todo: deze controle moet eigelijk ook in de service update methode
                if(!oldEmail.equalsIgnoreCase(p.getEmail()) && persons.exists(p.getEmail())){
                    result.setError(new DomainError("emailError", "Er is al een gebruiker met dit email adres"));            
                }
                
                if(result.hasError()){
                    result.setAttributes(request);
                }
                else{
                    persons.update(p);
                    response.sendRedirect("/cms/gebruiker");
                    return;
                }
                
            }
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
