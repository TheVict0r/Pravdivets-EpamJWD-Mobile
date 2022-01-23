package by.epamjwd.mobile.controller.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamjwd.mobile.controller.RouteHelper;

/**
 * Interface used as a key element of {@code Command} design pattern
 *
 */
public interface Command {
	
	/**
	 * Builds RouteHelper - container with path and route method
	 * 
	 * @param request -  http-request 
	 * 
	 * @param response - http-response 
	 * 
	 * @return - container with path to page for presenting the response and route method
	 */
	RouteHelper execute(HttpServletRequest request, HttpServletResponse response);
	
}
