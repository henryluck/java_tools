/**  @(#)ValidateAgent.java  Jan 6, 2006
 * 
 * All rights reserved. 
 * */

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.autonomy.client.aciObject;
import com.autonomy.client.client;

/**
 * @author ����jianglx@sc.mdcl.com.cn
 * @version
 */
public class ValidateAgent {

	public static String UAHost = "192.168.17.200";

	public static String UAPort = "7003";

	public static client aciClient = new client();

	public static aciObject acioUAConnection = null;

	/**
	 * 
	 */
	public ValidateAgent() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * �õ�acioUAConnection
	 * 
	 * @return acioUAConnection
	 */
	public static aciObject getUAConnection() {

		aciClient = new client();
		acioUAConnection = aciClient.aciObjectCreate(aciObject.ACI_CONNECTION);
		acioUAConnection.paramSetString(aciObject.ACI_HOSTNAME, UAHost);
		acioUAConnection.paramSetString(aciObject.ACI_PORTNUMBER, UAPort);
		acioUAConnection.setTimeout(2000);
		if(acioUAConnection != null) {
			if(acioUAConnection.isAlive()) {
				return acioUAConnection;
			}
		}
		return null;
	}

	/**
	 * ȡ��UA 读取UAServer所有的用户
	 * 
	 * @return �û����б�
	 */
	public static List getAllUser() {

		List list = new ArrayList();

		// ׼��acioCommand
		aciObject acioCommand = aciClient
				.aciObjectCreate(aciObject.ACI_COMMAND);
		acioCommand.paramSetBool(aciObject.ACI_COM_USE_POST, true);
		acioCommand.paramSetString(aciObject.ACI_COM_COMMAND,
				"ACTION=RoleGetUserList");
		acioCommand.paramSetBool(aciObject.ACI_COM_METHOD, true);
		acioCommand.paramSetString("RoleName", "everyone");

		if(getUAConnection() == null)
			return null;

		aciObject acioResults = acioUAConnection.aciObjectExecute(acioCommand);
		aciObject aciResdata = acioResults.findFirstOccurrence("autn:user");
		while (aciResdata != null) {
			String username = aciResdata.getTagValue("autn:user");
			list.add(username);
			aciResdata = aciResdata.aciObjectNextEntry();
		}

		return list;

	}

	public static List BadAgentUsername() {
		
		List badList = new ArrayList();
		
		List list = getAllUser();
		String username;
		if(list != null) {
			for (int i = 1; i < list.size(); i++) {
				username = (String) list.get(i);
				System.out.println("current check user:" + username);
				if(validate(username) == false){
					badList.add(username);
				}
			}
		}
		
		return badList;
	}

	/**
	 * �ж��û���agent�Ƿ�ȫ���Ϸ�
	 * 
	 * @param username
	 *            �жϵ��û���
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static boolean validate(String username){

		// ׼��acioCommand
		aciObject acioCommand = aciClient
				.aciObjectCreate(aciObject.ACI_COMMAND);
		acioCommand.paramSetBool(aciObject.ACI_COM_USE_POST, true);
		acioCommand.paramSetString(aciObject.ACI_COM_COMMAND,
				"ACTION=userreadagentlist");
		acioCommand.paramSetBool(aciObject.ACI_COM_METHOD, true);
		acioCommand.paramSetString("username", username);

		if(getUAConnection() == null)
			return false;

		// ִ��command
		aciObject acioResults = acioUAConnection.aciObjectExecute(acioCommand);

		String results = acioResults.toString();
		try {
			results = new String(results.getBytes("iso-8859-1"), "utf-8");
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		results = results.substring(results.indexOf("<autnresponse"), results
				.indexOf("</autnresponse>") + 15);

		if(results.indexOf("\ufffd") == -1) {
			return true;
		}

		return false;
	}

	/**
	 * Stringtounicode
	 * 
	 * @param asString
	 * @return
	 */
	public static final String StringToUnicode(String asString) {
		char[] ac = asString.toCharArray();
		int iValue;
		String s = null;
		StringBuffer sb = new StringBuffer();
		for (int ndx = 0; ndx < ac.length; ndx++) {
			iValue = ac[ndx];
			if(iValue < 0x10) {
				s = "\\u000";
			}else if(iValue < 0x100) {
				s = "\\u00";
			}else if(iValue < 0x1000) {
				s = "\\u0";
			}else {
				s = "\\u";
			}
			sb.append(s + Integer.toHexString(iValue));
		}
		return sb.toString();
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		List list = BadAgentUsername();
		System.out.println(">>>>>>>>>>>>>>>>>>>>Agent 含有非法字符的用户有：<<<<<<<<<<<<<<<<<<<<<<<");
		for (int i = 1; i < list.size(); i++) {
			System.out.println((String) list.get(i));
		}
		System.out.println(">>>>>>>>>>>>>>>>>>>>end<<<<<<<<<<<<<<<<<<<<<<<");
		System.out.println("按回车键（Enter）退出....");
		new InputStreamReader(System.in).read();

	}

}
