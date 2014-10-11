package com.mdcl.web.sso;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SSORequestServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String CONTENT_TYPE = "text/xml";

	// Initialize global variables
	public void init() throws ServletException {
	}

	// Process the HTTP Get request
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();

		Principal principal = request.getUserPrincipal();

		String uid = "";
		if(principal != null) {
			uid = principal.getName();
		}

		try {
			InitialContext ctxt = new javax.naming.InitialContext();
			ctxt.rebind("aName", uid);
		}catch (NamingException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace(out);

		}

		ResponseSet responseSet = new ResponseSet("authority");
		responseSet.addResponseContent(uid);

		out.println(responseSet.toXMLString());

	}

	// Process the HTTP Post request
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	// Clean up resources
	public void destroy() {
	}
}
