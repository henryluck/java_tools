/*
 * 一个mp3链接为这么一个实体
 */
package downloadmp3;

import java.io.File;

/**
 *
 * @author oneleaf
 */
public class Mp3Info implements Comparable {

    String title;
    String artist;
    String album;
    /**
     * 该mp3下载链接的地址url：url3
     */
    String downUrl;
    String size;
    /**
     * 保存文件名
     */
    File saveFile;
    /**
     * 从downUrl所在的页面解析出来的实际下载地址url4
     */
    String currDownUrl;
    int length;
    int pos;

    public int compareTo(Object arg0) {
        return this.size.compareTo(((Mp3Info) arg0).size);
    }

    public String toString() {
        return artist + "-" + title + "[" + size + "]:" + downUrl;
    }
}
