/*
 * baidu mp3下载的页面包括4个url
 *
 * url1: 例如新歌100首页面url地址
 * url2: 一首确定的mp3，第一页下载链接列表页面的url地址
 * url3: 一个mp3下载链接页面的url地址
 * url4: 一个mp3时间的url地址
 *
 * 程序是从url1一直解析进入获得正确的url4，进行下载的。
 */
package downloadmp3;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author oneleaf
 */
public class DownTask {

	private String savePath; // 保存路径
	/**
	 * 下载mp3任务列表，每个任务是一个TaskInfo 只有一个任务列表
	 */
	private List<TaskInfo> taskList;
	private List<Mp3Info> downloadList;

	public List<Mp3Info> getDownloadList() {
		return downloadList;
	}

	private DownTask() {
		savePath = System.getProperty("user.home");
		if (savePath != null) {
			if (savePath.charAt(savePath.length() - 1) == File.separatorChar) {
				savePath = savePath + "music";
			} else {
				savePath = savePath + File.separatorChar + "music";
			}
		}
		taskList = new ArrayList<TaskInfo>();
		downloadList = new ArrayList<Mp3Info>();
		// ----------add by kaka start---------------------
		// 增加配置,使用代理服务器
		ResourceBundle rbInfo = ResourceBundle.getBundle("config");
		if (rbInfo != null && "true".equals(rbInfo.getString("usePorxy"))) {
			System.out.println("使用代理配置：\nip：" + rbInfo.getString("proxyIp")
					+ "\n端口：" + rbInfo.getString("proxyPort"));
			System.getProperties().put("proxySet", "true");
			System.getProperties()
					.put("proxyHost", rbInfo.getString("proxyIp"));
			System.getProperties().put("proxyPort",
					rbInfo.getString("proxyPort"));
		}
		// ----------add by kaka end---------------------
	}

	private static class DownTaskHolder {

		static DownTask instance = new DownTask();
	}

	/**
	 * 只有一个实例
	 */
	public static DownTask getInstance() {
		return DownTaskHolder.instance;
	}

	private ExecutorService executorService;// 线程池

	public ExecutorService getExecutorService() {
		return executorService;
	}

	private int POOL_SIZE = 20;// 线程池大小

	public int getPOOL_SIZE() {
		return POOL_SIZE;
	}

