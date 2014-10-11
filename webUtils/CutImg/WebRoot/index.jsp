<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>截图页面</title>
	<link href="css/main.css" type="text/css" rel="Stylesheet"/>
    <script type="text/javascript" src="js/jquery1.2.6.pack.js"></script>
    <script  type="text/javascript" src="js/ui.core.packed.js" ></script>
    <script type="text/javascript" src="js/ui.draggable.packed.js" ></script>
    <script type="text/javascript" src="js/CutPic.js"></script>
    <script type="text/javascript">
        function Step1() {
            $("#Step2Container").hide();           
        }

        function Step2() {
            $("#CurruntPicContainer").hide();
        }
        function Step3() {
            $("#Step2Container").hide();          
       }
	   function show()
	   {
			alert("实际宽度："+$("#txt_width")[0].value+"实际高度："+$("#txt_height")[0].value+"距离顶部："+$("#txt_top")[0].value+"距离左边："+$("#txt_left")[0].value+"截取框的宽："+$("#txt_DropWidth")[0].value+"截取框的高："+$("#txt_DropHeight")[0].value+"放大倍数："+$("#txt_Zoom")[0].value);
	   }
	   
	   function sub(obj)
	   {
	   		$("#flag")[0].value=obj;
	   		document.forms[0].action="Service";
	   		document.forms[0].method="post";
	   		document.forms[0].submit();
	   }
    </script>
  </head>
  
  <body onload="Step2()">
  <form action="Service" method="post">
  <% 
		String path="";
		if(request.getParameter("path")!=null)
		{
			path=request.getParameter("path");
		}
  %>
  <input type="hidden" value="" name="flag" id="flag"/>
  <input type="hidden" value="<%=path %>" name="img" id="img"/>
    <div class="left">
         <!--当前照片-->
         <div id="CurruntPicContainer">
            <div class="title"><b>当前照片</b></div>
            <div class="photocontainer">
                <asp:Image ID="imgphoto" runat="server" ImageUrl="~/image/man.GIF" />
            </div>
         </div>
         <!--Step 2-->
         <div id="Step2Container">
           <div class="title"><b> 裁切头像照片</b></div>
           <div class="uploadtooltip">您可以拖动照片以裁剪满意的头像</div>                              
                   <div id="Canvas" class="uploaddiv">
                            <div id="ImageDragContainer">                               
                               <img ID="ImageDrag" src="<%=path %>" class="imagePhoto" />               
                            </div>
                            <div id="IconContainer">
                               <img ID="ImageIcon" src="<%=path %>" class="imagePhoto" />                        
                            </div>                 
                    </div>
                    <div class="uploaddiv">
                       <table>
                            <tr>
                                <td id="Min">
                                        <img alt="缩小" src="image/_c.gif" onmouseover="this.src='image/_c.gif';" onmouseout="this.src='image/_h.gif';" id="moresmall" class="smallbig" />
                                </td>
                                <td>
                                    <div id="bar">
                                        <div class="child">
                                        </div>
                                    </div>
                                </td>
                                <td id="Max">
                                        <img alt="放大" src="image/c.gif" onmouseover="this.src='image/c.gif';" onmouseout="this.src='image/h.gif';" id="morebig" class="smallbig" />
                                </td>
                            </tr>
                        </table>
                    </div>
                    <div class="uploaddiv">
                    	<input type="button" id="btn_Image" value="保存头像" onclick="sub('cutImg')" />
                    </div>           
                    <div>
				                    图片实际宽度： <input type="TextBox" ID="txt_width" name="txt_width" value="1" /><br />
				                    图片实际高度： <input type="TextBox" ID="txt_height" name="txt_height" value="1" /><br />
				                    距离顶部：  <input type="TextBox" ID="txt_top" name="txt_top" value="82" /><br />
				                    距离左边：  <input type="TextBox" ID="txt_left" name="txt_left" value="73" /><br />
				                    截取框的宽：<input type="TextBox" ID="txt_DropWidth" name="txt_DropWidth" value="120" /><br />
				                    截取框的高：<input type="TextBox" ID="txt_DropHeight" name="txt_DropHeight" value="120" /><br />
				                    放大倍数： <input type="TextBox" ID="txt_Zoom" />
                   </div>
         	</div>
     	</div>
     	<input type="file" name="imgFile"/><input type="button" value="上传" onclick="sub('upImg')"/>
     </form>
  </body>
</html>
