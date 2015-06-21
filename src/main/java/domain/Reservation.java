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
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Nigel
 */
@Entity
public class Reservation implements Serializable, Validate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Person thePerson;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar arrivalDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar pickupDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateCreated;
    
    public Reservation() {
        dateCreated = Calendar.getInstance();
    }
    
    public Reservation(Person thePerson, Calendar arrivalDate, Calendar pickupDate) {
        this.thePerson = thePerson;
        this.arrivalDate = arrivalDate;
        this.pickupDate = pickupDate;
        dateCreated = Calendar.getInstance();
    }

    public Person getThePerson() {
        return thePerson;
    }

    public Date getArrivalDate() {
        return arrivalDate.getTime();
    }

    public Date getPickupDate() {
        return pickupDate.getTime();
    }

    public Calendar getDateCreated() {
        return dateCreated;
    }

    public void setThePerson(Person thePerson) {
        this.thePerson = thePerson;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setArrivalDate(Calendar arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public void setPickupDate(Calendar pickupDate) {
        this.pickupDate = pickupDate;
    }

    public void setDateCreated(Calendar dateCreated) {
        this.dateCreated = dateCreated;
    }
    
    public boolean active() {
        Calendar today = Calendar.getInstance();
        return pickupDate.after(today);     
    }

    public ErrorList validate() {
        ErrorList list = new ErrorList();
        
        if(arrivalDate == null) {
            list.setError(new DomainError("arrivalError", "Geef een geldige datum op"));
        }
        
        if(pickupDate == null ) {
            list.setError(new DomainError("pickupError", "Geef een geldige datum op"));
        }
        
        if (arrivalDate.after(pickupDate)) {
            list.setError(new DomainError("pickupError", "De vertrek datum moet na de aankomst datum zijn."));
        }
        
        if(arrivalDate.before(Calendar.getInstance())) {
            list.setError(new DomainError("pickupError", "De aankomst datum kan niet in het verleden of vandaag zijn."));
        }
        
        return list;
    }   
}