	public void setPOOL_SIZE(int POOL_SIZE) {
		this.POOL_SIZE = POOL_SIZE;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public String UrlEncode(String surl) throws UnsupportedEncodingException {
		surl = URLDecoder.decode(surl, "GBK");
		StringBuffer bu = new StringBuffer();
		int no1 = surl.indexOf("?");
		String address = surl;
		if (no1 > 0) {
			address = surl.substring(0, no1);
		}
		bu.append(address.substring(0, 10));
		String[] b = address.substring(10).split("/");
		bu.append(b[0]);
		for (int i = 1; i < b.length; i++) {
			bu.append("/");
			bu.append(URLEncoder.encode(b[i], "GBK"));
		}
		if (address.substring(10).endsWith("/")) {
			bu.append("/");
		}
		if (no1 > 1) {
			bu.append("?");
			String[] q = surl.substring(no1 + 1).split("&");
			for (int i = 0; i < q.length; i++) {
				if (i != 0) {
					bu.append("&");
				}
				int no = q[i].indexOf("=");
				if (no != -1) {
					String name = q[i].substring(0, no);
					String value = q[i].substring(no + 1);
					bu.append(URLEncoder.encode(name, "GBK"));
					bu.append("=");
					bu.append(URLEncoder.encode(value, "GBK"));
				} else {
					bu.append(URLEncoder.encode(q[i], "GBK"));
				}
			}
		}
		return bu.toString();
	}

	/**
	 * 根据url得到html代码字符串
	 * 
	 * @param String
	 *            url
	 * @return html代码
	 */
	private synchronized String getHtml(String address)
			throws MalformedURLException, IOException {
		URL url = new URL(UrlEncode(address));
		URLConnection conn = url.openConnection();
		BufferedInputStream in = new BufferedInputStream(conn.getInputStream());
		try {
			String inputLine;
			StringBuffer html = new StringBuffer();
			byte[] buf = new byte[1024];
			int bytesRead = 0;
			while (bytesRead >= 0) {
				inputLine = new String(buf, 0, bytesRead, "ISO-8859-1");
				html.append(inputLine);
				bytesRead = in.read(buf);
			}
			return new String(html.toString().getBytes("ISO-8859-1"), "GBK");
		} finally {
			in.close();
		}
	}

	/**
	 * 实现mv命令
	 */
	private boolean mv(File oldfile, File newfile) {
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		try {
			in = new BufferedInputStream(new FileInputStream(oldfile));
			out = new BufferedOutputStream(new FileOutputStream(newfile));
			byte[] buf = new byte[4096];
			int bytesRead = 0;
			while (bytesRead >= 0) {
				out.write(buf, 0, bytesRead);
				bytesRead = in.read(buf);
			}
			if (!oldfile.delete()) {
				oldfile.deleteOnExit();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (Exception ex) {
			}
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception ex) {
			}
		}
		return true;
	}

	/**
	 * 根据mp3info实体下载一首mp3
	 * 
	 * 下载速度太慢，或者文件太小返回false
	 * 
	 * @param Mp3Info
	 *            mp3Info.currDownUrl 为这首mp3实际的下载地址
	 * @return boolean 下载成功返回true，否则false
	 */
	private boolean downloadFile(Mp3Info mp3Info) {
		downloadList.add(mp3Info);
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		File file = mp3Info.saveFile;
		File tempfile = null;
		try {
			tempfile = File.createTempFile("mp3", ".tmp");
			out = new BufferedOutputStream(new FileOutputStream(tempfile));
			URL url = new URL(UrlEncode(mp3Info.currDownUrl));
			URLConnection conn = url.openConnection();
			conn.addRequestProperty("Accept-Language", "zh-cn");
			conn.setReadTimeout(60000);
			mp3Info.length = conn.getContentLength();
			mp3Info.pos = 0;
			in = new BufferedInputStream(conn.getInputStream());
			byte[] buf = new byte[4096];
			int bytesRead = 0;
			Date sd = new Date();
			int count = 0;
			while (bytesRead >= 0) {
				out.write(buf, 0, bytesRead);
				mp3Info.pos = mp3Info.pos + bytesRead;
				bytesRead = in.read(buf);
				count = count + bytesRead;
				if (new Date().getTime() - sd.getTime() > 60000) {
					// 如果下载速度小于1K，放弃
					if (count < 60000) {
						return false;
					}
				}
			}
			out.flush();
		} catch (Exception ex) {
			if (tempfile != null) {
				tempfile.delete();
			}
			System.out.println(mp3Info.currDownUrl + " " + ex.getMessage());
			return false;
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (Exception ex) {
			}
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception ex) {
			}
			downloadList.remove(mp3Info);
		}
		if (tempfile.length() > 1024 * 1024) {
			return mv(tempfile, file);
		} else {
			if (tempfile != null) {
				tempfile.delete();
			}
			return false;
		}
	}

	/**
	 * 增加一个mp3下载任务到任务列表
	 * 
	 * 按照加入任务列表的顺序给每个TaskInfo.no赋值，从1开始
	 * 
	 * @param TaskInfo
	 *            task 需要添加的任务
	 */
	void addTask(TaskInfo task) {
		String url = task.url;
		if (!url.startsWith("http://mp3.baidu.com/m?tn=baidump3&ct=")
				&& !url
						.startsWith("http://mp3.baidu.com/m?f=ms&tn=baidump3&ct=")) {
			return;
		}
		for (TaskInfo t : taskList) {
			if (t.url.equals(url)) {
				return;
			}
		}
		task.no = taskList.size() + 1;
		taskList.add(task);
	}

