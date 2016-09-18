/**
 * FileName:     PagerTag.java
 * CreationDate: 2014年8月17日
 * Author:       qiujy
 * EMail:        qjyong@gmail.com
 * Site:         http://www.itvk.cn
 * CopyRight: ITVK.CN All Recieves.
 */
package com.huangyunchi.web.tag;

import java.io.IOException;
import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 自定义分页标签处理类
 * 
 * @author qiujy
 */
public class PagerTag extends TagSupport {

	private static final long serialVersionUID = 3643824142930194814L;
	private String url = "";
	private int size = 10;
	private int number = 1;
	private int totalElements = 0;

	public int doStartTag() throws JspException {
		StringBuilder sb = new StringBuilder();

		if (this.totalElements == 0) {
			sb.append("<span class='pull-right'>暂时没有记录</span>");
		} else {
			int totalPages = (totalElements + size - 1) / size;
			if (number > totalPages) {
				number = totalPages;
			}
			if (number < 1) {
				number = 1;
			}

			String id = UUID.randomUUID().toString().replaceAll("-", "");
			sb.append("<form method=\"post\" action=\"").append(this.url)
					.append("\" id=\"" + id + "\">\r\n");
			
			
			HttpServletRequest request = (HttpServletRequest) this.pageContext
					.getRequest();

			Enumeration<String> enumeration = request.getParameterNames();

			String name = null;
			String value = null;
			while (enumeration.hasMoreElements()) {
				name = (String) enumeration.nextElement();
				value = request.getParameter(name);
				if (name.equals("number")) {
					if ((value != null) && (!"".equals(value)))
						number = Integer.parseInt(value);
				} else {
					sb.append("<input type=\"hidden\" name=\"").append(name)
							.append("\" value=\"").append(value)
							.append("\"/>\r\n");
				}
			}

			sb.append("<input type=\"hidden\" name=\"").append("number")
					.append("\" value=\"").append(number).append("\"/>\r\n");

			sb.append("<ul class=\"pager pull-right\" style=\"margin:0;\">");

			if (number > 1) {
				sb.append("<li class=\"previous\"><a href=\"javascript:turnOverPage(")
						.append(number - 1).append(")\">«</a></li>\r\n");
			}

			int start = 1;
			if (number > 4) {
				start = number - 1;
				sb.append(
						"<li><a href=\"javascript:turnOverPage(1)\">1</a></li>\r\n");
				sb.append(
						"<li><a href=\"javascript:turnOverPage(2)\">2</a></li>\r\n");
				sb.append(
						"<li><a href=\"javascript:void(0)\">&hellip;</a></li>\r\n");
			}

			int end = number + 1;
			if (end > totalPages) {
				end = totalPages;
			}
			for (int i = start; i <= end; i++) {
				if (number == i)
					sb.append(
							"<li class=\"active\"><a href=\"javascript:void(0)\">")
							.append(i).append("</a></li>\r\n");
				else {
					sb.append("<li><a href=\"javascript:turnOverPage(")
							.append(i).append(")\">").append(i)
							.append("</a></li>\r\n");
				}
			}

			if (end < totalPages - 2) {
				sb.append(
						"<li><a href=\"javascript:void(0)\">&hellip;</a></li>\r\n");
			}
			if (end < totalPages - 1) {
				sb.append("<li><a href=\"javascript:turnOverPage(")
						.append(totalPages - 1).append(")\">")
						.append(totalPages - 1).append("</a></li>\r\n");
			}
			if (end < totalPages) {
				sb.append("<li><a href=\"javascript:turnOverPage(")
						.append(totalPages).append(")\">").append(totalPages)
						.append("</a></li>\r\n");
			}

			if (number < totalPages) {
				sb.append("<li class=\"next\"><a href=\"javascript:turnOverPage(")
						.append(number + 1).append(")\">»</a></li>\r\n");
			}
			sb.append("</ul>");
			sb.append("<div class='pull-right' style='height:32px;line-height:32px;margin-right:8px;'>共<strong>").append(totalElements).append("</strong>项")
			.append(",<strong>").append(totalPages).append("</strong>页</div>");
			sb.append("</form>\r\n");

			sb.append("<script language=\"javascript\">\r\n");
			sb.append("  function turnOverPage(no){\r\n");
			sb.append("    if(no>").append(totalPages).append("){");
			sb.append("no=").append(totalPages).append(";}\r\n");
			sb.append("    if(no<1){no=1;}\r\n");
			sb.append("    document.getElementById('" + id
					+ "').number.value=no;\r\n");
			sb.append(
					"    document.getElementById('" + id + "').submit();\r\n");
			sb.append("  }\r\n");
			sb.append("</script>\r\n");
			
		}

		try {
			this.pageContext.getOut().write(sb.toString());
			this.pageContext.getOut().flush();
		} catch (IOException e) {
			throw new JspException(e);
		}
		return 0;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @param number
	 *            the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * @return the totalElements
	 */
	public int getTotalElements() {
		return totalElements;
	}

	/**
	 * @param totalElements
	 *            the totalElements to set
	 */
	public void setTotalElements(int totalElements) {
		this.totalElements = totalElements;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

}
