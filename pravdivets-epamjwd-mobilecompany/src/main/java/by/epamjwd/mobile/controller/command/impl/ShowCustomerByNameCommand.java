package by.epamjwd.mobile.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.command.Command;

public class ShowCustomerByNameCommand implements Command{

	private final static Logger LOGGER = LogManager.getLogger(ShowCustomerByNameCommand.class);


	
	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Ку-ку!");
		return null;
	}

}
