<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta content="text/html; charset=UTF-8" http-equiv="content-type" />
    <title>
      签报
    </title>
    <style> 
      #fromstyle td{
      font-size: 12px;
      color: #333333;
      text-decoration: none;
      height: 28px;
      font-family:"Arial","Helvetica","sans-serif";
      padding-left: 4px;
      padding-top: 2px;
      padding-right: 2px;
      padding-bottom: 2px;
      }
    </style>
    <script>
    window.
    </script>
  </head>
  <body>
    <form style="margin-left: 92px; width: 768px;" method="get" action="1" name="签报">
      <br />
      <table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tbody>
          <tr align="center">
            <td valign="undefined">
              <b><big>
                <big>
                  <font color="red">
                    <font size="5">
                      <big>
                        <big>
                          签
                          报
                        </big>
                      </big>
                    </font>
                  </font>
                </big>
              </big></b>
            </td>
          </tr>
        </tbody>
      </table>
      <br />
      <table bordercolorlight="#AAB9C6" bordercolordark="#ffffff" id="fromstyle" border="1" cellpadding="1" cellspacing="0" width="90%">
        <tbody>
          <tr>
            <td align="center" valign="middle" width="109">
              <small>
                文
                件标题
              </small>
            </td>
            <td colspan="5" rowspan="1" align="undefined" valign="middle" width="130">
              <input roletype="0`0`0" usetype="0`0`0`0" cssclass="template-chulidan-input-normal" name="wenjianbiaoti" special="DocumentName" type="text" />
            </td>
          </tr>
          <tr>
            <td align="center" valign="middle" width="109">
              <small></small>
              <small>
                主
                办部门
              </small>
              <big>
                <br />
              </big>
              <small></small>
            </td>
            <td align="undefined" valign="middle" width="130">
              <input desc="办公室" cssclass="template-chulidan-input-normal" name="nigaodanwei" special="Department" value="办公室" type="text" />
            </td>
            <td align="center" valign="middle" width="80">
              <small></small>
              <small>
                拟
                稿 人
              </small>
              <big>
                <br />
              </big>
              <small></small>
            </td>
            <td align="undefined" valign="middle" width="162">
              <input cssclass="template-chulidan-input-normal" name="nigaoren" special="DocumentAuthor" type="text" />
            </td>
            <td align="center" valign="middle" width="80">
              <small></small>
              <small>
                拟
                稿时间
              </small>
              <big>
                <br />
              </big>
              <small></small>
            </td>
            <td align="undefined" valign="middle" width="181">
              <input desc="2006年2月21日" cssclass="template-chulidan-input-normal" name="nigaoshijian" special="SponsorDate" value="2006年2月21日" type="text" />
            </td>
          </tr>
          <tr>
            <td align="center" valign="middle" width="109">
              <small></small>
              <small>
                文
                件编号
              </small>
              <big>
                <br />
              </big>
              <small></small>
            </td>
            <td align="undefined" valign="middle" width="130">
              <input cssclass="template-chulidan-input-normal" name="wenjianbianhao" special="DocumentNumber" type="text" />
            </td>
            <td align="center" valign="middle" width="80">
              <small></small>
              <small>
                密
                &nbsp; &nbsp;级
              </small>
              <big>
                <br />
              </big>
              <small></small>
            </td>
            <td align="undefined" valign="middle" width="162">
              <select roletype="0`0`0" usetype="0`0`0`0" cssclass="template-chulidan-input-normal" name="miji" special="DocumentSecret" type="List"></select>
            </td>
            <td align="center" valign="middle" width="80">
              <small></small>
              <small>
                缓
                &nbsp;&nbsp; 急
              </small>
              <big>
                <br />
              </big>
              <small></small>
            </td>
            <td align="undefined" valign="middle" width="181">
              <select roletype="0`0`0" usetype="0`0`0`0" cssclass="template-chulidan-input-normal" name="huanji" special="DocumentPriority" type="List"></select>
            </td>
          </tr>
        </tbody>
      </table>
      <br />
      <table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tbody>
          <tr align="center">
            <td colspan="2" rowspan="1" valign="middle">
              <center><b><big>
                <big>
                  <font color="red">
                    <font size="5">
                      <big>
                        <big>
                          签
                          报
                        </big>
                      </big>
                    </font>
                  </font>
                </big>
              </big></b></center>
            </td>
          </tr>
          <tr align="center">
            <td colspan="2" rowspan="1" valign="middle">
              <input desc="2006001" cssclass="template-chulidan-input-normal" name="wenjianbianhao" special="DocumentNumber" value="2006001" type="text" />
            </td>
          </tr>
          <tr>
            <td colspan="2" rowspan="1" align="undefined" valign="middle">
              <hr size="1" width="100%" />
            </td>
          </tr>
          <tr>
            <td style="height: 41px;" colspan="2" rowspan="1" align="undefined" valign="middle" width="464">
              <br />
              <br />
              <tc:content name="content"></tc:content>
              <br />
              <br />
            </td>
          </tr>
          <tr>
            <td colspan="2" rowspan="1" align="undefined" valign="middle">
              <hr style="height: 3px; width: 100%;" size="1" width="100%" />
            </td>
          </tr>
        </tbody>
      </table>
      <br />
    </form>
  </body>
</html>
