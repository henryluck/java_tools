/**
 * 
 */
package com.jlx.javascript;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextFactory;
import org.mozilla.javascript.Scriptable;

/**
 * 用Rhino，执行javascript脚本
 * 
 * @author jianglx
 * 
 */
public class RunJS {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// javascript 脚本，可以从文件读入
		StringBuffer sb = new StringBuffer();
		sb.append("if(x>y)				");
		sb.append("{					");
		sb.append(" 	result = x*y;	");
		sb.append("}else{				");
		sb.append("		result= x+y;	");
		sb.append("}					");
		
		Context cx = ContextFactory.getGlobal().enterContext();
		Scriptable scope = cx.initStandardObjects();
		// 直接写成scope.put("x", scope, "231");也行
		scope.put("x", scope, new Integer(20));
		scope.put("y", scope, new Integer(10));
		
		try {
			cx.evaluateString(scope, sb.toString(), null, 1,null);
			System.out.println(scope.get("result", scope));
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			Context.exit();
		}		
	}

}
