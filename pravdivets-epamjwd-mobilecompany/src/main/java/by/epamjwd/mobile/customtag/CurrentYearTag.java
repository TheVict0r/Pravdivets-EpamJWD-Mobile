package by.epamjwd.mobile.customtag;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class CurrentYearTag extends TagSupport {
	private static final long serialVersionUID = -4777067879247009641L;
	private final static Logger LOGGER = LogManager.getLogger(CurrentYearTag.class);

	
	@Override
	public int doStartTag() throws JspException {

		String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		
		try {
			JspWriter out = pageContext.getOut();
			out.write(year);
		} catch (IOException e) {
            LOGGER.error("Unable to write to output stream.", e);
		}
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
}