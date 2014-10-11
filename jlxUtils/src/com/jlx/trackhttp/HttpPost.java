/**
 * 
 */
package com.jlx.trackhttp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.codec.net.URLCodec;

/**
 * 默认post提交工具类
 * org.apache.commons.codec.net.URLCodec
 * @author jianglx
 * 
 */
public class HttpPost {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * 使用了commons的codec工具包，模拟post提交
	 */
	public static void send() {
		try {
			StringBuffer sendStr = new StringBuffer();
			URLCodec codec = new URLCodec();

			sendStr.append("a=").append(codec.encode("哈哈11", "UTF-8")).append(
					"&b=").append(codec.encode("拉拉11", "UTF-8"));

			URL url = new URL("http://127.0.0.1:8080/test/Echo");
			URLConnection urlCon = url.openConnection();

			urlCon.setDoInput(true);
			urlCon.setDoOutput(true);
			urlCon.setUseCaches(false);
			urlCon.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");

			OutputStream out = urlCon.getOutputStream();
			out.write(sendStr.toString().getBytes("US-ASCII"));

			out.flush();
			out.close();

			InputStream in = urlCon.getInputStream();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
