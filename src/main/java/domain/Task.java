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
import java.sql.Timestamp;
import java.util.ArrayList;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
    private Car car; //Auto van de klant voor deze taak
    @OneToOne
    private Person mechanic; //Werknemer wie taak op zich neemt
    @OneToMany
    private List<UsedArticle> usedArticles = new ArrayList<UsedArticle>(); //Gebruite artikelen wordt uit voorraad geboekt 
    @Temporal(TemporalType.DATE)
    private Calendar plannedFor; //Datum waarop taak is ingepland
    private Timestamp created; //Wordt automatisch gevuld wanneer object gepresist wordt
    
    public Task(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
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

    public List<UsedArticle> getUsedArticles() {
        return usedArticles;
    }
    
    public void setUsedArticle(Article a, int numberUsed){
        UsedArticle ua = findUsedArticle(a);
        
        if(ua == null){
            ua = new UsedArticle();
            ua.setArticle(a);
            usedArticles.add(ua);
        }
        
        ua.setCount(numberUsed);
    }
    
    public void removeArticle(UsedArticle used){
        usedArticles.remove(used);
    }
    
    public UsedArticle findUsedArticle(Article a){
        for(UsedArticle ua : usedArticles){
            if(ua.getArticle().equals(a)){
                return ua;
            }
        }
        return null;
    }

    public void setUsedArticles(List<UsedArticle> usedArticles) {
        this.usedArticles = usedArticles;
    }

    public Calendar getPlannedFor() {
        return plannedFor;
    }

    public void setPlannedFor(Calendar plannedFor) {
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
        
        //Als de status request is dan wordt alleen de default validatie gedaan
        if((customerNote == null || customerNote.equals("")) && type == Type.REPAIR){
            list.setError(new DomainError("customerNoteError", "Geef notitie op van uw probleem!"));
        }
        
        //Als de status planned is moet de datum plan datum bekend zijn
        if(status == Status.PLANNED){
            
            if(plannedFor == null){
                list.setError(new DomainError("plannedForError", "Geef inplannings datum op"));
            }
            
        }
        
        //Als de status finished is dan moeten de uren en de 
        if(status == Status.FINISHED){
            
            if(mechanicNote == null || mechanicNote.equals("")){
                list.setError(new DomainError("mechanicNoteError", "Geef feedback voor klant!"));
            }
            
            if(hours < 0 || hours == 0.0){
                list.setError(new DomainError("hoursError", "Vul je uren in!"));
            }
        }
        
        return list;
    }
    
}

