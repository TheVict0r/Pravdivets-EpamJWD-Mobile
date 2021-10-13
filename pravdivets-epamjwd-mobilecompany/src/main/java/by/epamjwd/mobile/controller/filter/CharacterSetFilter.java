package by.epamjwd.mobile.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;


public class CharacterSetFilter implements Filter {

	public final static String ENCODING = "UTF-8";
	public final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	
    public void doFilter( ServletRequest request, ServletResponse response, FilterChain next) throws IOException, ServletException {
        request.setCharacterEncoding(ENCODING);
        response.setContentType(CONTENT_TYPE);
        response.setCharacterEncoding(ENCODING);
        next.doFilter(request, response);
    }

}