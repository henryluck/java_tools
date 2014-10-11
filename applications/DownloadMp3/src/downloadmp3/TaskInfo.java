/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadmp3;

/**
 * 一首mp3下载任务
 *
 * 一首mp3可以包含多个下载链接
 * @author oneleaf
 */
public class TaskInfo implements Comparable {

    /**
     * 任务编号
     */
    int no;
    /**
     * mp3名字
     */
    String title;
    /**
     * 歌手
     */
    String artist;
    /**
     * 这首mp3第一页多个下载链接页面的url
     * 为：url2
     */
    String url;
    /**
     * 这个下载任务是否有效的状态位
     */
    boolean isEnabled;

    public int compareTo(Object arg0) {
        return (this.no - ((TaskInfo) arg0).no) ;
    }
}
