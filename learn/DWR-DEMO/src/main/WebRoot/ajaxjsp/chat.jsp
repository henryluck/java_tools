<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Chat Room</title>
<script type="text/javascript">
var xmlHttp; 
function createXMLHttpRequest() {
    if (window.ActiveXObject) {
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    }else if (window.XMLHttpRequest) {
		xmlHttp = new XMLHttpRequest();
    }
}
function sendMessage() {
	var msg = document.getElementById("text").value;
	if(msg == "") {
		refreshMessage();
		return;
	}
	var param = "task=send&msg=" + msg;
	ajaxRequest(param);
	document.getElementById("text").value = "";
}
function queryMessage() {
	var param = "task=query";
	ajaxRequest(param);
} 
function ajaxRequest(param) {  
	var url = "/dwr/ChatRoomServlet?timestamp" + new Date().getTime();
	alert(url);
	createXMLHttpRequest();  xmlHttp.onreadystatechange = refreshMessage;    
	xmlHttp.open("POST", url, true);  
	xmlHttp.setRequestHeader("Content-Type",           "application/x-www-form-urlencoded;");    
	xmlHttp.send(param);
}  
function refreshMessage() {  
	if(xmlHttp.readyState == 4) {        
		if(xmlHttp.status == 200) {          
			var messages = xmlHttp.responseXML.getElementsByTagName("message");            
			var table_body = document.getElementById("dynamicUpdateArea");     
			var length = table_body.childNodes.length;      
			for (var i = 0; i < length; i++) {        
				table_body.removeChild(table_body.childNodes[0]);      
			}            
			var length = messages.length;          
			for(var i = length - 1; i >= 0 ; i--) {              
				var message = messages[i].firstChild.data;              
				var row = createRow(message);                      
				table_body.appendChild(row);                                  
			}      
			setTimeout("queryMessage()", 2000);        
		}  
	}
} 
function createRow(message) {    
	var row = document.createElement("tr");    
	var cell = document.createElement("td");    
	var cell_data = document.createTextNode(message);    
	cell.appendChild(cell_data);    
	row.appendChild(cell);    
	return row;
}
</script>
</head>
<body>
<p>Your Message: <input id="text" /> <input type="button"
	value="Send" onclick="sendMessage()" /></p>
<p>Messages:</p>
<table align="left">
	<tbody id="dynamicUpdateArea"></tbody>
</table>
</body>
</html>