	/**
	 * 按照正则表达式re匹配html代码找到符合的mp3下载任务，添加到任务列表中
	 * 
	 * @param String
	 *            html 多首mp3的页面代码
	 * @param String
	 *            re 匹配正则表达式
	 */
	void matchTask(String html, String re) {
		Pattern p = Pattern.compile(re, Pattern.DOTALL);
		Matcher m = p.matcher(html);
		while (m.find()) {
			TaskInfo task = new TaskInfo();
			task.isEnabled = true;
			String srcstr = m.group();

			Pattern purl = Pattern.compile("href=\"(.*?tn=baidump3.*?)\"");
			Matcher murl = purl.matcher(srcstr);
			String url = "";
			if (murl.find()) {
				url = murl.group(1);
			}
			url = url.replace("lm=-1", "lm=0"); // 仅仅下载mp3
			task.url = url;

			Pattern pname = Pattern.compile(
					"<[aA].*?tn=baidump3.*?>(.*?)</[aA]>", Pattern.DOTALL);
			Matcher mname = pname.matcher(srcstr);
			String title;
			if (mname.find()) {
				title = mname.group(1).replaceAll("<.*?>", "").replaceAll("\n",
						" ").replaceAll("\\.\\.\\.", " ").replaceAll(" +", " ")
						.trim();
			} else {
				continue;
			}

			String name = "";
			while (mname.find()) {
				String str = mname.group(1);
				str = str.replaceAll("<.*?>", "");
				name = name.trim() + " " + str.trim();
			}
			name = name.trim();

			if ("".equals(name)) {
				if (title.split(" ").length == 2) {
					task.title = title.substring(0, title.indexOf(" "));
					task.artist = title.substring(title.indexOf(" ") + 1);
				} else {
					task.title = title;
				}
			} else {
				task.title = title;
				task.artist = name;
			}
			disableExistedTask(task);

			addTask(task);
		}
	}

	/**
	 * 遍历savePath中已有的mp3，已经下载过的mp3就将下载task失效
	 * 
	 * @param TaskInfo
	 *            task 进行判断的下载任务task
	 * @author aaron.wu
	 */
	void disableExistedTask(TaskInfo task) {
		File dir = new File(savePath);
		if (dir.isDirectory()) {
			String[] files = dir.list();
			String name = task.artist + "-" + task.title + ".mp3";
			for (String file : files) {
				if (file.equalsIgnoreCase(name)) {
					task.isEnabled = false;
					return;
				}
			}
		}
	}

	/**
	 * 通过mp3名字获得一个下载任务taskInfo
	 * 
	 * @param String
	 *            name 名字规则： "artist-title"
	 * @author aaron.wu
	 */
	public TaskInfo getTaskByName(String name) {
		String artist = name.substring(0, name.indexOf("-")).trim();
		String title = name.substring(name.lastIndexOf("-") + 1).trim();
		TaskInfo task = new TaskInfo();
		task.isEnabled = true;
		task.title = title;
		task.artist = artist;
		try {
			task.url = "http://mp3.baidu.com/m?f=ms&tn=baidump3&ct=134217728&lf=&rn=&word="
					+ URLEncoder.encode(title, "gb2312")
					+ "+"
					+ URLEncoder.encode(artist, "gb2312") + "&lm=0";
		} catch (Exception e) {
		}
		return task;
	}

	/**
	 * 获得这个实例的mp3下载列表，一般是通过下面的方法得到的
	 */
	public List<TaskInfo> getTaskList() {
		return taskList;
	}

	/**
	 * 通过url的不同类型调用不同的函数来获得下载任务列表taskList
	 * 
	 * 1. 通过url地址 2. 通过本地文件，文件是mp3文件名列表 3. 通过mp3文件名: artist-title 4. 通过歌手名:
	 * artist
	 * 
	 * mp3文件名格式："artist-title"
	 * 
	 * @author aaron.wu
	 */
	public void getTaskList(String url) throws MalformedURLException,
			IOException {
		if (url.toLowerCase().contains("baidu.com")) {
			getTaskListByUrl(url);
		} else if (url.startsWith("/")) {
			getTaskListByFile(url);
		} else if (url.contains("-")) {
			taskList.clear();
			TaskInfo task = getTaskByName(url);
			disableExistedTask(task);
			addTask(task);
		} else {
			getTaskListByArtist(url);
		}
	}

