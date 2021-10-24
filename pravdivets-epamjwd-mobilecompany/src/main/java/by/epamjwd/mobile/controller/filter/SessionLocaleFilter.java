package by.epamjwd.mobile.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

@WebFilter(filterName = "SessionLocaleFilter", urlPatterns = {"/*"})
public class SessionLocaleFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
      throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        
        if (req.getParameter("sessionLocale") != null) {
            
        	String locale = req.getParameter("sessionLocale");
        	
        	req.getSession().setAttribute("lang", locale);
            System.out.println("AAAAAAAAAAAAAAAA" + req.getParameter("sessionLocale"));
        }
        chain.doFilter(request, response);
    }
    public void destroy() {}
    public void init(FilterConfig arg0) throws ServletException {}
}