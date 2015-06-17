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

public class WEB_Gebruiker_Form extends HttpServlet {
    
    @Inject
    PersonService persons;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        Person p = (Person)request.getSession().getAttribute("user");
        Person person = persons.getPerson(p.getEmail(), p.getPassword());
        
        if(request.getParameter("data_change") != null && person != null){
            ErrorList result = person.validate();
            
            String oldEmail = person.getEmail();
            person.setEmail(request.getParameter("email"));
            person.setName(request.getParameter("name"));
            person.setAdress(request.getParameter("adress"));
            person.setPlace(request.getParameter("place"));
            person.setZipcode(request.getParameter("zipcode"));
                
            if(persons.exists(request.getParameter("email")) && !request.getParameter("email").equals(oldEmail)){
                result.setError(new DomainError("emailError", "Er is al een gebruiker met dit email adres"));            
            }
                
            if(result.hasError()){
                result.setAttributes(request);
            }
           else{
                persons.update(person);
            }
        }else if(request.getParameter("password_change") != null && person != null){
            ErrorList result = new ErrorList();
            String oldPassword = person.getPassword();
             
            if(request.getParameter("oldPassword").equals("")){
                result.setError(new DomainError("oldPasswordError", "Vul je oude wachtwoord in"));
            }else{
                if(request.getParameter("oldPassword") != null && !request.getParameter("oldPassword").equals(oldPassword)){
                    result.setError(new DomainError("oldPasswordError", "incorrecte wachtwoord"));
                }
            }
            if(request.getParameter("newPassword").equals("")){
                result.setError(new DomainError("newPasswordError", "Vul je nieuwe wachtwoord in"));
            }
            if(request.getParameter("repeatPassword").equals("")){
                result.setError(new DomainError("repeatPasswordError", "Herhaal je nieuwe wachtwoord"));
            }else{
                if(!request.getParameter("newPassword").equals(request.getParameter("repeatPassword"))){
                    result.setError(new DomainError("repeatPasswordError", "Niet hetzelfde wachtwoord"));
                }
            }
            
            if(result.hasError()){
                result.setAttributes(request);
            }
           else{
                result.setError(new DomainError("newPasswordError", "Wachtwoord aangepast"));
                result.setAttributes(request);
                
                person.setPassword(request.getParameter("newPassword"));
                persons.update(person);
            }
        }
        request.getSession().setAttribute("user", person);
        
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pageParts/WEB_Gebruiker_Form.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
}
