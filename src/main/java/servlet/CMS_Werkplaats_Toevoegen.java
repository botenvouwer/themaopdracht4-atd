package servlet;

import domain.Car;
import domain.Person;
import domain.Task;
import domain.validate.ErrorList;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            Car c = cars.find(Long.parseLong(request.getParameter("klant")));
            
            Task t = new Task();
            t.setCar(c);
            t.setCustomer(c.getOwner());
            t.setCustomerNote(request.getParameter("customerNote"));
            t.setType(Task.Type.valueOf(request.getParameter("type")));
            t.setStatus(Task.Status.PLANNED);
            
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.GERMANY);
            try {
                cal.setTime(sdf.parse(request.getParameter("date")));
            } catch (ParseException ex) {
                Logger.getLogger(CMS_Werkplaats_Toevoegen.class.getName()).log(Level.SEVERE, null, ex);
            }
            t.setPlannedFor(cal);
            
            t.setMechanic(persons.find(Long.parseLong(request.getParameter("monteur"))));
            
            ErrorList list = tasks.create(t);
            
            if(list.isValid()){
                response.sendRedirect("/cms/werkplaats");
                return;   
            }
            
            list.setAttributes(request);
            
        }
        
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pageParts/CMS_Werkplaats_Toevoegen.jsp");
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
