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
public class OracleDriverProxy extends oracle.jdbc.driver.OracleDriver {

	private static OracleDriverProxy defaultDriver;
	
	// 注册驱动
	static {
		defaultDriver = null;
		try {
			if (defaultDriver == null) {
				defaultDriver = new OracleDriverProxy();
				DriverManager.registerDriver(defaultDriver);
			}
		} catch (RuntimeException runtimeexception) {
		} catch (SQLException sqlexception) {
		}
	}
	public OracleDriverProxy() {
		super();
		// 把其他的驱动都反注册
		Enumeration ds = DriverManager.getDrivers();
		while(ds.hasMoreElements()){
			try {
				Driver d = (Driver)ds.nextElement();
				if(d.getClass().toString().endsWith("OracleDriver")){
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see oracle.jdbc.driver.OracleDriver#defaultConnection()
	 */
	public Connection defaultConnection() throws SQLException {
		return new ConnetionProxy(super.defaultConnection());
	}

}
