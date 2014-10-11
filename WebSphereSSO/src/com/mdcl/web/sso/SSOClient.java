package com.mdcl.web.sso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.Cookie;

public class SSOClient {

	public SSOClient() {
	}

	public static ResponseSet send(URL url, RequestSet set)
			throws SendRequestException {

		HttpURLConnection conn = null;
		OutputStream out = null;

		try {

			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");

			if(set.getCookie() != null) {
				Cookie[] cookies = set.getCookie();
				StringBuffer cbf = formatCookie(cookies);
				conn.setRequestProperty("Cookie", cbf.toString());
			}

			conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
			String xml = set.toXMLString();

			int requestLength = xml.getBytes("UTF-8").length;
			conn.setRequestProperty("Content-Length", Integer
					.toString(requestLength));
			out = conn.getOutputStream();
			out.write(xml.getBytes("UTF-8"));
			out.flush();

			BufferedReader in = new BufferedReader(new InputStreamReader(conn
					.getInputStream(), "UTF-8"));
			StringBuffer in_buf = new StringBuffer();
			char buf[] = new char[1024];
			int i;
			while ((i = in.read(buf, 0, buf.length)) != -1)
				in_buf.append(buf, 0, i);

			String in_string = in_buf.toString();
			ResponseSet resset = ResponseSet.parseXML(in_string);

			return resset;
		}catch (Exception e) {

			throw new SendRequestException(e.getMessage());
		}finally {
			if(out != null)
				try {
					out.close();
				}catch (IOException e) {
					throw new SendRequestException(e.getMessage());
				}
			if(conn != null)
				conn.disconnect();
		}
	}

	/**
	 * 格式化cookie，将多个cookie用;连接起来
	 * 
	 * @param cookies
	 *            Cookie[]
	 * @return StringBuffer
	 */
	private static StringBuffer formatCookie(Cookie[] cookies) {

		StringBuffer cbf = new StringBuffer();

		for (int i = cookies.length - 1; i >= 0; i--) {

			Cookie cookie = cookies[i];
			String name = cookie.getName();
			String value = cookie.getValue();

			cbf.append(name).append("=").append(value);

			if(i != 0) {
				cbf.append(";");
			}
		}

		return cbf;
	}

	private static final String sccsID = "$Id: SSOClient.java,v 1.15 2004/03/25 03:02:22 ganesh Exp $ $Date: 2004/03/25 03:02:22 $  MDCL, Inc.";
}
