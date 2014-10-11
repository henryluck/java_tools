package jlx.util;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class WebUtil {

    /**
     * {method description}.
     * 
     * @param webURL
     * @return
     * @throws Exception
     */
    public static String getWebPageContent(final String webURL) throws Exception {
        Connection conn = Jsoup.connect(webURL);
        // 加上agent，防止返回wap页面的内容
        conn.userAgent("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)");
        conn.timeout(10000);
        conn.followRedirects(true);
        // long t1 = System.currentTimeMillis();
        Document doc1 = conn.get();
        // System.out.println("哈哈：" + (System.currentTimeMillis() - start));
        return doc1.html();
    }

    /**
     * 得到网络上的url
     * 
     * @param imageUrl
     * @return
     */
    public static BufferedImage getImage(final String imageUrl) {
        BufferedImage image = null;
        try {
            URL url = new URL(imageUrl);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            image = ImageIO.read(connection.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    /**
     * 下载文件
     * 
     * @param strUrl
     * @param path
     */
    public static void downloadFile(final String strUrl, final String path) {
        URL url = null;
        try {
            url = new URL(strUrl);
            InputStream is = url.openStream();
            OutputStream os = new FileOutputStream(path,false);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = is.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

}
