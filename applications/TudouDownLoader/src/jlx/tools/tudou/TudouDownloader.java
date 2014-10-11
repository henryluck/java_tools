package jlx.tools.tudou;

import java.util.ArrayList;
import java.util.List;

import jlx.tools.tudou.process.PageProcessor;
import jlx.tools.tudou.vo.Video;
import jlx.tools.tudou.vo.VideoPage;
import jlx.tools.utils.DebugLogger;

import com.gargoylesoftware.htmlunit.WebClient;

/**
 * {class description} <br>
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
public class TudouDownloader implements Runnable {
    /**
     * 搜索视频的起始url地址
     */
    private String startUrlString = "http://music.tudou.com/";
    /**
     * 搜索深度，默认1层
     */
    private int deep = 1;
    private Video filter = new Video();

    /**
     * 视频iid，可以调用itudo下载 tudou://119573839:st=99/ ；119573839就是iid，99代表原画
     */
    private List<String> iidList;
    //itudou客户端使用的下载地址
    private List<String> iTudouURList;
    // 是否停止
    public static boolean stop = false;

    /**
     * Constructors.
     */
    public TudouDownloader(String startUrlString, int deep, String quality,String encodeType) {
        this.startUrlString = startUrlString;
        this.deep = deep;
        filter.setPlayerType(quality);
        filter.setEncodeType(encodeType);
        
    }

    /**
     * 视频iid列表，可以调用itudou下载
     * 
     * @return
     * @throws Exception
     */
    /**
     * @return iidList - {return content description}
     * @throws Exception
     */
    public List<String> getIidList() throws Exception {
        if(iidList ==null){
            work();
        }
        return iidList;
    }

    /**
     * @return iTudouURList - {return content description}
     * @throws Exception
     */
    public List<String> getiTudouURList() throws Exception {
        if (iTudouURList == null) {
            work();
        }
        return iTudouURList;
    }

    private void work() throws Exception {
        iTudouURList = new ArrayList<String>();

        final WebClient webClient = new WebClient();
        PageProcessor processor = new PageProcessor(webClient, filter);

        // 保存每层解析时候需要处理的url列表
        List<String> needProcessUrls = new ArrayList<String>();
        needProcessUrls.add(startUrlString);

        for (int i = 0; i < deep; i++) {
            if (TudouDownloader.stop) {
                break;
            }
            // 本深度解析html发现的playlist url列表
            List<String> newUrls = new ArrayList<String>();
            for (String url : needProcessUrls) {
                if (TudouDownloader.stop) {
                    break;
                }
                VideoPage vp = processor.process(url);
                iTudouURList.addAll(vp.getITudouURList());
                newUrls.addAll(vp.getPlayListURLs());
            }
            needProcessUrls.clear();
            needProcessUrls.addAll(newUrls);
        }
        webClient.closeAllWindows();
    }

    /**
     * 下载
     * 
     * @throws Exception
     */
    public void startDownload() throws Exception {
        new Thread(this).start();
    }

    public void stop() throws Exception {

    }

    /*
     * (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        try {
            work();
            if (TudouDownloader.stop) {
                return;
            }
            List<String> tudouList = getiTudouURList();
            DebugLogger.log("解析视频数量：" + tudouList.size());
            DebugLogger.log("开始调研itudou下载...");
            for (String url : tudouList) {
                Runtime.getRuntime().exec("cmd /c start " + url);
                Thread.sleep(100); // 等待，不然itudou容易死
            }
            DebugLogger.log("完成.");
            MainFrame.m_btnGo.setEnabled(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /**
     * @param startUrlString - {parameter description}.
     */
    public void setStartUrlString(String startUrlString) {
        this.startUrlString = startUrlString;
    }

    /**
     * @return deep - {return content description}
     */
    public int getDeep() {
        return deep;
    }

    /**
     * @param deep - {parameter description}.
     */
    public void setDeep(int deep) {
        this.deep = deep;
    }

    /**
     * {method description}.
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        new TudouDownloader("http://music.tudou.com/", 1, "99", "f4v").startDownload();
        // Runtime.getRuntime().exec("start tudou://156503238:st=99/");
        // Process child = Runtime.getRuntime().exec("cmd /c start tudou://156503238:st=99/");

    }
}
