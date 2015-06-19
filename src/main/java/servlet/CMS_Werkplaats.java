/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import domain.Task;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        
        //todo: haal alle werkplaats data op en set attribute
        //pseado code:
        List<Task> tasksForToday = tasks.getTasks("2015-06-18");
        request.setAttribute("tasks", tasksForToday);
        
        //Note: Yanick je mag het uiterlijk ook helemaal aanpassen als je maar vast de werkplaats vult vanuit de database en aan bestaande taken bijvoorbeeld articles kan toevoegen ben ik al heel blij 
        //Maak de werkplaats zo dat je default de taken van vandaag kan zienn en dat je met footer controls naar het verleden of de toekomst kan
        
        //Het zou heel fijn zijn als je het bovenstaande zou kunnen realizeren morgen
        
        //Concept: Voor de chef zit ik na te denken om meer een maand overzicht te maken net zoals elke gemiddelde agenda app. Dat si best makkelijk te maken
        //De werknemer ziet alleen de taken van hem per dag houdt er dus rekening mee dat je 2 rollen hebt binnen werkplaats
        
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pageParts/CMS_Werkplaats.jsp");
        rd.forward(request, response);
    }

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
    }

}
