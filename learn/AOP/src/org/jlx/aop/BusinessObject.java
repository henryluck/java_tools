package org.jlx.aop;

/**
 * <strong>Title : BusinessObject<br>
 * </strong> <strong>Description : </strong><br>
 * <strong>Create on : 2008-4-16<br>
 * </strong>
 * <p>
 * <strong>Copyright (C) Mocha Software Co.,Ltd.<br>
 * </strong>
 * <p>
 * 
 * @author jianglx jianglx@mochasoft.com.cn<br>
 * @version <strong>test</strong><br>
 *          <br>
 *          <strong>修改历史:</strong><br>
 *          修改人 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */
public class BusinessObject implements BusinessInterface {
	public void processBusiness() {
		// business processing
		System.out.println("here is business logic");
	}
}
