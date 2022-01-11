package by.epamjwd.mobile.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.command.NumericParser;
import by.epamjwd.mobile.controller.command.PlanCommandHelper;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.AttributeValue;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.controller.repository.ParameterName;
import by.epamjwd.mobile.service.PlanService;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.exception.ServiceException;
import by.epamjwd.mobile.service.impl.PlanServiceImpl;

public class AddPlanCommand implements Command {
	private final static Logger LOGGER = LogManager.getLogger(AddPlanCommand.class);
	private final static long EMPTY_ID = 0L;

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter(ParameterName.NAME);
		String description = request.getParameter(ParameterName.DESCRIPTION);
		int regularPayment = NumericParser.parseIntValue(request.getParameter(ParameterName.REGULAR_PAYMENT));
		int upfrontPayment = NumericParser.parseIntValue(request.getParameter(ParameterName.UPFRONT_PAYMENT));
		int withinNetwork = NumericParser.parseIntValue(request.getParameter(ParameterName.WITHIN_NETWORK));
		int otherNetworks = NumericParser.parseIntValue(request.getParameter(ParameterName.OTHER_NETWORKS));
		int abroad = NumericParser.parseIntValue(request.getParameter(ParameterName.ABROAD));
		int videocall = NumericParser.parseIntValue(request.getParameter(ParameterName.VIDEOCALL));
		int sms = NumericParser.parseIntValue(request.getParameter(ParameterName.SMS));
		int mms = NumericParser.parseIntValue(request.getParameter(ParameterName.MMS));
		int internet = NumericParser.parseIntValue(request.getParameter(ParameterName.INTERNET));
		long newPlanID = EMPTY_ID;

		PlanService planService = ServiceProvider.getInstance().getPlanService();

		HttpSession session = request.getSession();

		if (name == null || name.isBlank() || description == null || description.isBlank()
				|| regularPayment == NumericParser.INVALID_VALUE || upfrontPayment == NumericParser.INVALID_VALUE
				|| withinNetwork == NumericParser.INVALID_VALUE || otherNetworks == NumericParser.INVALID_VALUE
				|| abroad == NumericParser.INVALID_VALUE || videocall == NumericParser.INVALID_VALUE
				|| sms == NumericParser.INVALID_VALUE || mms == NumericParser.INVALID_VALUE
				|| internet == NumericParser.INVALID_VALUE) {
			session.setAttribute(AttributeName.WRONG_DATA, AttributeValue.WRONG_DATA);
			return new RouteHelper(PagePath.ADD_PLAN_REDIRECT, RouteMethod.REDIRECT);
		}

		try {
			if (planService.isPlanExist(name)) {
				session.setAttribute(AttributeName.ERROR, AttributeValue.PLAN_EXIST);
				session.setAttribute(AttributeName.NAME, name);
				session.setAttribute(AttributeName.DESCRIPTION, description);
				session.setAttribute(AttributeName.REGULAR_PAYMENT, regularPayment);
				session.setAttribute(AttributeName.UPFRONT_PAYMENT, upfrontPayment);
				session.setAttribute(AttributeName.WITHIN_NETWORK, withinNetwork);
				session.setAttribute(AttributeName.OTHER_NETWORKS, otherNetworks);
				session.setAttribute(AttributeName.ABROAD, abroad);
				session.setAttribute(AttributeName.VIDEOCALL, videocall);
				session.setAttribute(AttributeName.SMS, sms);
				session.setAttribute(AttributeName.MMS, mms);
				session.setAttribute(AttributeName.INTERNET, internet);
				return new RouteHelper(PagePath.ADD_PLAN_REDIRECT, RouteMethod.REDIRECT);
			}
		} catch (ServiceException e) {
			LOGGER.error("Error while checking is tarif plan exist by name - " + name, e);
			return RouteHelper.ERROR_500;
		}

		try {
			newPlanID = planService.addNewPlan(planService.buildPlan(name, description, regularPayment, upfrontPayment,
					withinNetwork, otherNetworks, abroad, videocall, sms, mms, internet));
		} catch (ServiceException e) {
			LOGGER.error("Error while adding new plan", e);
			return RouteHelper.ERROR_500;
		}

		return PlanCommandHelper.getInstance().handlePlanByID(session, newPlanID, PagePath.PLAN_ADMIN_REDIRECT, LOGGER);
	}

}
