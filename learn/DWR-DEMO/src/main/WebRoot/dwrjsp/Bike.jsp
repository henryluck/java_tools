<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>The first dwr programme</title>
<script type='text/javascript' src='/DWR-DEMO/dwr/interface/Bike.js'></script>
<script type='text/javascript' src='/DWR-DEMO/dwr/engine.js'></script>
<script type='text/javascript' src='/DWR-DEMO/dwr/util.js'></script>
<SCRIPT LANGUAGE="JavaScript">
<!--
function refreshYearList() {
    Bike.getYears(populateYearList);
}

function populateYearList(list){
     DWRUtil.removeAllOptions("years");
     DWRUtil.addOptions("years", list);
     refreshBikeList();
} 
function refreshBikeList() {
    var year = $("years").value;
    Bike.getBikes(year, populateBikeList);
}

function populateBikeList(list){
    DWRUtil.removeAllOptions("bikes");
    DWRUtil.addOptions("bikes", list);
}
//-->
</SCRIPT>
</head>
<body onload="refreshYearList()">
年份：
<select id="years" onchange="refreshBikeList();"></select>
<br />
<br />
型号：
<select id="bikes"></select>
<br />
</body>
</html>
