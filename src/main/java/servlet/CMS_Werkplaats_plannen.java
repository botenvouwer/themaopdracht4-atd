/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import domain.Person;
import domain.Task;
import domain.Task.Type;
import domain.validate.ErrorList;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mail.GoogleMail;
import service.PersonService;
import service.TaskService;

/**
 *
 * @author william
 */
public class CMS_Werkplaats_plannen extends HttpServlet {

    @Inject
    TaskService tasks;
    
    @Inject
    PersonService persons;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        request.setAttribute("mechanics", persons.getPersons(Person.Role.EMPLOYEE));
        
        Long id = Long.parseLong(request.getParameter("id"));
        Task t = tasks.find(id);
        request.setAttribute("task", t);
        
        if(request.getParameter("send") != null){
            
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.GERMANY);
            try {
                cal.setTime(sdf.parse(request.getParameter("date")));
            } catch (ParseException ex) {
                Logger.getLogger(CMS_Werkplaats_Toevoegen.class.getName()).log(Level.SEVERE, null, ex);
            }
            t.setPlannedFor(cal);
            t.setMechanic(persons.find(Long.parseLong(request.getParameter("monteur"))));
            t.setStatus(Task.Status.PLANNED);
            
            ErrorList list = tasks.update(t);
            
            if(list.isValid()){
                
                //mail klant plandatum
                try{
                    String gUser = request.getServletContext().getInitParameter("mail");
                    String gPass = request.getServletContext().getInitParameter("mailpass");
                    
                    String url = request.getRequestURL().toString();
                    String baseURL = url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath() + "/";
                    String activateURL = baseURL+"/klant/reparatie/detail?id="+t.getId();
                    sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.GERMANY);
                    String message = String.format("<p>Beste %1$s</p><p>Wij hebben een %3$s voor u ingepland op %4$s. Zorg dat u auto een dag van te voren bij onze garage afgeleverd wordt. Zie <a href=\"%2$s\">hier</a> meer details van u %3$s. Werkt de link niet knip en plak deze dan handmatig in je browser:<br>%2$s</p><p>Groeten,<br>Het ATD team</p>", t.getCustomer().getName(), activateURL, (t.getType() == Type.APK ? "APK" : "Reparatie"), sdf.format(cal));
                    
                    GoogleMail.Send(gUser, gPass, t.getCustomer().getEmail(), String.format("Bevestiging %s aanvraag" , (t.getType() == Type.APK ? "APK" : "Reparatie")), message);
                } catch (MessagingException ex) {
                    Logger.getLogger(WEB_Registreer.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                response.sendRedirect("/cms/werkplaats?toon=aanvragen");
                return;   
            }
            
            list.setAttributes(request);
            
        }
        
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pageParts/CMS_Werkplaats_Plannen.jsp");
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
