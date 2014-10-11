/**
 * 
 */
package jlx.db.proxy;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jianglx
 * 
 */
public class Logger {
	public static DateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
	public static void log(String log) {
		System.out.println(getPrefix() + "[DBSQLLog Debug] " + log);
	}

	public static void warn(String log) {
		System.err.println(getPrefix() + "[DBSQLLog Warn] " + log);
	}
	
	private static String getPrefix(){
		return "[" + formatter.format(new Date()) + "] [" + Thread.currentThread().getName() + "] ";
	}
}
