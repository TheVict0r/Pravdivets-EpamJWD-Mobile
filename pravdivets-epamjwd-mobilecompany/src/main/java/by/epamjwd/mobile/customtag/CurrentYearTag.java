package by.epamjwd.mobile.customtag;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

@SuppressWarnings("serial")
public class CurrentYearTag extends TagSupport {
	@Override
	public int doStartTag() throws JspException {

		String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		
		try {
			JspWriter out = pageContext.getOut();
			out.write(year);
		} catch (IOException e) {
			throw new JspException(e.getMessage());
		}
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
}