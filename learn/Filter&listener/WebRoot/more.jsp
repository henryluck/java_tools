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
//Ҫ������ļ���
String FileName = sdf.format(d)+".html";
//webӦ�õ�������
String path = request.getContextPath();
//jsp�ļ��Ŀͻ��˷���·���ļ���
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/k-portal/emis/news/";
//Ŀ���ļ�����·��
String DestFile = "/opt/SUNWps/web-apps/https-kportal.js.cmcc/portal/k-portal/emis/news/cache/" + FileName;
//Ҫת����jsp�ķ���ȫ·��·��
String jspurl = basePath + "more_file.jsp?CFGPath=/opt/SUNWam/lib/portlets.cfg&cfgProperties=/opt/SUNWam/lib/AutonomyPortletconfig.properties";
//���ʻ����ļ���http·��
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
		
		//�����ļ���ʼ���ܲ���������
		while ((line = in.readLine()) != null) {
			if(line.indexOf("html") != -1){
				all.append(line).append("\r\n");
				break;
			}
		}
		while ((line = in.readLine()) != null) {
			all.append(line).append("\r\n");
		}
		//Ϊ��ȷ������,���һ��ѭ��
		while ((line = in.readLine()) != null) {
			all.append(line).append("\r\n");
		}
		fw.write(all.toString() + "\r\n");
		
		fw.flush();
		fw.close();
		response.sendRedirect(cachefile);
	}
%>