package kaka.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import kaka.util.Log;

/**
 * 
 */

/**
 * @author jianglx
 * 
 */
public class Dao {

	/**
	 * 查询所有的记录
	 * 
	 * @param conn
	 *            db connection
	 * @param tablename
	 *            db table name
	 * @return data list
	 */
	public List select(Connection conn, String tablename) {
		// sql
		String sql = new StringBuffer("select * from ").append(tablename)
				.toString();
		// 日志
		if (Log.isDebugEnable()) {
			Log.log("sql: " + sql);
		}
		// 
		PreparedStatement pst = null;
		ResultSet rst = null;
		ResultSetMetaData rsmd = null;
		List list = new ArrayList();
		try {
			// 查询表的所有信息
			pst = conn.prepareStatement(sql);
			rst = pst.executeQuery();
			rsmd = rst.getMetaData();
			while (rst.next()) {
				Map colMap = new HashMap();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					colMap.put(rsmd.getColumnName(i), rst.getObject(rsmd
							.getColumnName(i)));
				}
				list.add(colMap);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rst != null) {
					rst.close();
					rst = null;
				}
				if (pst != null) {
					pst.close();
					pst = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return list;

	}

	/**
	 * 查询所有的记录
	 * 
	 * @param conn
	 *            db connection
	 * @param tablename
	 *            db table name
	 * 
	 */
	public void insert(Connection conn, String tablename, List list) {
		// sql
		StringBuffer sql = new StringBuffer("insert into ").append(tablename)
				.append("(");
		// db
		PreparedStatement pst = null;

		// 存放列名称的list
		ArrayList colName = new ArrayList();

		// 把sql拼出来，并且把列名称保存到list里面
		if (list != null && list.size() > 0) {
			Map map = (Map) list.get(0);
			for (Iterator iterator = map.keySet().iterator(); iterator
					.hasNext();) {
				Object object = (Object) iterator.next();
				colName.add(object);
				sql.append(object.toString()).append(",");
			}

		}
		makeSql(sql, colName);
		// 日志
		if (Log.isDebugEnable()) {
			Log.log("sql: " + sql);
		}
		// 开始数据库操作

		try {
			// Disable auto-commit
			conn.setAutoCommit(false);
			pst = conn.prepareStatement(sql.toString());
			// insert data
			for (int i = 0; i < list.size(); i++) {
				Map map = (Map) list.get(i);
				for (int j = 0; j < colName.size(); j++) {
					pst.setObject(j + 1, map.get(colName.get(j)));
				}
				pst.addBatch();
			}

			// Execute the batch
			int[] updateCounts = pst.executeBatch();
			// 日志
			if (Log.isDebugEnable()) {
				Log.log("update counts: " + updateCounts.length);
			}
			// Since there were no errors, commit
			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				if (pst != null) {
					pst.close();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}
	}

	/**
	 * 清除表数据
	 * 
	 * @param conn
	 *            db connection
	 * @param tablename
	 *            db table name
	 * 
	 */
	public int delete(Connection conn, String tablename) {
		String sql = "delete from " + tablename;
		Statement pst = null;
		int count = 0;
		// 日志
		if (Log.isDebugEnable()) {
			Log.log("delete sql: " + sql);
		}
		try {
			pst = conn.createStatement();
			count = pst.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null) {
					pst.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (Log.isDebugEnable()) {
			Log.log("delete counts: " + count);
		}

		return count;
	}

	/**
	 * 执行一个sql
	 * @param conn
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public boolean execSQL(Connection conn, String sql) throws SQLException {
		// 日志
		if (Log.isDebugEnable()) {
			Log.log("execSQL sql: " + sql);
		}
		// 
		Statement pst = null;
		boolean result = false;
		try {
			// 查询表的所有信息
			pst = conn.createStatement();
			result = pst.execute(sql);

		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (pst != null) {
					pst.close();
					pst = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return result;
	}

	/**
	 * 拼装sql
	 * 
	 * @param str
	 *            sql
	 * @param colName
	 *            所有的列
	 * @return
	 */
	private void makeSql(StringBuffer sql, List colName) {
		// 把sql拼完整
		sql.deleteCharAt(sql.length() - 1);
		// 加问号
		sql.append(")").append(" values(");
		for (int i = 0; i < colName.size(); i++) {
			sql.append("?,");
		}
		sql.deleteCharAt(sql.length() - 1);
		sql.append(")");
	}
}
