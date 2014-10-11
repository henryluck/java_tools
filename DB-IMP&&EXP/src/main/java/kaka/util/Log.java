/**
 * 
 */
package kaka.util;

import java.util.Date;

/**
 * 日志工具，使用开关：java -DisDebug=true
 * 
 * @author jianglx
 * 
 */
public class Log {

	private static boolean isDebug = false;

	static {
		String isDebugStr = System.getProperty("isDebug");
		if (isDebugStr != null && isDebugStr.equals("true")) {
			isDebug = true;
			System.out.println("debug log is enable.");
		}
	}

	/**
	 * 是否需要打日志
	 * 
	 * @return true if need，false or not
	 */
	public static boolean isDebugEnable() {
		return isDebug;
	}

	/**
	 * 控制台写日志
	 * 
	 * @param log
	 *            log content
	 */
	public static void log(Object log) {
		StackTraceElement stack[] = (new Throwable()).getStackTrace();
		System.out.println(new StringBuffer((new Date()).toString())
				.append(" ").append(stack[1].getClassName()).append("	")
				.append(stack[1].getMethodName()).append("	").append(
						log.toString()).toString());
	}
}
