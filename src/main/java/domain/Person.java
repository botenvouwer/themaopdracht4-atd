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
import java.math.BigInteger;
import java.security.SecureRandom;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

/**
 *
 * @author Nigel
 */
@Entity
public class Person implements Serializable, Validate{
    private static final long serialVersionUID = 1L;
    
    public enum Role {BOSS, EMPLOYEE, CUSTOMER};
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Role role = Role.CUSTOMER;
    private String name;
    private String email;
    private String password;
    private String place;
    private String zipcode;
    private String adress;
    @Column(length=32)
    private String activation;
    private boolean active = true;
        
    public Person(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getActivation() {
        return activation;
    }

    public void setActivation(String activation) {
        this.activation = activation;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    @PrePersist
    void setActiveCode() {
        if(role == Role.CUSTOMER){
            
            // als gebruiker een klant is maak dan een activatie code aan
            active = false;
            SecureRandom random = new SecureRandom();
            activation = new BigInteger(130, random).toString(32);
            
        }
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
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("domain.Person[ id= %s, name= %s ]", id, name);
    }
    
    //Valideer functie deze controleert of er fouten zijn en deze worden gerapporteert in ErrorList
    public ErrorList validate(){
        
        ErrorList list = new ErrorList();
        
        //controleer alle velden
        if(name == null || name.equals("")){
            list.setError(new DomainError("nameError", "Vul je naam in"));
        }

        if(adress == null || adress.equals("")){
            list.setError(new DomainError("adressError", "Vul je adres in"));
        }

        if(place == null || place.equals("")){
            list.setError(new DomainError("placeError", "Vul in waar je woont"));
        }

        if(zipcode == null || zipcode.equals("")){
            list.setError(new DomainError("zipcodeError", "Vul je postcode in"));
        }

        if(email == null || email.equals("")){
            list.setError(new DomainError("emailError", "Vul je e-mailadres in"));
        }
        else{
            boolean result = true;
            try {
               InternetAddress emailAddr = new InternetAddress(email);
               emailAddr.validate();
            } catch (AddressException ex) {
               result = false;
            }

            if(!result){
                list.setError(new DomainError("emailError", "Moet valid email adres zijn"));
            }
        }

        if(password == null || password.equals("")){
            list.setError(new DomainError("passwordError", "Vul wachtwoord in"));
        }
        
        return list;
    }
    
}
