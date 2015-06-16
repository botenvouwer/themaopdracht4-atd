/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import domain.Invoice;
import domain.Person;
import java.io.IOException;
import java.io.PrintWriter;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.InvoiceService;

/**
 *
 * @author william
 */
public class WEB_factuur_detail extends HttpServlet {
    
    @Inject
    InvoiceService invoices;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
        Person user = (Person)session.getAttribute("user");
        
        String pid = request.getParameter("id");
        if(pid != null){
            
            Long id = 0l;
            
            try{
                id = Long.parseLong(pid);
            }
            catch(NumberFormatException e){}
            
            Invoice invoice = invoices.find(id);
            
            //voor de security check of opgevraagde factuur wel van klant is
            if(invoice != null && user.equals(invoice.getCustomer())){
                request.setAttribute("invoice", invoice);
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/pdf/Factuur.jsp");
                rd.forward(request, response);
                return;
            }
        }
        
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
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
