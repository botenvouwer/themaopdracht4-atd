/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import domain.Person;
import domain.Person.Role;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author william
 */
public class KlantFilter implements Filter{
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest r, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest request = (HttpServletRequest)r;
        HttpSession session = request.getSession(true);
        Person u = (Person)session.getAttribute("user");
        
        if(u == null || u.getRole() != Role.CUSTOMER || !u.isActive()){
            
            r.setAttribute("returnUrl", request.getRequestURI() + ( request.getQueryString() == null ? "" : "?"+request.getQueryString()));
            
            if(u != null && !u.isActive()){
                r.setAttribute("usernameError", "Uw account is niet actief");
            }
            
            //Geef de login pagina weer inplaats van de aangevraagde pagina
            RequestDispatcher rd = r.getRequestDispatcher("/WEB-INF/pageParts/WEB_Login.jsp");
            rd.forward(r, response);
        }
        else{
            //Ga naar de opgevraagde pagina
            chain.doFilter(r, response);
        }
    }

    @Override
    public void destroy() {
        
    }
    
}
