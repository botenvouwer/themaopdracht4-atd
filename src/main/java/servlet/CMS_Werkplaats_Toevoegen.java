package servlet;

import domain.Article;
import domain.Car;
import domain.Person;
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
import service.CarService;
import service.PersonService;
import service.TaskService;

public class CMS_Werkplaats_Toevoegen extends HttpServlet {
    
   @Inject
   TaskService tasks;
    
   @Inject
   PersonService persons;
   
   @Inject
   CarService cars;

   protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        request.setAttribute("mechanics", persons.getPersons(Person.Role.EMPLOYEE));
        request.setAttribute("persons", persons.getPersons(Person.Role.CUSTOMER));
        request.setAttribute("cars", cars.getCars());
        
        if(request.getParameter("send") != null) {
            Task t = new Task();
            
            // Update
            if (request.getParameter("id") != null && !request.getParameter("id").equals("")) {
                t.setId(Long.parseLong(request.getParameter("id")));
                tasks.update(t);
                        
                // Bewerken
            } else {
                Car c = cars.find(Long.parseLong(request.getParameter("klant")));
                
                t.setCar(c);
                t.setCustomer(c.getOwner());
                t.setCustomerNote(request.getParameter("notek"));
                t.setHours(Double.parseDouble(request.getParameter("hours")));
                t.setMechanic(persons.find(Long.parseLong(request.getParameter("monteur"))));
                t.setMechanicNote(request.getParameter("notem"));
                t.setPlannedFor(Timestamp.valueOf(request.getParameter("date") + " 00:00:00"));
                
                if (request.getParameter("type").equals("APK")) {
                    t.setType(Task.Type.APK);
                } else {
                    t.setType(Task.Type.REPAIR);
                }
                
                tasks.create(t);
            }
            
            response.sendRedirect("/cms/werkplaats");
            return;
        }
        
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pageParts/CMS_Werkplaats_Toevoegen.jsp");
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
