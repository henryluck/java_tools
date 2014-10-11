<%@ page contentType="text/html;charset=GBK"%>
<%@ page import="org.jfree.chart.ChartFactory"%>
<%@ page import="org.jfree.chart.JFreeChart,org.jfree.chart.plot.PlotOrientation"%>
<%@ page import="org.jfree.chart.servlet.ServletUtilities"%>
<%@ page import="org.jfree.data.category.*"%>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=GBK">
<TITLE>��״ͼ</TITLE>
</HEAD>

<body>


<%
	CategoryDataset dataset;
	String category = request.getParameter("category");
	category = new String(category.getBytes("ISO8859_1"), "GBK");
	if (category.equals("����") || category.equals("����")
			|| category.equals("��ר")) {
		dataset = getDataSet();
	} else if (category.equals("˶ʿ") || category.equals("��ʿ")) {
		dataset = getDataSet2();
	} else {
		dataset = getDataSet3();
	}
	String title = category + "����Ա�ڸ�����н�����ͳ��";
	JFreeChart chart = ChartFactory.createBarChart3D(title, "����", "н��",
			dataset, PlotOrientation.VERTICAL, true, false, false);

	String filename = ServletUtilities.saveChartAsPNG(chart, 800, 300,
			null, session);
	String graphURL = request.getContextPath()
			+ "/servlet/DisplayChart?filename=" + filename;
%>
<P ALIGN="CENTER"><img src="<%= graphURL %>" width=800 height=300
	border=0 usemap="#<%= filename %>"></P>
<%!private static CategoryDataset getDataSet() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(2000, "", dealWithduplicate(dataset,"VB"));
		dataset.addValue(1800, "", dealWithduplicate(dataset,"VB"));
		dataset.addValue(2200, "", dealWithduplicate(dataset,"VB"));
		dataset.addValue(22000, "", dealWithduplicate(dataset,"VB"));
		dataset.addValue(3200, "", dealWithduplicate(dataset,"JAVA"));
		dataset.addValue(3500, "", dealWithduplicate(dataset,"JAVA"));
		dataset.addValue(3600, "", dealWithduplicate(dataset,"JAVA"));
		dataset.addValue(3300, "", dealWithduplicate(dataset,"JAVA"));
		dataset.addValue(3400, "", dealWithduplicate(dataset,"JAVA"));
		dataset.addValue(3700, "", dealWithduplicate(dataset,"JAVA"));
		dataset.addValue(2500, "", "DELPHI");
		dataset.addValue(2800, "", "DELPHI");
		dataset.addValue(3200, "", "DELPHI");
		dataset.addValue(5000, "", "VC");
		dataset.addValue(3500, "", "VC");
		dataset.addValue(4600, "", "VC");
		System.out.println(dataset.getColumnIndex("JAVA "));
		return dataset;
	}

	private static CategoryDataset getDataSet2() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(2000, "�Ϻ�", "VB");
		dataset.addValue(3000, "����", "JAVA");
		dataset.addValue(3330, "�Ϻ�", "JAVA");
		dataset.addValue(3500, "����", "JAVA");
		dataset.addValue(3500, "����", "DOT NET");
		dataset.addValue(4000, "�Ϻ�", "DOT NET");
		dataset.addValue(4800, "����", "DOT NET");
		dataset.addValue(2600, "����", "DELPHI");
		dataset.addValue(2200, "�Ϻ�", "DELPHI");
		dataset.addValue(4000, "����", "VC");
		dataset.addValue(4000, "�Ϻ�", "VC");
		dataset.addValue(4200, "����", "VC");
		return dataset;
	}

	private static CategoryDataset getDataSet3() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(2100, "����", "VB");
		dataset.addValue(2200, "�Ϻ�", "VB");
		dataset.addValue(2100, "����", "VB");
		dataset.addValue(3000, "����", "JAVA");
		dataset.addValue(3200, "�Ϻ�", "JAVA");
		dataset.addValue(3600, "����", "JAVA");
		dataset.addValue(4100, "����", "DOT NET");
		dataset.addValue(4200, "�Ϻ�", "DOT NET");
		dataset.addValue(4160, "����", "DOT NET");
		dataset.addValue(2400, "����", "DELPHI");
		dataset.addValue(2600, "�Ϻ�", "DELPHI");
		dataset.addValue(2500, "����", "DELPHI");
		dataset.addValue(5400, "����", "VC");
		dataset.addValue(5000, "�Ϻ�", "VC");
		dataset.addValue(5500, "����", "VC");
		return dataset;
	}
	private static String dealWithduplicate(CategoryDataset dataset, String itemName) {
		int t_rowIndex = dataset.getColumnIndex(itemName);
		if (t_rowIndex >= 0) {
			itemName = dealWithduplicate(dataset, " " + itemName + " ");
		}
		return itemName;
	}
	%>
</body>
</html>
