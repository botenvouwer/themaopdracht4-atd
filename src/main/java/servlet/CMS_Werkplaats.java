/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import domain.Person;
import domain.Person.Role;
import domain.Task;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.TaskService;

/**
 *
 * @author william
 */
public class CMS_Werkplaats extends HttpServlet {

   @Inject
   TaskService tasks;
    
   protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession(true);
        Person user = (Person)session.getAttribute("user");
        
        List<Task> tasksForToday = null;
        if(request.getParameter("toon") != null && request.getParameter("toon").equals("aanvragen") && user.getRole() == Role.BOSS){
            tasksForToday = tasks.getRequests();
        }
        else{
            
            Calendar  date = Calendar.getInstance();
            
            if(request.getParameter("plannedFor") != null){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.GERMANY);
                try {
                    date.setTime(sdf.parse(request.getParameter("plannedFor")));
                } catch (ParseException ex) {}
            }
            
            if((user.getRole() == Role.BOSS)){
                tasksForToday = tasks.getTasks(date);
            }
            else{
                tasksForToday = tasks.getTasks(date, user);
            }
        }
        
        
        request.setAttribute("tasks", tasksForToday);
        
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pageParts/CMS_Werkplaats.jsp");
        rd.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
