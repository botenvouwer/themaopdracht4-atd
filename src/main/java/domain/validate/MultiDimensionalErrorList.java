/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.validate;

import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author william
 */
public class MultiDimensionalErrorList extends ErrorList{
    
    private ArrayList<ArrayList<DomainError>> arrayList = new ArrayList<ArrayList<DomainError>>();
    
    public void setNextError(){
        
    }
    
    public void setErrorAt(){
        
    }
    
    @Override
    public void setAttributes(HttpServletRequest request){
        
        super.setAttributes(request);
        
        for(ArrayList<DomainError> listE : arrayList){
            HashMap<String, String> errors = new HashMap<String, String>();
            for(DomainError error : listE){
                request.setAttribute(error.name, error.message);
            }
        }
    }
    
}
