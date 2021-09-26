package by.epamjwd.mobile.controller.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamjwd.mobile.controller.path.Routing;

public interface Command {
	
	Routing execute(HttpServletRequest request, HttpServletResponse response);
	
}
