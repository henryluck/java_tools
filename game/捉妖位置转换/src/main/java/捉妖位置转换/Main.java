package 捉妖位置转换;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.IOException;

import com.alibaba.fastjson.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Main {
	public static void main(String[] args) throws IOException {
		String input = "39.084038,117.242575";
		String[] split = input.split(",");
		String requestBody = "src_type=gcj02&src_coordinate=" + split[1] + "," + split[0];
		MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded; charset=UTF-8");

		Request request = new Request.Builder().url("https://tool.lu/coordinate/ajax.html")
				.post(RequestBody.create(mediaType, requestBody)).build();
		OkHttpClient okHttpClient = new OkHttpClient();
		Response response = okHttpClient.newCall(request).execute();
		String result = response.body().string();
//		System.out.println(result);

		JSONObject jsonObject = JSONObject.parseObject(result);
		JSONObject wgs84 = jsonObject.getJSONObject("result").getJSONObject("wgs84");
//		System.out.println(wgs84);
		StringBuffer buffer = new StringBuffer();
		buffer.append("<wpt lat=\"");
		buffer.append(wgs84.get("lat"));
		buffer.append("\" lon=\"");
		buffer.append(wgs84.get("lng"));
		buffer.append("\">");
		String output = buffer.toString();

		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		// 封装文本内容
		Transferable trans = new StringSelection(output);
		// 把文本内容设置到系统剪贴板
		clipboard.setContents(trans, null);

		System.out.println(output);

	}
}
