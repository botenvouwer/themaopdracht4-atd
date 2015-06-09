/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.validate.ErrorList;
import domain.validate.Validate;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;

/**
 *
 * @author william
 */
@Entity
public class Invoice implements Serializable, Validate {
    private static final long serialVersionUID = 1L;
    
    public enum Status {OFFER, NOTPAID, PAID, CANCELED};
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany
    private List<InvoiceLine> lines = new ArrayList<InvoiceLine>();
    @OneToOne
    private Person customer;
    private Timestamp date;
    private Timestamp send;
    @Enumerated(EnumType.STRING)
    private Status status = Status.OFFER;
    private double tax = 1.21; //TODO: uit configutratie halen
    
    public Invoice() {}
    
    public double getTotal() {
        return getTaxFree() * tax;
    }
    
    public double getBTW(){
        double price = getTaxFree();
        return (price * tax) - price;
    }
    
    public double getTaxFree(){
        double totalPrice = 0;
        for (InvoiceLine line : lines) {
            totalPrice += line.getTotal();
        }
        return totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<InvoiceLine> getLines() {
        return lines;
    }

    public void setLines(List<InvoiceLine> lines) {
        this.lines = lines;
    }
    
    public void addLine(InvoiceLine line){
        this.lines.add(line);
    }

    public Person getCustomer() {
        return customer;
    }

    public void setCustomer(Person customer) {
        this.customer = customer;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public Timestamp getSend() {
        return send;
    }

    public void setSend(Timestamp send) {
        this.send = send;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
    @PrePersist
    void createdAt() {
        date = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Invoice)) {
            return false;
        }
        Invoice other = (Invoice) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("domain.Person[ id= %s ]", id);
    }

    @Override
    public ErrorList validate() {
        
        ErrorList list = new ErrorList();
        
        
        //Loop door factuur lijnen en validate deze
        
        return list;
    }

}
