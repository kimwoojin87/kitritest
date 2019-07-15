package com.kitri.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class WelcomeTag extends TagSupport {
	//jsp1.2스타일로 구현된 커스텀 태그
	@Override
	public int doStartTag() throws JspException {
		System.out.println("WelcomeTag doStartTag");
		JspWriter out = this.pageContext.getOut();
		try {
			out.write("WELCOME");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return super.doStartTag();
	}
	@Override
	public int doEndTag() throws JspException {
		System.out.println("WelcomeTag doEndTag");
		
		return super.doEndTag();
	}
}
