<%@ page contentType="text/html;charset=GBK"%>
<%@ page import="org.jfree.data.general.DefaultPieDataset"%>
<%@ page import="org.jfree.chart.*"%>
<%@ page import="org.jfree.chart.plot.*"%>
<%@ page import="org.jfree.chart.servlet.ServletUtilities"%>
<%@ page import="org.jfree.chart.labels.StandardPieItemLabelGenerator"%>
<%@ page import="org.jfree.chart.urls.StandardPieURLGenerator"%>
<%@ page import="org.jfree.chart.entity.StandardEntityCollection"%>
<%@ page import="java.io.*"%>
<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=GBK">
<TITLE>jfreechar����</TITLE>
</HEAD>
<BODY>
<%
	DefaultPieDataset data = new DefaultPieDataset();
	data.setValue("��������", 370);
	data.setValue("����", 1530);
	data.setValue("��ר", 5700);
	data.setValue("����", 8280);
	data.setValue("˶ʿ", 4420);
	data.setValue("��ʿ", 80);

	PiePlot3D plot = new PiePlot3D(data);//3D��ͼ 
	plot.setURLGenerator(new StandardPieURLGenerator("barview.jsp"));//�趨���� 
	JFreeChart chart = new JFreeChart("",
			JFreeChart.DEFAULT_TITLE_FONT, plot, true);
	chart.setBackgroundPaint(java.awt.Color.white);//��ѡ������ͼƬ����ɫ 
	chart.setTitle("����Աѧ����������");//��ѡ������ͼƬ���� 
	plot.setToolTipGenerator(new StandardPieItemLabelGenerator());
	StandardEntityCollection sec = new StandardEntityCollection();
	ChartRenderingInfo info = new ChartRenderingInfo(sec);
	PrintWriter w = new PrintWriter(out);//���MAP��Ϣ 
	//500��ͼƬ���ȣ�300��ͼƬ�߶� 
	String filename = ServletUtilities.saveChartAsJPEG(chart, 500, 300,
			info, session);
	ChartUtilities.writeImageMap(w, "map0", info, false);

	String graphURL = request.getContextPath()
			+ "/servlet/DisplayChart?filename=" + filename;
	out.println(System.getProperties());
%>

<P ALIGN="CENTER"><img src="<%= graphURL %>" width=500 height=300
	border=0 usemap="#map0"></P>
</BODY>
</HTML>
