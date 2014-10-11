import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Echo extends HttpServlet {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1643098998783772132L;

	/**
	 * Constructor of the object.
	 */
	public Echo() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out
				.println("---------------------start headers----------------------");
		PrintWriter out = response.getWriter();
		Enumeration kk = request.getHeaderNames();
		while (kk.hasMoreElements()) {
			String key = (String) kk.nextElement();
			out.print(key + "=" + request.getHeader(key));
			System.out.println(key + "=" + request.getHeader(key));
		}
		BufferedReader d = new BufferedReader(new InputStreamReader(request
				.getInputStream()));
		String line;
		System.out
		.println("---------------------start xml----------------------");
		while ((line = d.readLine()) != null) {
			out.println(line);
			System.out.println(line);
		}
		d.close();
		out.flush();
		out.close();

	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occure
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
