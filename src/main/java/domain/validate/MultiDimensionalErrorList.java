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
    
    private ArrayList<ErrorList> arrayList = new ArrayList<ErrorList>();
    
    public void setNextError(ErrorList list){
        arrayList.add(list);
    }
    
    public void setErrorAt(int index, ErrorList list){
        arrayList.set(index, list);
    }
    
    public ArrayList<ErrorList> getArrayList(){
        return arrayList;
    }
    
    @Override
    public void setAttributes(HttpServletRequest request){
        
        super.setAttributes(request);
        
        ArrayList<HashMap<String, String>> masterList = new ArrayList<HashMap<String, String>>();
        for(ErrorList listE : arrayList){
            
            HashMap<String, String> errors = new HashMap<String, String>();
            for(DomainError error : listE.getList()){
                errors.put(error.name, error.message);
            }
            
            masterList.add(errors);
        }
        
        request.setAttribute("errorList", masterList);
    }
    
    private boolean isArrayListValid(){
        for(ErrorList listE : arrayList){
            if(listE.hasError()){
                return false;
            }
        }
        
        return true;
    }
    
    @Override
    public boolean isValid(){
        return (list.isEmpty() && isArrayListValid());
    }
    
    @Override
    public boolean hasError(){
        return (!list.isEmpty() || !isArrayListValid());
    }
    
    public String toString(){
        String t = "Main error:\n";
        
        for(DomainError error : list){
            t += "\t"+error.name+": "+error.message+"\n";
        }
        
        t += "\n\nChild Error:\n";
        int q = 0;
        for(ErrorList listE : arrayList){
            
            t += "\tchild "+q+": \n";
            for(DomainError error : listE.getList()){
                t += "\t\t"+error.name+": "+error.message+"\n";
            }
            
            q++;
        }
        
        return t;
    }
    
}
