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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import mail.GoogleMail;

/**
 *
 * @author Frank, William Loosman
 */
public class WEB_Registreer extends HttpServlet {

    //injecteer de service waarmee we gebruiker kunnen toevoegen, verwijderen, aanpassen en uitlezen
    @Inject
    private PersonService persons;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        //chek of er een formulier verzonden is en verwerk data als dit zo is
        if(request.getParameter("send") != null){
            
            //Haal alle formulier velden op
            Person person = new Person();
            person.setEmail(request.getParameter("email"));
            person.setPassword(request.getParameter("password"));
            person.setName(request.getParameter("name"));
            person.setRole(Person.Role.CUSTOMER);
            person.setAdress(request.getParameter("adress"));
            person.setPlace(request.getParameter("place"));
            person.setZipcode(request.getParameter("zipcode"));
            
            ErrorList result = person.validate();
            
            if(request.getParameter("repeat").equals("")){
                result.setError(new DomainError("passwordError", "Herhaal wachtwoord"));
            }
            else if(!person.getPassword().equals(request.getParameter("repeat"))){
                result.setError(new DomainError("passwordError", "Wachtwoorden komen niet overeen"));
            }
            
            if(persons.exists(person.getEmail())){
                result.setError(new DomainError("emailError", "Er is al een gebruiker met dit email adres"));            
            }
            
            if(result.hasError()){
                result.setAttributes(request);
            }
            
            //als gonogo true is de nieuwe klant opslaan en email verzenden
            if(result.isValid()){
                
                //sla persoon op in database
                persons.create(person);
                
                //Stuur gebruiker naar inlog pagina met melding
                request.setAttribute("registered", String.format("Er is een account aangemaakt voor %s check je email voor activatie code", person.getEmail()));
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pageParts/WEB_Login.jsp");
                rd.forward(request, response);
                
                //mail gebruiker met activatie code
                try{
                    String gUser = request.getServletContext().getInitParameter("mail");
                    String gPass = request.getServletContext().getInitParameter("mailpass");
                    
                    String url = request.getRequestURL().toString();
                    String baseURL = url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath() + "/";
                    String activateURL = baseURL+"activeren?code="+person.getActivation();
                    String message = String.format("<p>Beste %1$s</p><p>Er is een account voor u aangemaakt op onze website. Ga <a href=\"%2$s\">hier</a> naartoe om je account te activeren. Werkt de link niet knip en plak deze dan handmatig in je browser:<br>%2$s</p><p>Groeten,<br>Het ATD team</p>", person.getName(), activateURL);

                    GoogleMail.Send(gUser, gPass, person.getEmail(), "Account aangemaakt", message);
                } catch (MessagingException ex) {
                    Logger.getLogger(WEB_Registreer.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                return;
                
            }
        }
        
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pageParts/WEB_Registreer.jsp");
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
