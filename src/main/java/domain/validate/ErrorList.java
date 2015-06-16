/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.validate;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author william
 */
public class ErrorList{
    
    protected ArrayList<DomainError> list = new ArrayList<DomainError>();
    
    public ErrorList(){
        
    }
    
    public ArrayList<DomainError> getList(){
        return list;
    }
    
    public boolean isValid(){
        return (list.isEmpty());
    }
    
    public boolean hasError(){
        return (!list.isEmpty());
    }
    
    public void setError(DomainError e){
        int index = find(e);
        if(index == -1){
            list.add(e);
        }
        else{
            list.set(index, e);
        }
    }
    
    public boolean contains(DomainError e){
        for(DomainError c : list){
            if(c.equals(e))
                return true;
        }
        return false;
    }
    
    public int find(DomainError e){
        int a = -1;
        for(DomainError c : list){
            a++;
            if(c.equals(e)){
                break;
            }
        }
        return -1;
    }
    
    public String toString(){
        return "Errors: "+hasError();
    }
    
    public void setAttributes(HttpServletRequest request){
        for(DomainError error : list){
            request.setAttribute(error.name, error.message);
        }
    }
    
}
