/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import domain.Delivery;
import java.io.IOException;
import java.io.PrintWriter;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.ArticleService;
import service.DeliveryService;

/**
 *
 * @author yanick
 */
public class CMS_Bestelling_Form extends HttpServlet {
    
    @Inject
    DeliveryService deliverys;
    
    @Inject
    ArticleService articles;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setAttribute("articles", articles.getArticles());

        if(request.getParameter("send") != null) {
            Delivery d = new Delivery();
            d.setArticle(articles.find(Long.parseLong(request.getParameter("article"))));
            d.setCount(Integer.parseInt(request.getParameter("count")));
            d.setStatus(Delivery.Status.STANDAARD);
            deliverys.create(d);
            
            response.sendRedirect("/cms/bestellingen");
            return;
        }
        
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pageParts/CMS_Bestelling_Form.jsp");
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
