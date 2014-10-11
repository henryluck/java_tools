/**
 * 
 */
package kaka.property;

import java.io.InputStream;
import java.util.Properties;

import kaka.util.Log;

/**
 * @author jianglx
 * 
 */
public class Info {

	private static Properties properties = null;

	/**
	 * 连接字符串
	 */
	private static String url;
	/**
	 * 驱动
	 */
	private static String driver;
	/**
	 * 数据库用户名
	 */
	private static String user;
	/**
	 * 密码
	 */
	private static String pwd;

	/**
	 * 文件保存目录
	 */
	private static String dirname;

	public static String getDirname() {
		return dirname;
	}

	public static String getUrl() {
		return url;
	}

	public static String getDriver() {
		return driver;
	}

	public static String getUser() {
		return user;
	}

	public static String getPwd() {
		return pwd;
	}

	/**
	 * 初始化
	 */
	static {
		InputStream is = null;

		try {
			is = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("Info.properties");
			if (is == null) {
				is = Info.class.getResourceAsStream("Info.properties");
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}

		if (is != null) {
			try {
				properties = new Properties();
				properties.load(is);
				is.close();
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}

		if (properties != null) {
			url = (String) properties.get("url");
			driver = (String) properties.get("driver");
			user = (String) properties.get("user");
			pwd = (String) properties.get("pwd");
			dirname = (String) properties.get("dirname");
			if (dirname == null || "".equals(dirname)) {
				dirname = System.getProperty("dirname");
			}
			if (dirname == null || "".equals(dirname)) {
				dirname = System.getProperty("user.home")
						+ System.getProperty("file.separator") + "dbimpexp";
			}
		} else {
			// 输出日志
			if (Log.isDebugEnable()) {
				Log.log("File ConnectionInfo.properties not find!");
			}
		}
		// 输出日志
		if (Log.isDebugEnable()) {
			Log.log("url=" + getUrl());
			Log.log("driver=" + getDriver());
			Log.log("user=" + getUser());
			Log.log("pwd=" + getPwd());
			Log.log("dirname=" + getDirname());
		}
	}

	/**
	 * 获得配置文件项
	 * 
	 * @param key
	 * @return
	 */
	public static String getString(String key) {
		return (String) properties.get(key);
	}
}
