package kaka.property;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import kaka.util.Log;

/**
 * @author jianglx
 * 
 */
public class TableList {
	private static List tables = new ArrayList();

	static {
		loadTables();
	}

	private static void loadTables() {

		InputStream is = null;

		try {
			is = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("TableList.properties");
			if (is == null) {
				is = TableList.class
						.getResourceAsStream("TableList.properties");
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}

		if (is != null) {

			BufferedReader bin = new BufferedReader(new InputStreamReader(is));
			String line = null;
			try {
				while ((line = bin.readLine()) != null) {
					if (!line.startsWith("#")) {
						tables.add(line);
					}
				}
				bin.close();
				is.close();
				if (Log.isDebugEnable()) {
					Log.log("Table list=" + tables);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static List getTableList() {
		return tables;
	}
}
