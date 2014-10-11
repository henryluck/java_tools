/**
 * 
 */
package kaka.file;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * 解析sql脚本文件里面的脚本语句，脚本文件不能带注释，没个语句都是用分号;分隔 输入脚本文件位置
 * 
 * @author jianglx
 * 
 */
public class ScriptReader {

	private String fileURL;

	public ScriptReader(String url) {
		fileURL = url;
	}

	public String[] pareseScript() {
		if (fileURL == null) {
			return null;
		}
		StringBuffer script = null;
		try {
			FileInputStream in = new FileInputStream(fileURL);
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(in));

			script = new StringBuffer();
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.startsWith("--") || line.startsWith("/*")
						|| line.endsWith("*/")) {
					//忽略注释
					continue;
				}
				script.append(line);
			}
			in.close();
			in = null;
			reader.close();
			reader = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return script == null ? null : script.toString().split(";");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] str = new ScriptReader(
				"E:/ccview/jianglx_MochaBPM7.0_int/MochaBPM6.2.1_Dev/MochaBPM6.2.1/database/oracle/BPM_7.0/BPM_7_0_INIT_DATA_UUID_NOCOMMENT.sql")
				.pareseScript();
		
//		for (int i = 0; i < str.length; i++) {
//			System.out.println(str[i]);
//		}
		
		System.out.println(str.length);

	}

}
