package servlet;

import domain.Car;
import domain.Person;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.CarService;

@WebServlet(name = "WEB_Gebruiker", urlPatterns = {"/klant"})
public class WEB_Gebruiker extends HttpServlet {

    @Inject
    CarService cars;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        List<Car> allCars = cars.getCars((Person)request.getSession().getAttribute("user"));
        
        if(request.getParameter("delete") != null){
            Car car = null;
            int id = 0;
            
            try {
               id = Integer.parseInt(request.getParameter("delete"));
            } catch (NumberFormatException e) { }
            for(Car c : allCars){
                if(c.getId() == id){
                    request.setAttribute("car", c);
                    car = c;
                }
            }
            if(car != null){
                cars.deleteCar(id);
                response.sendRedirect("/klant");
                return;
            }
        }
        
        List<Car> caarrs = new ArrayList<Car>();
        for(Car c : allCars){
            if(!c.isSoftDelete()){
               caarrs.add(c);
            }
        }
        
        request.setAttribute("cars", caarrs);
        
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pageParts/WEB_Gebruiker.jsp");
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
    }

}
