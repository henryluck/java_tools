/**
 * 
 */
package jlx.ajax.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jlx.ajax.test.Message;

/**
 * @author Administrator
 * 
 */
public class ChatRoomServlet {

	/**
	 * 
	 */
	private static LinkedList messages = new LinkedList();

	public ChatRoomServlet() {
		super();
	}

	private List addMessage(String text) {
		if (text != null && text.trim().length() > 0) {
			messages.addFirst(new Message(text));
			while (messages.size() > 10) {
				messages.removeLast();
			}
		}
		return messages;
	}

	private List getMessages() {
		return messages;
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List list = null;
		if ("send".equals(request.getParameter("task"))) {
			list = addMessage(request.getParameter("msg"));
		} else if ("query".equals(request.getParameter("task"))) {
			list = getMessages();
		}
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml");
		response.setHeader("Cache-Control", "no-cache");
		out.println("<response>");
		for (int i = 0; i < list.size(); i++) {
			String msg = ((Message) list.get(i)).getText();
			out.println("<message>" + msg + "</message>");
		}
		out.println("</response>");
	}
}
