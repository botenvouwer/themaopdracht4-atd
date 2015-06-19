package servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import domain.Car;
import domain.Person;
import domain.validate.DomainError;
import domain.validate.ErrorList;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.CarService;
import service.CarService;

@WebServlet(urlPatterns = {"/klant/auto"})
public class WEB_Auto_Form extends HttpServlet {

    @Inject
    CarService cars;    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Car car = null;
        int id = 0;
        List<Car> allCars = cars.getCars((Person)request.getSession().getAttribute("user"));
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {

        }
        for(Car c : allCars){
            if(c.getId() == id){
                request.setAttribute("car", c);
                car = c;
            }
        }
        
        boolean succes = false;
        if(request.getParameter("new") != null){
            // Nieuwe auto
            car = new Car();
            car.setBrand(request.getParameter("brand"));
            car.setLicensePlate(request.getParameter("licensePlate"));
            car.setModel(request.getParameter("model"));
            car.setOwner((Person)request.getSession().getAttribute("user"));
            
            ErrorList result = car.validate();
            
            if(cars.exists(car.getLicensePlate())){
                  result.setError(new DomainError("licensePlateError", "Kenteken bestaat al"));          
            }
            if(result.hasError()){
                result.setAttributes(request);
            }else{
                cars.create(car);
                response.sendRedirect("/klant");
                return;
            }
        }else if(request.getParameter("edit") != null && car != null){
            String oldLicensePlate = car.getLicensePlate();
            car.setBrand(request.getParameter("brand"));
            car.setLicensePlate(request.getParameter("licensePlate"));
            car.setModel(request.getParameter("model"));
            car.setOwner((Person)request.getSession().getAttribute("user"));
            
            ErrorList result = car.validate();
            
            if(cars.exists(car.getLicensePlate()) && !car.getLicensePlate().equals(oldLicensePlate)){
                  result.setError(new DomainError("licensePlateError", "Kenteken bestaat al"));          
            }
            if(result.hasError()){
                result.setAttributes(request);
            }else{
                cars.update(car);
                response.sendRedirect("/klant");
                return;
            }
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pageParts/WEB_Auto_Form.jsp");
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
