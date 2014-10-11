<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>The first dwr programme</title>
<script type='text/javascript' src='/dwr/dwr/interface/OPT.js'></script>
<script type='text/javascript' src='/dwr/dwr/engine.js'></script>
<script type='text/javascript' src='/dwr/dwr/util.js'></script>
<SCRIPT LANGUAGE="JavaScript">
<!--
window.onload = function() {
    OPT.getOptions(populate);
 }; 
function populate(list){
    DWRUtil.removeAllOptions("opts");
    DWRUtil.addOptions("opts", list);
} 
//-->
</SCRIPT>
</head>
<body>
<select id="opts"></select>
</body>
</html>
