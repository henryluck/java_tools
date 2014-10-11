/**
 * 
 */
package kaka.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import kaka.property.Info;
import kaka.util.Log;

/**
 * @author jianglx
 * 
 */
public class ConnectionManger {
	private static ConnectionManger instance = new ConnectionManger();

	private ConnectionManger() {
	}

	public static ConnectionManger getInstance() {
		return instance;
	}

	/**
	 * 获取连接，信息来自配置文件
	 * 
	 * @return Connection
	 */
	public Connection getConnection() {
		String jdbcDriverClass = Info.getDriver();
		String jdbcConUrl = Info.getUrl();
		String user = Info.getUser();
		String password = Info.getPwd();
		Connection conn = null;
		
		// 日志
		if (Log.isDebugEnable()) {
			Log.log("jdbcDriverClass:" + jdbcDriverClass);
			Log.log("jdbcConUrl:" + jdbcConUrl);
			Log.log("user:" + user);
			Log.log("password:" + password);
			Log.log("conn:" + conn);
		}
		
		try {
			Class.forName(jdbcDriverClass).newInstance();
			conn = DriverManager.getConnection(jdbcConUrl, user, password);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}
}
