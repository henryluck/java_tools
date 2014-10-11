/**
 * 
 */
package jlx.db.proxy;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;

/**
 * @author jianglx
 * 
 */
public class MySQLDriverProxy extends com.mysql.jdbc.NonRegisteringDriver {

	private static MySQLDriverProxy defaultDriver;

	// 注册驱动
	static {
		defaultDriver = null;
		try {
			if (defaultDriver == null) {
				defaultDriver = new MySQLDriverProxy();
				DriverManager.registerDriver(defaultDriver);
			}
		} catch (RuntimeException runtimeexception) {
		} catch (SQLException sqlexception) {
		}
	}

	public MySQLDriverProxy() throws SQLException {
		super();
		// 把Mysql默认的驱动反注册
		Enumeration ds = DriverManager.getDrivers();
		while (ds.hasMoreElements()) {
			try {
				Driver d = (Driver) ds.nextElement();
				if (d.getClass().toString().endsWith("mysql.jdbc.Driver")) {
					DriverManager.deregisterDriver(d);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see oracle.jdbc.driver.OracleDriver#connect(java.lang.String,
	 * java.util.Properties)
	 */
	public Connection connect(String arg0, Properties arg1) throws SQLException {
		return new ConnetionProxy(super.connect(arg0, arg1));
	}

}
