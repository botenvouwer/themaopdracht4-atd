/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import domain.InvoiceLine;
import javax.ejb.Stateless;

/**
 *
 * @author william
 */
@Stateless
public class InvoiceLineService extends Service<InvoiceLine, Long> {
    
    public InvoiceLineService() {
        super(InvoiceLine.class);
    }
}
