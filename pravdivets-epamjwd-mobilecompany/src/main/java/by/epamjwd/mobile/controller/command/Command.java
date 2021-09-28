package by.epamjwd.mobile.controller.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamjwd.mobile.controller.RouteHelper;

public interface Command {
	
	RouteHelper execute(HttpServletRequest request, HttpServletResponse response);
	
}
