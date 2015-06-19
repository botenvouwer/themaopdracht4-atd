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
import java.util.Calendar;
import java.util.List;
import javax.persistence.Column;
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
 * @author William
 */
@Entity
public class Task implements Serializable, Validate{
    private static final long serialVersionUID = 1L;
    
    public enum Type {REPAIR, APK}; //Type taak is het een reparatie of een apk keuring
    public enum Status {REQUEST, PLANNED, FINISHED, CANCELED}; //status is als klant een aanvraag doet een REQUEST (die kan an door een chef ingeplant worden) en panned als een taak wordt aangemaakt in cms. Daarna kan een taak worden geFINISHED. Hij kan altijd worden geannuleerd tenzij hij al geFINISHED is!
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Status status = Status.REQUEST;
    @Enumerated(EnumType.STRING)
    private Type type = Type.REPAIR;
    @Column(columnDefinition="TEXT") //zorgt ervoor dat de string als lange TEXT in db komt anders krijg je een varchar van 60~/130~ karakters
    private String customerNote; //notitie van de klant is optioneel!
    @Column(columnDefinition="TEXT")
    private String mechanicNote; //Notitie van de werknemer is verplicht!
    private double hours; //Huren als double kan berekent worden naar uren of anders iets van Time class opzoeken!?
    @OneToOne
    private Invoice invoice;
    @OneToOne
    private Person customer; //Klant voor wie de taak wordt uitgevoerd
    @OneToOne
    private Car car; //Auto van de kalnt voor deze taak
    @OneToOne
    private Person mechanic; //Werknemer wie taak op zich neemt
    @OneToMany
    private List<Delivery> usedArticles; // Let op! dit zijn de gebruikte artikelen -> de Delivery class moet je generiek maken zodat deze ook gebruikt kan worden voor usedArticle of je maakt een nieuwe class usedArticle
    private Timestamp plannedFor; //Datum waarop taak is ingepland
    private Timestamp created; //Wordt automatisch gevuld wanneer object gepresist wordt
    
    public Task(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getCustomerNote() {
        return customerNote;
    }

    public void setCustomerNote(String customerNote) {
        this.customerNote = customerNote;
    }

    public String getMechanicNote() {
        return mechanicNote;
    }

    public void setMechanicNote(String mechanicNote) {
        this.mechanicNote = mechanicNote;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public Person getCustomer() {
        return customer;
    }

    public void setCustomer(Person customer) {
        this.customer = customer;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Person getMechanic() {
        return mechanic;
    }

    public void setMechanic(Person mechanic) {
        this.mechanic = mechanic;
    }

    public List<Delivery> getUsedArticles() {
        return usedArticles;
    }

    public void setUsedArticles(List<Delivery> usedArticles) {
        this.usedArticles = usedArticles;
    }

    public Timestamp getPlannedFor() {
        return plannedFor;
    }

    public void setPlannedFor(Timestamp plannedFor) {
        this.plannedFor = plannedFor;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }
    
    @PrePersist
    void onCreate() {
        created = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
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
        if (!(object instanceof Task)) {
            return false;
        }
        Task other = (Task) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("domain.Task[ id= %s ]", id);
    }
    
    public ErrorList validate(){
        
        ErrorList list = new ErrorList();
        
        //Todo: validatie maken voor Task
        
        return list;
    }
    
}