	/**
	 * 通过歌手名获得下载任务列表
	 */
	public void getTaskListByArtist(String name) throws MalformedURLException,
			IOException {
		String url = "http://mp3.baidu.com/singerlist/"
				+ URLEncoder.encode(name, "gb2312") + ".html";
		getTaskListByUrl(url);
		for (TaskInfo task : getTaskList()) {
			task.artist = name;
			disableExistedTask(task);
		}
	}

	/**
	 * 通过本地mp3文件名列表文件来获得下载任务列表
	 * 
	 * @author aaron.wu
	 */
	public void getTaskListByFile(String file) throws IOException {
		taskList.clear();
		String name;
		String record = null;
		BufferedReader br = new BufferedReader(new FileReader(file));
		while ((name = br.readLine()) != null) {
			TaskInfo task = getTaskByName(name);
			disableExistedTask(task);
			addTask(task);
		}
		br.close();
	}

	/**
	 * 通过url解析获得下载任务列表
	 * 
	 * 下载任务列表用于在GUI页面上显示 每个下载任务是一首mp3，一首mp3可以包含多个下载链接
	 * 
	 * @param String
	 *            souceUrl 为url1: 例如新歌100首页面url地址
	 */
	public void getTaskListByUrl(String soureUrl) throws MalformedURLException,
			IOException {
		String html = getHtml(soureUrl);
		taskList.clear();

		String url = soureUrl.toLowerCase();
		if (url.startsWith("http://list.mp3.baidu.com/")) {
			matchTask(html,
					"<div class=\"DivSong(L*?)\">.*?tn=baidump3.*?</div>");
			// 如果什么都没有找到，尝试使用其它的匹配方式
			if (taskList.size() == 0) {
				matchTask(html,
						"<td width=\"3%\".*?</td>.*?<td width=\"17%\".*?</td>");
			}

			if (taskList.size() == 0) {
				matchTask(html, "<td><a.*?</a>.*?(<a.*?)</td>");
			}
			//
			if (taskList.size() == 0) {
				matchTask(html, "<th>.*?</th>.*?<td>.*?tn=baidump3.*?</td>");
			}

			if (taskList.size() == 0) {
				matchTask(html, "<tr>.*?<TD>.*?tn=baidump3.*?</TD>.*?</tr>");
			}

			if (taskList.size() == 0) {
				matchTask(html,
						"<td.*?<div>.*?\\.</div>.*?</td>.*?<td.*?tn=baidump3.*?</td>");
			}
		} else if (url.startsWith("http://zhangmen.baidu.com/")) {
			matchTask(html, "<td class=\"TdSn\">.*?tn=baidump3.*?</td>");
			matchTask(html, "<nobr>.*?tn=baidump3.*?</nobr>");
			matchTask(html, "<div class=\"TxExRecDt\">.*?tn=baidump3.*?</div>");
			// 如果什么都没有找到，尝试使用其它的匹配方式
			if (taskList.size() == 0) {
				matchTask(html,
						"<(td|div).*?tn=baidump3.*?tn=baidump3.*?</(td|div)>");
			}
		} else if (url.startsWith("http://mp3.baidu.com/singerlist/")) {
			matchTask(html, "<td class=ct2>.*?tn=baidump3.*?</td>");
		}

		// 如果什么都没有找到，尝试使用其它的匹配方式
		if (taskList.size() == 0) {
			matchTask(html, "<[aA].*?tn=baidump3.*?</[aA]>");
		}
	}

	Pattern pgeturl = Pattern.compile("href=\"(.*?)\".*?>(.*?)</[aA]>");
	Pattern ptd = Pattern.compile("<td.*?>(.*?)</td>", Pattern.DOTALL);

