package by.epamjwd.mobile.controller.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamjwd.mobile.controller.RouteHelper;

/**
 * Common interface used as a key element of {@code Command} design pattern
 *
 */
public interface Command {
	
	RouteHelper execute(HttpServletRequest request, HttpServletResponse response);
	
}
