/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import domain.Invoice;
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

/**
 *
 * @author william
 */
public class CMS_Factuur extends HttpServlet {

    @Inject
    InvoiceService invoices;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        if(request.getParameter("id") != null && request.getParameter("action") != null){
            //voer actie uit
            Long id = Long.parseLong(request.getParameter("id"));
            String action = request.getParameter("action");
            
            if(action.equals("send")){
                invoices.setNotPaid(id);
                sendMail(invoices.find(id), request);
            }
            else if(action.equals("sendall")){
                //Todo: alle facturen verzenden
            }
            else if(action.equals("cancel")){
                invoices.setCanceled(id);
            }
            else if(action.equals("paid")){
                invoices.setPaid(id);
            }
            
        }
        else if(request.getParameter("id") != null){
            //toon factuur detail pagina
            Long id = Long.parseLong(request.getParameter("id"));
            request.setAttribute("invoice", invoices.find(id));
            
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pageParts/CMS_Factuur_Detail.jsp");
            rd.forward(request, response);
            return;
        }
        
        //Todo: filters maken
        
        request.setAttribute("invoices", invoices.get("SELECT i FROM Invoice i"));
        
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pageParts/CMS_Factuur.jsp");
        rd.forward(request, response);
    }
    
    private void sendMail(Invoice invoice, HttpServletRequest request){
        
        try{
            String gUser = request.getServletContext().getInitParameter("mail");
            String gPass = request.getServletContext().getInitParameter("mailpass");

            String url = request.getRequestURL().toString();
            String baseURL = url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath() + "/";
            String refURL = baseURL+"klant/factuur/pdf?id="+invoice.getId();
            String overzichtURL =  baseURL+"klant/factuur";
            
            String message = String.format("<p>Beste %1$s</p><p>Wij hebben een factuur voor u. Ga <a href=\"%2$s\">hier</a> naartoe om het factuur in te zien. Werkt de link niet knip en plak deze dan handmatig in je browser:<br>%2$s</p><p>Voor een overzicht van al uw facturen <a href=\"%3$s\">ga</a> naar: %3$s</p><p>Groeten,<br>Het ATD team</p>", invoice.getCustomer().getName(), refURL, overzichtURL);
            
            GoogleMail.Send(gUser, gPass, invoice.getCustomer().getEmail(), String.format("Nieuwe factuur - F%07d", invoice.getId()) , message);
        } catch (MessagingException ex) {
            Logger.getLogger(WEB_Registreer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