	/**
	 * 从html代码中得到Mp3Info信息实体
	 * 
	 * @param String
	 *            html
	 * @param String
	 *            defaultArtist
	 * @return Mp3Info实体
	 */
	Mp3Info splitUrl(String html, String defaultArtist) {

		Matcher m = ptd.matcher(html);
		// 序号
		if (!m.find()) {
			System.out.println("不是合法的链接地址，请检查：\n" + html);
			return null;
		}
		// 地址
		if (!m.find()) {
			System.out.println("不是合法的链接地址，请检查：\n" + html);
			return null;
		}
		Matcher murl = pgeturl.matcher(m.group());
		if (!murl.find()) {
			System.out.println("不是合法的链接地址，请检查：\n" + html);
			return null;
		}
		Mp3Info mp3Info = new Mp3Info();
		mp3Info.downUrl = murl.group(1);
		mp3Info.title = murl.group(2).replaceAll("<.*?>", "").replaceAll(
				"&amp;", "&").trim();
		if (mp3Info.title.indexOf(" ") > 0) {
			mp3Info.title = mp3Info.title.substring(0, mp3Info.title
					.indexOf(" "));
		}

		// 歌唱者
		if (!m.find()) {
			System.out.println("不是合法的链接地址，请检查：\n" + html);
			return null;
		}
		murl = pgeturl.matcher(m.group());
		if (murl.find()) {
			mp3Info.artist = murl.group(2).replaceAll("<.*?>", "").trim()
					.toLowerCase();
		} else {
			mp3Info.artist = defaultArtist.toLowerCase();
		}
		if (mp3Info.artist == null || "".equals(mp3Info.artist)) {
			mp3Info.artist = "";
			mp3Info.saveFile = new File(savePath + File.separator
					+ mp3Info.title + ".mp3");
		} else {
			mp3Info.saveFile = new File(savePath + File.separator
					+ mp3Info.artist + "-" + mp3Info.title + ".mp3");
		}
		if (mp3Info.saveFile.exists()) {
			return null;
		}

		// 专辑
		if (!m.find()) {
			System.out.println("不是合法的链接地址，请检查：\n" + html);
			return null;
		}
		murl = pgeturl.matcher(m.group());
		if (murl.find()) {
			mp3Info.album = murl.group(2).replaceAll("<.*?>", "").trim();
		} else {
			mp3Info.album = "";
		}

		// 试听
		if (!m.find()) {
			System.out.println("不是合法的链接地址，请检查：\n" + html);
			return null;
		}

		// 歌词
		if (!m.find()) {
			System.out.println("不是合法的链接地址，请检查：\n" + html);
			return null;
		}

		// 铃声
		if (!m.find()) {
			System.out.println("不是合法的链接地址，请检查：\n" + html);
			return null;
		}

		// 尺寸
		if (m.find()) {
			mp3Info.size = m.group(1);
		} else {
			System.out.println("不是合法的链接地址，请检查：\n" + html);
			return null;
		}

		return mp3Info;
	}

