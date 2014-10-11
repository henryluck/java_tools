package dao;

import java.io.IOException;
import java.io.PrintWriter;
import dao.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Service extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		//PrintWriter out = response.getWriter();
		//out.flush();
		//out.close();
		CommonMethod common=new CommonMethod();
		String flag=request.getParameter("flag");
		ServletContext context = getServletContext();
		String basePath = context.getRealPath("/");
		if(flag.equals("upImg"))
		{
			String img="";
			if(request.getParameter("imgFile")!=null)
			{
				img = request.getParameter("imgFile");
				String path = common.Upload(img,basePath+"tmp");
				response.sendRedirect("index.jsp?path="+path);
			}
		}
		else if(flag.equals("cutImg"))
		{
			int imgWith=Convert(request.getParameter("txt_width"),response);
			int imgHeight=Convert(request.getParameter("txt_height"),response);
			int left=Convert(request.getParameter("txt_left"),response);
			int top=Convert(request.getParameter("txt_top"),response);
			int cutWidth=Convert(request.getParameter("txt_DropWidth"),response);
			int cutHeight=Convert(request.getParameter("txt_DropHeight"),response);
			String path=request.getParameter("img");
			common.CutImg(imgWith, imgHeight, left, top, cutWidth, cutHeight,basePath+path,basePath+"tmp");
			response.sendRedirect("index.jsp");
		}
	}
	
	public int Convert(String wh,HttpServletResponse response)throws ServletException, IOException
	{
		if(wh!=null)
		{
			return Integer.parseInt(wh);
		}
		else
		{
			response.sendRedirect("index.jsp");
			return 0;
		}
	}
	

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doGet(request,response);
	}

}
