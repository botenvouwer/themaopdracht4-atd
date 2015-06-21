/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import domain.Invoice;
import domain.InvoiceLine;
import domain.Task;
import domain.Task.Type;
import domain.UsedArticle;
import domain.validate.ErrorList;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mail.GoogleMail;
import service.InvoiceService;
import service.TaskService;

/**
 *
 * @author william
 */
public class CMS_Werkplaats_Uitboeken extends HttpServlet {
        
    @Inject
    TaskService tasks;
    
    @Inject
    InvoiceService invoices;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        Long id = Long.parseLong(request.getParameter("id"));
        Task t = tasks.find(id);
        request.setAttribute("task", t);
        
        if(request.getParameter("send") != null){
            
            t.setMechanicNote(request.getParameter("mechanicNote"));
            t.setHours(Double.parseDouble(request.getParameter("hours")));
            t.setStatus(Task.Status.FINISHED);
            
            ErrorList list = t.validate();
            
            if(list.isValid()){
                
                Invoice taskInvoice = new Invoice();
                taskInvoice.setStatus(Invoice.Status.OFFER);
                taskInvoice.setCustomer(t.getCustomer());
                String tax = request.getServletContext().getInitParameter("tax");
                taskInvoice.setTax(Double.parseDouble(tax));
                
                String loan = request.getServletContext().getInitParameter("loanperhour");
                double hourloan = Double.parseDouble(loan);
                
                InvoiceLine repairLine = new InvoiceLine();
                repairLine.setDescription(String.format("T%07d %s voor auto: %s %s met kenteken %s", t.getId(), (t.getType() == Type.APK ? "APK" : "Reparatie"), t.getCar().getBrand(), t.getCar().getModel(), t.getCar().getLicensePlate()));
                repairLine.setPrice(hourloan);
                repairLine.setQuantity((int)t.getHours());
                taskInvoice.addLine(repairLine);
                
                for(UsedArticle used : t.getUsedArticles()){
                    InvoiceLine line = new InvoiceLine();
                    line.setDescription(String.format("A%07d %s", used.getArticle().getId(),used.getArticle().getName()));
                    line.setPrice(used.getArticle().getPrice());
                    line.setQuantity(used.getCount());
                    taskInvoice.addLine(line);
                }
                
                invoices.create(taskInvoice);
                t.setInvoice(taskInvoice);
                
                tasks.update(t);
                
                //mail gebruiker over auto
                try{
                    String gUser = request.getServletContext().getInitParameter("mail");
                    String gPass = request.getServletContext().getInitParameter("mailpass");
                    
                    String message = String.format("<p>Beste %s</p><p>Uw %s %s met kenteken %s is klaar om afgehaald te worden.</p><p>Groeten,<br>Het ATD team</p>", t.getCustomer().getName(), t.getCar().getBrand(), t.getCar().getModel(), t.getCar().getLicensePlate());
                    
                    GoogleMail.Send(gUser, gPass, t.getCustomer().getEmail(), "Uw auto is klaar", message);
                } catch (MessagingException ex) {
                    Logger.getLogger(WEB_Registreer.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                response.sendRedirect("/cms/werkplaats");
                return;
            }
            
            list.setAttributes(request);
            
        }
        
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pageParts/CMS_Werkplaats_Uitboeken.jsp");
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
