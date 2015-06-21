/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import domain.Article;
import domain.Task;
import domain.UsedArticle;
import domain.validate.ErrorList;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.ArticleService;
import service.TaskService;

/**
 *
 * @author william
 */
public class CMS_Werkplaats_Artikel_Toevoegen extends HttpServlet {

    @Inject
    ArticleService articles;
    
    @Inject
    TaskService tasks;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        if(request.getParameter("id") != null){
            Long taskid = Long.parseLong(request.getParameter("id"));
            Task task = tasks.find(taskid);
            
            if(task != null){
                
                request.setAttribute("task", task);
                request.setAttribute("articles", articles.getArticles());
                
                if(request.getParameter("send") != null){
                    
                    Long articleid = Long.parseLong(request.getParameter("article"));
                    Article a = articles.find(articleid);
                    
                    int count = Integer.parseInt(request.getParameter("count"));
                    
                    UsedArticle used = new UsedArticle();
                    used.setArticle(a);
                    used.setCount(count);
                    
                    ErrorList list = used.validate();
                    
                    if(list.isValid()){
                        task.setUsedArticle(a, count);
                        tasks.update(task);
                        
                        response.sendRedirect("/cms/werkplaats");
                        return;
                    }
                    else{
                        request.setAttribute("used", used);
                        list.setAttributes(request);
                    }
                    
                }
                
            }
            else{
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
            
        }
        else{
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pageParts/CMS_Werkplaats_Artikel_Toevoegen.jsp");
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
