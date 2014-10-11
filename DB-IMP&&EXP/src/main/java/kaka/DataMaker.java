package kaka;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import kaka.db.ConnectionManger;
import kaka.db.Dao;
import kaka.file.ObjectReadWriter;
import kaka.property.TableList;

/**
 * 
 */

/**
 * @author jianglx
 * 
 */
public class DataMaker {
	/**
	 * 保存表的数据
	 */
	public void readTableData2Disk() {
		// 需要读取数据的table列表
		List tableList = TableList.getTableList();

		ObjectReadWriter rw = new ObjectReadWriter();
		Dao dao = new Dao();
		Connection conn = null;

		try {
			conn = ConnectionManger.getInstance().getConnection();
			for (int i = 0; i < tableList.size(); i++) {
				String tablename = (String) tableList.get(i);
				List data = dao.select(conn, tablename);
				rw.write(data, tablename);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 保存表的数据
	 */
	public void writeTableData2DB() {
		// 需要读取数据的table列表
		List tableList = TableList.getTableList();

		ObjectReadWriter rw = new ObjectReadWriter();
		Dao dao = new Dao();
		Connection conn = null;
		boolean isDelFirst = "true".equals(System.getProperty("isDel")) ? true
				: false;

		try {
			conn = ConnectionManger.getInstance().getConnection();
			for (int i = 0; i < tableList.size(); i++) {
				String tablename = (String) tableList.get(i);
				// 如果需要先清除数据
				if(isDelFirst){
					dao.delete(conn, tablename);
				}
				List obj = (List) rw.read(tablename);
				dao.insert(conn, tablename, obj);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
