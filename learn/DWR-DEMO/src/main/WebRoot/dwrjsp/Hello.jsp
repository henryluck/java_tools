<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>The first dwr programme</title>
<script type='text/javascript' src='/dwr/dwr/interface/Hello.js'></script>
<script type='text/javascript' src='/dwr/dwr/engine.js'></script>
<script type='text/javascript' src='/dwr/dwr/util.js'></script>
<SCRIPT LANGUAGE="JavaScript">
<!--
function hello() {
	var user = $('user').value;
    Hello.hello(user, callback);
} 
function callback(msg) {
   DWRUtil.setValue('result', msg);
}
//-->
</SCRIPT>
</head>
<body>
<input id="user" type="text" />
<input type='button' value='HELLO' onclick='hello();' />
<div id="result"></div>
</body>
</html>
