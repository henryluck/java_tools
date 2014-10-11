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
 * ת���ļ��ı��룬��Ҫ��Ϊ�˽���ļ�������ʾ��������<br>
 * ���磺ԭ���༭��ʱ����big5��������GBK��������<br>
 * ����������big5�ı���ת����GBK����ʾ�Ͷ���<br>
 * 
 * <br>
 * �������class�ļ�����ʹ��<br>
 * Ĭ�ϱ���Ŀ¼����������ļ�ת��<br>
 * 
 * userage: java com.jlx.fileencode.FileCodeConVer BIG5 GBK d:\source
 * 
 * @author jianglx
 * 
 */
public class FileCodeConVer {

	/**
	 * ��Դ����
	 */
	private String fromEncode = "iso-8859-1";

	/**
	 * Ŀ�ı���
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
			// ����ת���ַ���
			fileCon.setFromEncode(args[0]);
			fileCon.setToEncode(args[1]);
		}

		if (args.length > 2) {
			// ���ù���Ŀ¼
			dir = args[2];
		}
		File curDir = new File(dir);
		fileCon.converDir(curDir);

	}

	/**
	 * ת��Ŀ¼�������е��ļ��������Ŀ¼�����������
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
	 * �ļ�����ת��
	 * 
	 * @param file
	 *            Ҫת�����ļ�
	 */
	public void converFile(File file) {

		InputStream in;
		InputStreamReader isr;
		OutputStream os;
		OutputStreamWriter osw;
		CharArrayWriter caw;
		try {
			// ������
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

			// д����
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
	 * �ļ�������
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
