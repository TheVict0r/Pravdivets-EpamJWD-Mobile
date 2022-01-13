package by.epamjwd.mobile.controller.command.impl;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Plan;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.command.NumericParser;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.AttributeValue;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.controller.repository.ParameterName;
import by.epamjwd.mobile.service.PlanService;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.exception.ServiceException;

public class CalculatorCommand implements Command{
	
	private final static Logger LOGGER = LogManager.getLogger(CalculatorCommand.class);

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		int withinNetwork = NumericParser.parseIntValue(request.getParameter(ParameterName.CALCULATOR_WITHIN_NETWORK));
		int otherNetworks = NumericParser.parseIntValue(request.getParameter(ParameterName.CALCULATOR_OTHER_NETWORKS));
		int abroad        = NumericParser.parseIntValue(request.getParameter(ParameterName.CALCULATOR_ABROAD));
		int videocall     = NumericParser.parseIntValue(request.getParameter(ParameterName.CALCULATOR_VIDEOCALL));
		int sms           = NumericParser.parseIntValue(request.getParameter(ParameterName.CALCULATOR_SMS));
		int mms           = NumericParser.parseIntValue(request.getParameter(ParameterName.CALCULATOR_MMS));
		int internet      = NumericParser.parseIntValue(request.getParameter(ParameterName.CALCULATOR_INTERNET));		
		
		HttpSession session = request.getSession();
		
		if( 	 withinNetwork == NumericParser.INVALID_VALUE ||
				 otherNetworks == NumericParser.INVALID_VALUE ||
						abroad == NumericParser.INVALID_VALUE ||
					 videocall == NumericParser.INVALID_VALUE ||
						   sms == NumericParser.INVALID_VALUE ||
						   mms == NumericParser.INVALID_VALUE ||
			    	  internet == NumericParser.INVALID_VALUE ){
        			session.setAttribute(AttributeName.WRONG_DATA, AttributeValue.WRONG_DATA);
					return new RouteHelper(PagePath.CALCULATOR_REDIRECT, RouteMethod.REDIRECT);
				}
		
		PlanService planService = ServiceProvider.getInstance().getPlanService();
		Plan bestPlan = null;
		Optional<Plan> planOptional = Optional.empty();
		RouteHelper result = RouteHelper.ERROR;

		try {
			planOptional = planService.suggestPlan(withinNetwork, otherNetworks, abroad, videocall, sms, mms, internet);
			if(planOptional.isPresent()) {
				bestPlan = planOptional.get();
				session.setAttribute(AttributeName.CALCULATOR_BEST_PLAN, bestPlan);
				result = new RouteHelper(PagePath.CALCULATOR_RESULT_REDIRECT, RouteMethod.REDIRECT);
			}
		} catch (ServiceException e) {
			LOGGER.error("Unable to obtain best tariff plan.", e);
			result = RouteHelper.ERROR_500;
		}
		return result;
	}

}
