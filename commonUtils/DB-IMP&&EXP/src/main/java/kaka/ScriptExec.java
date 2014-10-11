/**
 * 
 */
package kaka;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kaka.db.ConnectionManger;
import kaka.db.Dao;
import kaka.file.ScriptReader;
import kaka.property.Info;
import kaka.util.Log;

/**
 * @author jianglx
 * 
 */
public class ScriptExec {

	public void exec() {
		String url = Info.getString("sriptfile");
		if (Log.isDebugEnable()) {
			Log.log("ScriptExec exec url= " + url);
		}
		// sql脚本数组
		String[] scripts = new ScriptReader(url).pareseScript();
		Dao dao = new Dao();
		Connection conn = null;

		try {
			conn = ConnectionManger.getInstance().getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 成功的list
		List list = new ArrayList();
		// 遍历执行
		for (int i = 0; i < scripts.length; i++) {
			try {

				dao.execSQL(conn, scripts[i]);

			} catch (Exception e) {
				if (Log.isDebugEnable()) {
					Log.log("ScriptExec exec error sql= " + scripts[i]);
				}
				e.printStackTrace();
				// 继续
				continue;
			}
			list.add(scripts[i]);
		}

		try {
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ScriptExec().exec();

	}

}
