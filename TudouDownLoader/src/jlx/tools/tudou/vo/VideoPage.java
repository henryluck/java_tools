package jlx.tools.tudou.vo;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import jlx.tools.utils.RegexUtils;

/**
 * 描述播放页面的一个page <br>
 * <p>
 * Create on : 2012-11-7<br>
 * <p>
 * </p>
 * <br>
 * 
 * @author jianglinxue@ruijie.com.cn<br>
 * @version TudouDownLoader v1.0
 *          <p>
 *          <br>
 *          <strong>Modify History:</strong><br>
 *          user modify_date modify_content<br>
 *          -------------------------------------------<br>
 *          <br>
 */
public class VideoPage {
    /**
     * 从baseurl里面截取iid的正则表达式
     */
    private static final String IID_PATTREN = "/(\\d*)\\.h264";

    /**
     * itudou下载的url
     */
    private static final String TUDOU_URL_FORMAT = "tudou://{0}:st={1}/";
    //当前页面url
    private final String url;
    private List<String> playListURLs;
    private List<String> videoPageURLs;
    // 视频下载地址
    // private List<String> videoUrls;
    // 视频对象列表
    private List<Video> videoList;

    public VideoPage(String url) {
        this.url = url;
    }

    /**
     * @return url - {return content description}
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return playListURLs - {return content description}
     */
    public List<String> getPlayListURLs() {
        return playListURLs;
    }

    /**
     * @param playListURLs - {parameter description}.
     */
    public void setPlayListURLs(List<String> playListURLs) {
        this.playListURLs = playListURLs;
    }

    /**
     * @return videoURLs - {return content description}
     */
    public List<String> getVideoPageURLs() {
        return videoPageURLs;
    }

    /**
     * @param videoURLs - {parameter description}.
     */
    public void setVideoPageURLs(List<String> videoPageURLs) {
        this.videoPageURLs = videoPageURLs;
    }

    /**
     * @return videoUrls - 这个不太对
     */
    public List<String> getVideoUrls() {
        List<String> result = new ArrayList<String>();
        if (videoList != null) {
            for (Video video : videoList) {
                result.add(video.getFullUrl());
            }

        }
        return result;
    }

    /**
     * @return videoList - {return content description}
     */
    public List<Video> getVideoList() {
        return videoList;
    }

    /**
     * @param videoList - {parameter description}.
     */
    public void setVideoList(List<Video> videoList) {
        this.videoList = videoList;
    }

    /**
     * {method description}.
     * 
     * @return
     */
    public List<String> getIidList() {
        List<String> result = new ArrayList<String>();
        if (videoList != null) {
            for (Video video : videoList) {
                String baseUrl = video.getBaseUrl();
                String iid = RegexUtils.getMatchString(baseUrl, IID_PATTREN);
                result.add(iid);
            }
        }
        return result;
    }

    /**
     * {method description}.
     * 
     * @return
     */
    public List<String> getITudouURList() {
        List<String> result = new ArrayList<String>();
        if (videoList != null) {
            for (Video video : videoList) {
                String baseUrl = video.getBaseUrl();
                String iid = RegexUtils.getMatchString(baseUrl, IID_PATTREN);
                result.add(MessageFormat.format(TUDOU_URL_FORMAT, iid, video.getPlayerType()));
            }
        }
        return result;
    }
}