	/**
	 * 通过一个下载任务TaskInfo，获得一首mp3的所有下载列表信息，按照大小排序 每个mp3下载链接保存在一个Mp3Info实体中
	 * 
	 * @param TaskInfo
	 *            , task.url 为这首mp3下载列表的url地址: url2
	 * @return List 一首mp3下载任务的多个链接Mp3Info实体组出的List
	 */
	List<Mp3Info> getDownloadList(TaskInfo task) {
		String html = null;
		List<Mp3Info> list = new ArrayList<Mp3Info>();
		try {
			html = getHtml(task.url);
		} catch (MalformedURLException ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
		if (html == null) {
			return list;
		}
		Pattern p = Pattern.compile("<td class=tdn>.*?<td class=spd>",
				Pattern.DOTALL);
		Matcher m = p.matcher(html);
		while (m.find()) {
			try {
				String srcstr = m.group();
				Mp3Info mp3Info = splitUrl(srcstr, task.artist);
				if (mp3Info != null
						&& mp3Info.title.equalsIgnoreCase(task.title)
						&& mp3Info.artist.equalsIgnoreCase(task.artist)) {
					list.add(mp3Info);
				}
			} catch (Exception ex) {
				System.out.println("Url " + task.url + " " + ex.getMessage());
			}
		}
		// Collections.sort(list);
		Collections.reverse(list);
		return list;
	}

	/**
	 * 根据一个下载任务TaskInfo下载一首mp3
	 * 
	 * @param TaskInfo
	 *            task 为一首mp3的下载任务,task.url为该mp3的下载列表页面的url
	 * 
	 */
	public void downloadmp3(TaskInfo task) {
		try {
			List<Mp3Info> dlist = getDownloadList(task);
			if (dlist == null) {
				return;
			}
			for (Mp3Info mp3Info : dlist) {
				String downUrl = mp3Info.downUrl;
				String size = mp3Info.size;

				// 得到最终的下载URL
				downUrl = getDownUrl(downUrl);
				if (downUrl == null) {
					continue;
				}

				System.out.println("开始下载 " + mp3Info.saveFile.getAbsoluteFile()
						+ " " + mp3Info.album + " 从 " + downUrl + " [" + size
						+ "]");
				mp3Info.currDownUrl = downUrl;
				mp3Info.pos = 0;
				if (downloadFile(mp3Info)) {
					try {
						Mp3Tag.convert(mp3Info);
					} catch (Exception ex) {
						ex.printStackTrace();
						System.out.println("File "
								+ mp3Info.saveFile.getAbsoluteFile() + " "
								+ ex.getMessage());
						mp3Info.saveFile.delete();
						continue;
					}
					break;
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
		}
		finishTaskCount = finishTaskCount + 1;
	}

	/*
	 * 根据链接得到最终的下载URL
	 */
	private String getDownUrl(String link) {
		String mhtml2;
		try {
			mhtml2 = getHtml(link);
		} catch (Exception ex) {
			return null;
		}
		Pattern purl = Pattern.compile("var I=\"(.*?)\"");
		Matcher murl = purl.matcher(mhtml2);
		if (murl.find()) {
			// 得到加密字符串
			// s440://p00zz.nzx/53p23/CBBBI/x53tn/DBBJBJBGCIEDFBDBEHI.7xl
			String enStr = murl.group(1);
			String s = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
			Map<Character, Integer> s_ord = new HashMap();
			for (int i = 0; i < s.length(); i++) {
				s_ord.put(s.charAt(i), i);
			}
			int dif = 0;
			if (enStr.indexOf(":") == 3) {
				// ftp://
				dif = s_ord.get('f') + s.length() - s_ord.get(enStr.charAt(0));
			} else {
				// http://
				dif = s_ord.get('h') + s.length() - s_ord.get(enStr.charAt(0));
			}
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < enStr.length(); i++) {
				char c = enStr.charAt(i);
				if (s_ord.get(c) == null) {
					sb.append(c);
				} else {
					c = s.charAt((s_ord.get(c) + dif) % s.length());
					sb.append(c);
				}
			}
			return sb.toString();
		}
		return null;
	}

	int allTaskCount = 0;
	int finishTaskCount = 0;

	/**
	 * 下载入口方法
	 * 
	 * 输入参数是taskList下载任务列表，对于任务列表中isEnabled的任务进行下载 按照线程池的方式，启动多个线程下载任务列表中的mp3
	 * 
	 */
	public void downloadAllMp3() throws MalformedURLException, IOException {
		executorService = Executors.newFixedThreadPool(POOL_SIZE);
		try {
			if (taskList == null) {
				return;
			}
			allTaskCount = 0;
			finishTaskCount = 0;
			new File(savePath).mkdirs();
			for (TaskInfo taskInfo : taskList) {
				if (!taskInfo.isEnabled) {
					continue;
				}
				allTaskCount = allTaskCount + 1;
				String name = taskInfo.title;
				String url = taskInfo.url;
				DownThread d = new DownThread();
				d.taskInfo = taskInfo;
				d.task = this;
				executorService.submit(d);
			}
		} finally {
			executorService.shutdown();
		}
	}
}
