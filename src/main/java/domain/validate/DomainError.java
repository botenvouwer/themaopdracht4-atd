/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.validate;

/**
 * 
 * @author william
 */
public class DomainError {
    
    public String name;
    public String message;
    
    public DomainError(String name, String message){
        this.name = name;
        this.message = message;
    }
    
    @Override
    public boolean equals(Object o){
        return (o instanceof DomainError && ((DomainError)o).name.equalsIgnoreCase(name));
    }
    
    public String toString(){
        return String.format("Error[%s]: %s", name, message);
    }
}
