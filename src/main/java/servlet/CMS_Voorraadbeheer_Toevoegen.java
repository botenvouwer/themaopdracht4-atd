/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import domain.Article;
import domain.validate.ErrorList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.ArticleService;

/**
 *
 * @author william
 */
public class CMS_Voorraadbeheer_Toevoegen extends HttpServlet {

    @Inject
    ArticleService articles;
    
   protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        if (request.getParameter("bewerken") != null) {
            Article a = articles.find(Long.parseLong(request.getParameter("bewerken")));
            request.setAttribute("article", a);
        }
        
        if(request.getParameter("send") != null) {
            ErrorList errors = null;
            Article a = new Article();
            
            // Update
            if (request.getParameter("id") != null) {
                a.setId(Long.parseLong(request.getParameter("id")));
                a.setName(request.getParameter("artikel"));
                a.setPrice(Double.parseDouble(request.getParameter("prijs")));
                a.setStock(Integer.parseInt(request.getParameter("voorraad")));
                errors = articles.update(a);
                        
                // Bewerken
            } else {
                a.setName(request.getParameter("artikel"));
                a.setPrice(Double.parseDouble(request.getParameter("prijs")));
                a.setStock(Integer.parseInt(request.getParameter("voorraad")));
                errors = articles.create(a);
            }
            
            if (errors.isValid()) {
                 response.sendRedirect("/cms/voorraad");
                return;   
            } else {
                request.setAttribute("article", a);
                errors.setAttributes(request);
            }
        }
        
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pageParts/CMS_Voorraadbeheer_Toevoegen.jsp");
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
