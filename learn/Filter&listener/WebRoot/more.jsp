<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.io.File"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.net.URL"%>
<%@ page import="java.io.BufferedReader"%>
<%@ page import="java.io.InputStreamReader"%>
<%@ page import="java.io.FileWriter"%>


<%
Date d = new Date();
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd") ;
//要保存的文件名
String FileName = sdf.format(d)+".html";
//web应用的上下文
String path = request.getContextPath();
//jsp文件的客户端访问路径文件夹
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/k-portal/emis/news/";
//目标文件物理路径
String DestFile = "/opt/SUNWps/web-apps/https-kportal.js.cmcc/portal/k-portal/emis/news/cache/" + FileName;
//要转换的jsp的访问全路径路径
String jspurl = basePath + "more_file.jsp?CFGPath=/opt/SUNWam/lib/portlets.cfg&cfgProperties=/opt/SUNWam/lib/AutonomyPortletconfig.properties";
//访问缓存文件的http路径
String cachefile = basePath + "cache/" + FileName;
%>

<%

	
	File f = new File(DestFile);
	if(f.exists()){
		response.sendRedirect(cachefile);
	}else{

		URL url = new URL(jspurl);		
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(),"utf-8"));
		
		String line = "";
		StringBuffer all = new StringBuffer("");
		FileWriter fw = new FileWriter(f);
		
		//过滤文件开始可能产生的乱码
		while ((line = in.readLine()) != null) {
			if(line.indexOf("html") != -1){
				all.append(line).append("\r\n");
				break;
			}
		}
		while ((line = in.readLine()) != null) {
			all.append(line).append("\r\n");
		}
		//为了确保读完,多加一个循环
		while ((line = in.readLine()) != null) {
			all.append(line).append("\r\n");
		}
		fw.write(all.toString() + "\r\n");
		
		fw.flush();
		fw.close();
		response.sendRedirect(cachefile);
	}
%>