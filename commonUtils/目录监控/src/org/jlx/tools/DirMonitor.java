/**
 * 
 */
package org.jlx.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.util.List;

/**
 * 文件目录内容监控
 * 
 * @author jianglx
 * 
 */
public class DirMonitor {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		long start = System.currentTimeMillis();

		String url = "\\\\192.168.17.226\\文件中转\\";
		if(System.getProperty("MonitorDir") != null){
			url = System.getProperty("MonitorDir");
		}
		
		File file = new File(url);

		System.out.println("开始了...");
		List<URI> list = new Scanner().listFile(file);
		System.out.println(list.size());
		System.out.println("时间：" + (System.currentTimeMillis() - start));
		putFile(list);
		System.out.println("总时间：" + (System.currentTimeMillis() - start));
		System.out.println("搜索结果,请查看D:\\out.txt，\n按任意键结束...");
		
		InputStream in = System.in;
		while(in.read() != -1){
			break;
		}

	}

	/**
	 * @param list
	 */
	public static void putFile(List<URI> list) {
		try {
			FileOutputStream out = new FileOutputStream(new File("D:\\out.txt"));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
					out, "GBK"));

			for (int i = 0; i < list.size(); i++) {
				URI uri = list.get(i);
				writer.write(uri.toString());
				writer.newLine();
			}

			writer.close();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
