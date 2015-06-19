package servlet;

import domain.Car;
import domain.Person;
import domain.Task;
import java.io.IOException;
import java.io.PrintWriter;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.CarService;
import service.TaskService;

public class WEB_Reparatie_Form extends HttpServlet {
    
    @Inject
    CarService cars;
    
    @Inject
    TaskService tasks;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession(true);
        request.setAttribute("cars", cars.getCars((Person) session.getAttribute("user")));
        
        if(request.getParameter("send") != null) {
            Task t = new Task();
            t.setCar(cars.find(Long.parseLong(request.getParameter("car"))));
            t.setCustomer((Person) session.getAttribute("user"));
            t.setStatus(Task.Status.REQUEST);
            
            if (request.getParameter("type").equals("APK")) {
                t.setType(Task.Type.APK);
            } else {
                t.setType(Task.Type.REPAIR);
                t.setCustomerNote(request.getParameter("note"));
            }
            
            tasks.create(t);
            
            response.sendRedirect("/klant/reparatie");
            return;
        }
        
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pageParts/WEB_Reparatie_Form.jsp");
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
