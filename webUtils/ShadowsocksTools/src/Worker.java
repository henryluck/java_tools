import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import bean.Config;
import bean.GUIConfig;

import com.google.gson.Gson;

public class Worker {
	// 保存一个密码，判断是否发生变化
	private String apwdCache;

	// 工作类
	public void doWork() throws Exception {
		StringBuffer stringBuffer = null;
		// 读页面内容
		try {
			URL url = new URL("http://www.ishadowsocks.com/");
			URLConnection urlConnection = url.openConnection();
			urlConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			urlConnection.setDoOutput(true);

			InputStream openStream = urlConnection.getInputStream();// url.openStream();
			stringBuffer = copy2String(openStream);
//			System.out.println(stringBuffer.toString());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		Config AConfig = getConfigFromWeb(stringBuffer, "A服务器地址:");
		Config BConfig = getConfigFromWeb(stringBuffer, "B服务器地址:");
		Config CConfig = getConfigFromWeb(stringBuffer, "C服务器地址:");
		if (AConfig.getPassword().equals(this.apwdCache)) {
			return; // 如果密码没有变，就返回
		}
		this.apwdCache = AConfig.getPassword();
		System.out.println("密码变了更新...");

		// 替换配置文件
		String dir = new File(Shadowsocks.class.getClass().getResource("/").getPath()).getAbsolutePath();
		System.out.println("dir="+dir);
		String configFilePah = dir + "/gui-config.json";
		
		GUIConfig guiConfig = new GUIConfig();
		guiConfig.addConfig(AConfig);
		guiConfig.addConfig(BConfig);
		guiConfig.addConfig(CConfig);
		
		Gson gson = new Gson();
		writeConfigFile(gson.toJson(guiConfig), configFilePah);
		// 重启
		Process exec = Runtime.getRuntime().exec("taskkill /F /IM Shadowsocks.exe");
		exec.waitFor();
		Runtime.getRuntime().exec(dir + "/Shadowsocks.exe");
	}

	// 传入服务器地址的字符串
	private Config getConfigFromWeb(StringBuffer buffer, String string) {
		Config config = new Config();
		int startIndex = buffer.indexOf(string);
		config.setServer(parseStr(buffer, string, "<"));
		config.setMethod(parseStr(buffer, "加密方式:", "<", startIndex));
		config.setPassword(parseStr(buffer, "密码:", "<", startIndex));
		config.setServer_port(Integer.parseInt(parseStr(buffer, "端口:", "<", startIndex)));
		config.setRemarks(parseStr(buffer, string, "<"));
		return config;
	}

	public static void main(String[] args) {
		StringBuffer a = new StringBuffer("1234567890");
		System.out.println(new Worker().parseStr(a, "12", "90"));
	}

	// 得到指定开始字符和结束字符直接的内容
	private String parseStr(StringBuffer content, String startStr, String endStr, int fromIndex) {
		int startStrLen = startStr.length();
		int startIndex = content.indexOf(startStr, fromIndex) + startStrLen;
		int endIndex = content.indexOf(endStr, startIndex);
		return content.substring(startIndex, endIndex);
	}

	private String parseStr(StringBuffer content, String startStr, String endStr) {
		return parseStr(content, startStr, endStr, 0);
	}

	private void writeConfigFile(String configFileBuf, String configFilePah) throws IOException {
		FileOutputStream out = new FileOutputStream(configFilePah);
		out.write(configFileBuf.toString().getBytes());
		out.flush();
		out.close();
	}

	private StringBuffer copy2String(InputStream in) throws Exception {
		StringBuffer stringBuffer = new StringBuffer();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
		char[] charBuffer = new char[10240];
		int length;
		while ((length = reader.read(charBuffer)) != -1) {
			stringBuffer.append(charBuffer, 0, length);
		}
		in.close();
		return stringBuffer;
	}
}