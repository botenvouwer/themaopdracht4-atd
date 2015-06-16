/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import domain.Invoice;
import domain.InvoiceLine;
import domain.Person;
import domain.validate.MultiDimensionalErrorList;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.InvoiceService;
import service.PersonService;

/**
 *
 * @author william
 */
public class CMS_Factuur_Form extends HttpServlet {

    @Inject
    InvoiceService invoices;
    
    @Inject
    PersonService persons;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        request.setAttribute("persons", persons.getPersons(Person.Role.CUSTOMER));
        
        String pid = request.getParameter("id");
        Invoice invoice = null;
        if(pid != null){
            Long id = 0l;
            
            try{
                id = Long.parseLong(pid);
            }
            catch(NumberFormatException e){}
            
            invoice = invoices.find(id);
        }
        
        if(invoice == null){
            invoice = new Invoice();
            invoice.setTax(Double.parseDouble(request.getServletContext().getInitParameter("tax")));
        }
        
        if(request.getParameter("send") != null){
            
            
            //Zet klant voor factuur 
            if(request.getParameter("customer") != null && !request.getParameter("customer").isEmpty() && !request.getParameter("customer").equals((invoice.getCustomer() != null ? invoice.getCustomer().getId().toString() : "" ))){
                Long personId = Long.parseLong(request.getParameter("customer"));
                Person p = persons.find(personId);
                invoice.setCustomer(p);
            }
            
            //loop door verstuurde lijnen
            String[] lineid = request.getParameterValues("lineid");
            String[] descriptions = request.getParameterValues("description");
            String[] prices = request.getParameterValues("price");
            String[] quantitys = request.getParameterValues("quantity");
            String[] discounts = request.getParameterValues("discount");
            
            //Kijken of er bestaande lijnen zijn verwijdert zo ja ook uit Invoice verwijderen
            ArrayList<InvoiceLine> removes = new ArrayList<InvoiceLine>();
            for(InvoiceLine line : invoice.getLines()){
                
                if(!Arrays.asList(lineid).contains(line.getId().toString())){
                    removes.add(line);
                }
            }
            
            for(InvoiceLine line : removes){
                invoice.removeLine(line);
            }
            
            //update oude lijnen en voeg nieuwe toe
            int row = 0;
            for(String lpid : lineid){
                
                Long lineId = Long.parseLong(lpid);
                String description = descriptions[row];
                String price = prices[row];
                String quantity = quantitys[row];
                String discount = discounts[row];
                
                InvoiceLine line = null;
                if(lineId != 0l){
                    //Als lijn als bestaad dan deze updaten
                    line = invoice.getLine(lineId);
                }
                else{
                    //Anders een nieuwe aanmaken
                    line = new InvoiceLine();
                }
                
                line.setDescription(description);
                line.setPrice((price.isEmpty() ? 0 : Double.parseDouble(price)));
                line.setQuantity((quantity.isEmpty() ? 0 : Integer.parseInt(quantity)));
                line.setDiscount((discount.isEmpty() ? 0 : Double.parseDouble(discount)));
                
                if(lineId == 0l){
                    invoice.addLine(line);
                }
                
                row++;
            }
            
            MultiDimensionalErrorList list = null;
            
            if(invoice.getId() == null || invoice.getId() == 0l){
                list = invoices.create(invoice);
            }
            else{
                list = invoices.update(invoice);
            }
            
            if(list.isValid()){
                response.sendRedirect("/cms/factuur");
                return;
            }
            else{
                list.setAttributes(request);
            }
        }
        
        request.setAttribute("invoice", invoice);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pageParts/CMS_Factuur_Form.jsp");
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
