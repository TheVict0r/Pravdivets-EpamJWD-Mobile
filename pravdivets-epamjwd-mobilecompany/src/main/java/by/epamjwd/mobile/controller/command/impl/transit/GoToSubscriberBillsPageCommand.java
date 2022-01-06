package by.epamjwd.mobile.controller.command.impl.transit;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Bill;
import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.service.BillService;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.exception.ServiceException;

public class GoToSubscriberBillsPageCommand implements Command {

	private final static Logger LOGGER = LogManager.getLogger(GoToSubscriberBillsPageCommand.class);

	
	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Subscriber currentSubscriber = (Subscriber)session.getAttribute(AttributeName.SUBSCRIBER);
		
		if(currentSubscriber == null) {
			LOGGER.error("Null subscriber while moving to subscriber bills page");
			return RouteHelper.ERROR;
		}
		
		long subscriberID = currentSubscriber.getId();
		
		BillService billService = ServiceProvider.getInstance().getBillService();
		
		List<Bill> billList;
		
		try {
			billList = billService.getBillListBySubscriberID(subscriberID);
			session.setAttribute(AttributeName.BILL_LIST, billList);
		} catch (ServiceException e) {
			LOGGER.error("Error during recieving bills data for subscriber with ID" + subscriberID, e);
			return RouteHelper.ERROR_500;
		}
		
		
		return new RouteHelper(PagePath.SUBSCRIBER_BILLS, RouteMethod.FORWARD);
	}

}
