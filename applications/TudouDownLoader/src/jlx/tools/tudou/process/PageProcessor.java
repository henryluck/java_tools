package jlx.tools.tudou.process;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import jlx.tools.tudou.TudouDownloader;
import jlx.tools.tudou.vo.Video;
import jlx.tools.tudou.vo.VideoPage;
import jlx.tools.utils.DebugLogger;
import jlx.tools.utils.RegexUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.TextPage;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

// 处理器，抓页面，解析
public class PageProcessor {

    private final WebClient webClient;
    private final Video filter;

    /**
     * 匹配播放列表url的正则表达式
     */
    public static final String PLAYLIST_URL_PATTREN = "(http://www.tudou.com/listplay/.*?.html)";
    /**
     * 匹配播放视频页面url的正则表达式
     */
    public static final String VIDEO_PAGE_URL_PATTREN = "(http://www.tudou.com/programs/view/.*?)[/\"]";
    /**
     * 获得视频下载地址json串的json
     */
    private static final String VIDEO_DOWNLOAD_URL_PRX = "http://www.tudou.com/outplay/goto/getCdnFileList.action?code=";

    public PageProcessor(WebClient client, Video filter) {
        this.webClient = client;
        this.filter = filter;
    }

    public VideoPage process(String url) throws Exception {
        DebugLogger.log("解析根URL:" + url);

        VideoPage vp = new VideoPage(url);
        HtmlPage page = webClient.getPage(url);
        String pageAsXml = page.asXml();
        // 解析播放的url和豆单的url
        List<String> playListUrls = RegexUtils.getMatchList(pageAsXml, PLAYLIST_URL_PATTREN);
        List<String> videoPageUrls = RegexUtils.getMatchList(pageAsXml, VIDEO_PAGE_URL_PATTREN);
        // String iid = RegexUtils.getMatchString(pageAsXml, "iid:(\\d*)");

        // vp.setIid(iid);
        vp.setPlayListURLs(playListUrls);
        vp.setVideoPageURLs(videoPageUrls);

        List<Video> videos = parseVideoByVideoURL(videoPageUrls);
        videos.addAll(parseVideoByListURL(playListUrls));
        if (videos != null) {
            vp.setVideoList(videos);
        }
        return vp;
    }

    /**
     * {method description}.
     * 
     * @param playListUrls
     * @return
     * @throws IOException
     * @throws MalformedURLException
     * @throws FailingHttpStatusCodeException
     */
    private Collection<? extends Video> parseVideoByListURL(List<String> playListUrls) throws Exception {
        List<Video> result = new ArrayList<Video>();
        for (String url : playListUrls) {
            if (TudouDownloader.stop) {
                break;
            }
            DebugLogger.log("开始解析URL:" + url);

            String code = RegexUtils.getMatchString(url, "/([^/]*)\\.html");

            Video v = parseVideoJson(code);
            if (v != null) {
                result.add(v);
            }
        }
        return result;
    }

    private Video parseVideoJson(String code) throws Exception {
        Video result = null;
        TextPage page = webClient.getPage(VIDEO_DOWNLOAD_URL_PRX + code);
        String pageAsXml = page.getContent();
        JSONObject jObj = JSONObject.fromObject(pageAsXml);

        String status = jObj.getString("status");
        if (!status.equals("1")) {
            return null;
        }
        JSONArray videObjs = jObj.getJSONArray("message");
        JSONArray.toArray(videObjs, Video.class);
        List<Video> list = getDTOList(videObjs, Video.class);

        for (int i = 0; i < list.size(); i++) {
            Video video = list.get(i);
            // 判断画质和文件类型
            if (video.getPlayerType().equals(filter.getPlayerType())
                   /* && filter.getEncodeType().equals(video.getEncodeType())*/) {
                result = video;
                break;
            }
        }
        return result;
    }

    /**
     * {method description}.
     * 
     * @param videoPageUrls
     * @return
     * @throws Exception
     */
    private List<Video> parseVideoByVideoURL(List<String> videoPageUrls) throws Exception {

        List<Video> result = new ArrayList<Video>();
        for (String url : videoPageUrls) {
            if (TudouDownloader.stop) {
                break;
            }
            DebugLogger.log("开始解析URL:" + url);

            String code = url.substring(url.lastIndexOf('/') + 1);

            Video v = parseVideoJson(code);
            if (v != null) {
                result.add(v);
            }
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> getDTOList(JSONArray array, Class<T> clazz) {
        List<T> list = new ArrayList<T>();
        for (Iterator<T> iter = array.iterator(); iter.hasNext();) {
            JSONObject jsonObject = (JSONObject) iter.next();
            list.add((T) JSONObject.toBean(jsonObject, clazz));
        }
        return list;
    }

    public static void main(String[] args) {
        String url = "http://www.tudou.com/programs/view/IfGvSBPFghY";
        System.out.println(url.substring(url.lastIndexOf('/') + 1));
    }
}