/**
 * 
 */
package com.jlx.fileencode;

import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * 转换文件的编码，主要是为了解决文件中文显示乱码问题<br>
 * 例如：原来编辑的时候是big5的现在用GBK打开是乱码<br>
 * 用这个程序把big5的编码转换成GBK的显示就对了<br>
 * 
 * <br>
 * 拷贝这个class文件即可使用<br>
 * 默认遍历目录里面的所有文件转换<br>
 * 
 * userage: java com.jlx.fileencode.FileCodeConVer BIG5 GBK d:\source
 * 
 * @author jianglx
 * 
 */
public class FileCodeConVer {

	/**
	 * 来源编码
	 */
	private String fromEncode = "iso-8859-1";

	/**
	 * 目的编码
	 */
	private String toEncode = "iso-8859-1";

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println(System.getProperty("user.dir"));
		String dir = System.getProperty("user.dir");

		FileCodeConVer fileCon = new FileCodeConVer();
		if (args != null && args.length > 1) {
			// 设置转换字符集
			fileCon.setFromEncode(args[0]);
			fileCon.setToEncode(args[1]);
		}

		if (args.length > 2) {
			// 设置工作目录
			dir = args[2];
		}
		File curDir = new File(dir);
		fileCon.converDir(curDir);

	}

	/**
	 * 转换目录里面所有的文件，如果是目录就往里面遍历
	 * 
	 * @param dir
	 */
	public void converDir(File dir) {
		File[] files = dir.listFiles(new FileFilter());
		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile()) {
				converFile(files[i]);
			} else if (files[i].isDirectory()) {
				converDir(files[i]);
			}
		}
	}

	/**
	 * 文件编码转换
	 * 
	 * @param file
	 *            要转换的文件
	 */
	public void converFile(File file) {

		InputStream in;
		InputStreamReader isr;
		OutputStream os;
		OutputStreamWriter osw;
		CharArrayWriter caw;
		try {
			// 读内容
			in = new FileInputStream(file);
			isr = new InputStreamReader(in, fromEncode);
			caw = new CharArrayWriter();

			char[] cbuf = new char[1024];
			int len;
			while ((len = isr.read(cbuf)) != -1) {
				caw.write(cbuf, 0, len);
			}

			isr.close();
			in.close();

			// 写内容
			os = new FileOutputStream(file);
			osw = new OutputStreamWriter(os, toEncode);

			osw.write(caw.toCharArray());

			caw.flush();
			caw.close();
			osw.flush();
			osw.close();
			os.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 文件过滤器
	 * 
	 * @author jianglx
	 * 
	 */
	public class FileFilter implements FilenameFilter {

		public boolean accept(File dir, String name) {
			File file = new File(dir, name);
			if (file.isDirectory()) {
				return true;
			}
			if (name.toLowerCase().endsWith(".c")
					|| name.toLowerCase().endsWith(".h")
					|| name.toLowerCase().endsWith(".rc")
					|| name.toLowerCase().endsWith(".cpp")) {
				return true;
			}
			return false;
		}

	}

	/**
	 * @return the fromEncode
	 */
	public String getFromEncode() {
		return fromEncode;
	}

	/**
	 * @param fromEncode
	 *            the fromEncode to set
	 */
	public void setFromEncode(String fromEncode) {
		this.fromEncode = fromEncode;
	}

	/**
	 * @return the toEncode
	 */
	public String getToEncode() {
		return toEncode;
	}

	/**
	 * @param toEncode
	 *            the toEncode to set
	 */
	public void setToEncode(String toEncode) {
		this.toEncode = toEncode;
	}
}
