package by.epamtc.restaurant.view.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

@SuppressWarnings("serial")
public class CopyrightTag extends TagSupport {

	private String copyrightText;

	public String getCopyrightText() {
		return copyrightText;
	}

	public void setCopyrightText(String copyrightText) {
		this.copyrightText = copyrightText;
	}

	@Override
	public int doStartTag() throws JspException {
		try {

			JspWriter out = pageContext.getOut();
			out.write(copyrightText);

		} catch (IOException e) {
			throw new JspException(e.getMessage());
		}
		return SKIP_BODY;
	}

}
