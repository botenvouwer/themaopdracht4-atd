/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package html;

/**
 *
 * @author william
 */
public class Format {
    
    public static String error(String message){
        return String.format("<i class=\"error\">%s</i>", message);
    }
    
}
