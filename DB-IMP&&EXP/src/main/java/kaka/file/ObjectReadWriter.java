/**
 * 
 */
package kaka.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import kaka.property.Info;
import kaka.util.Log;

/**
 * @author jianglx
 * 
 */
public class ObjectReadWriter {

	/**
	 * 写数据到磁盘
	 * 
	 * @param obj
	 *            the object save to db
	 * @param filename
	 *            table name
	 */
	public void write(Object obj, String filename) {
		try {
			// 检查目录不存在就创建
			File dir = new File(Info.getDirname());
			if (!dir.exists()) {
				dir.mkdirs();
			}
			// 写数据到磁盘，文件名 filename.dat
			File file = new File(Info.getDirname(), filename + ".dat");
			// 日志
			if (Log.isDebugEnable()) {
				Log.log("write table data,table name is " + filename);
			}
			// 写数据
			ObjectOutputStream writer = new ObjectOutputStream(
					new FileOutputStream(file));
			writer.writeObject(obj);
			writer.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 写数据到磁盘
	 * 
	 * @param filename
	 *            tablename
	 * @return db data
	 */
	public Object read(String filename) {
		try {
			File file = new File(Info.getDirname(), filename + ".dat");
			// 日志
			if (Log.isDebugEnable()) {
				Log.log("read table data,table name is " + filename);
			}
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(
					file));
			Object result = in.readObject();

			in.close();

			return result;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
