<%@ page contentType="text/html;charset=GBK"%>
<%@ page import="org.jfree.chart.ChartFactory"%>
<%@ page import="org.jfree.chart.JFreeChart,org.jfree.chart.plot.PlotOrientation"%>
<%@ page import="org.jfree.chart.servlet.ServletUtilities"%>
<%@ page import="org.jfree.data.category.*"%>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=GBK">
<TITLE>柱状图</TITLE>
</HEAD>

<body>


<%
	CategoryDataset dataset;
	String category = request.getParameter("category");
	category = new String(category.getBytes("ISO8859_1"), "GBK");
	if (category.equals("本科") || category.equals("高中")
			|| category.equals("大专")) {
		dataset = getDataSet();
	} else if (category.equals("硕士") || category.equals("博士")) {
		dataset = getDataSet2();
	} else {
		dataset = getDataSet3();
	}
	String title = category + "程序员在各城市薪金情况统计";
	JFreeChart chart = ChartFactory.createBarChart3D(title, "城市", "薪金",
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
		dataset.addValue(2000, "上海", "VB");
		dataset.addValue(3000, "北京", "JAVA");
		dataset.addValue(3330, "上海", "JAVA");
		dataset.addValue(3500, "广州", "JAVA");
		dataset.addValue(3500, "北京", "DOT NET");
		dataset.addValue(4000, "上海", "DOT NET");
		dataset.addValue(4800, "广州", "DOT NET");
		dataset.addValue(2600, "北京", "DELPHI");
		dataset.addValue(2200, "上海", "DELPHI");
		dataset.addValue(4000, "北京", "VC");
		dataset.addValue(4000, "上海", "VC");
		dataset.addValue(4200, "广州", "VC");
		return dataset;
	}

	private static CategoryDataset getDataSet3() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(2100, "北京", "VB");
		dataset.addValue(2200, "上海", "VB");
		dataset.addValue(2100, "广州", "VB");
		dataset.addValue(3000, "北京", "JAVA");
		dataset.addValue(3200, "上海", "JAVA");
		dataset.addValue(3600, "广州", "JAVA");
		dataset.addValue(4100, "北京", "DOT NET");
		dataset.addValue(4200, "上海", "DOT NET");
		dataset.addValue(4160, "广州", "DOT NET");
		dataset.addValue(2400, "北京", "DELPHI");
		dataset.addValue(2600, "上海", "DELPHI");
		dataset.addValue(2500, "广州", "DELPHI");
		dataset.addValue(5400, "北京", "VC");
		dataset.addValue(5000, "上海", "VC");
		dataset.addValue(5500, "广州", "VC");
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
