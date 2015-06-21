/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.validate.DomainError;
import domain.validate.ErrorList;
import domain.validate.Validate;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author william
 */
@Entity
public class InvoiceLine implements Serializable, Validate {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(columnDefinition="TEXT")
    private String description;
    private double price = 0;
    private double discount = 0;
    private int quantity = 1;

    public InvoiceLine(){}
    
    public double getTotal() {
        return getPrice() * getQuantity() * (1 - discount / 100);
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }
    
    public String getDiscountPercentage() {
        String numberD = String.valueOf(discount);
        numberD = numberD.substring(numberD.indexOf(".")+1);
        return numberD;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
        if (!(object instanceof InvoiceLine)) {
            return false;
        }
        InvoiceLine other = (InvoiceLine) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("domain.InvoiceLine[ id= %s, description= %s ]", id, description);
    }

    @Override
    public ErrorList validate() {
        
        ErrorList list = new ErrorList();
        
        if(description == null || description.equals("")){
            list.setError(new DomainError("descriptionError", "Geef omschrijving op!"));
        }
        else if(description.length() < 5){
            list.setError(new DomainError("descriptionError", "Omschrijving moet langer dan 5 karakters zijn!"));
        }
        
        if(price < 0 || price == 0.0){
            list.setError(new DomainError("priceError", "prijs mag niet negatief zijn!"));
        }
        
        if(quantity < 1){
            list.setError(new DomainError("quantityError", "Aantal moet minstens 1 zijn!"));
        }
        
        //Todo: discount validatie
        
        return list;
        
    }
    
}
