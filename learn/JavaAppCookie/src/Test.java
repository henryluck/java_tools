/*
 * Sort.java<br>
 * 2006-8-9<br>
 * Copyright (c) 2003-2006 MDCL-FRONTLINE, Inc.<br>
 * All rights reserved.
 */

import java.util.ArrayList;
import java.util.Iterator;

/**
 * <p>
 * Title: Sort.java
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2003-2006
 * </p>
 * <p>
 * Company: MDCL-FRONTLINE, Inc.
 * </p>
 * <p>
 * 修改历史:<br>
 * 修改人 修改日期 修改描述<br>
 * -------------------------------------------<br>
 * <br>
 * <br>
 * </p>
 * 
 * @author yangjl
 * @version 1.0<br>
 */
public class Test {

	public Test() {

	}

	public static void main(String args[]) {
		String GET_MY_WORK_NEED_TO_READ = new StringBuffer()
		.append(" SELECT T.READLOG_ID,B.TEMPLATE_ID, ")
		.append(" T.USER_NAME,B.TEMPLATE_VER, ")
		.append(" B.DOCUMENT_ID, ")
		.append(" B.DOCUMENT_STATE AS ST, ")
		.append(" B.CURR_ACTIVITY, ")
		.append(" B.CURR_USER, ")
		.append(" B.DOCUMENT_PRIORITY, ")
		.append(" B.DOCUMENT_TITLE, ")
		.append(" FET.TEMPLATE_TYPE_NAME, ")
		.append(" T.RECEIVE_TIME AS SENDTIME, ")
		.append(" T.SENDER_NAME AS SUBMITNAME, ")
		.append(" T.IDENTITY, ")
		.append(" B.FINISH_FLAG AS FF, ")
		.append(" T.ACTIVITY_NAME AS WORKFLOWNAME, ")
		.append(" E.NAME, ")
		.append(" IO.ORG_NAME ")
		.append(" FROM MOCHA_FE_DOCUMENT_BODY B, ")
		.append(" (SELECT S.READLOG_ID,S.DOC_ID, ")
		.append(" S.USER_ID, ")
		.append(" S.RECEIVE_TIME, ")
		.append(" S.IDENTITY,")
		.append(" S.ACTIVITY_NAME,S.USER_NAME, ")
		.append(" S.SENDER_NAME ")
		.append(
				" FROM (Select DOC_ID, USER_ID, MAX(RECEIVE_TIME) AS RECEIVE_TIME ")
		.append(" FROM MOCHA_FE_DOC_TOREAD ")
		.append(" GROUP BY DOC_ID, uSer_id) I ")
		.append(
				" LEFT JOIN MOCHA_FE_DOC_TOREAD S ON S.RECEIVE_TIME = I.RECEIVE_TIME ")
		.append(" AND S.DOC_ID = I.DOC_ID ").append(
				" AND S.USER_ID = I.USER_ID) T, ").append(
				" MOCHA_FE_CODE_SCHEMA_EXIGENT E, ").append(
				" MOCHA_FE_TEMPLATE_DEFINITION FED, ").append(
				" MOCHA_FE_TEMPLATE_TYPE FET, ").append(
				" MOCHA_IM_PERSON_POSITION PP, ").append(
				" MOCHA_IM_ORG IO ").append(
				" WHERE B.DOCUMENT_ID = T.DOC_ID ").append(
				" AND B.DOCUMENT_PRIORITY = E.ID ").append(
				" AND B.DOCUMENT_STATE <> 300 ").append(
				" AND T.USER_ID = ? ").append(
				" AND T.USER_ID = PP.USER_ID ").append(
				" AND PP.ORG_ID = IO.ORG_ID ").append(
				" AND FED.TEMPLATE_ID = B.TEMPLATE_ID ").append(
				" AND FET.TEMPLATE_TYPE_ID = FED.TEMPLATE_TYPE_ID ")
		.toString();
		System.out.println(GET_MY_WORK_NEED_TO_READ);
	}
}
