package jlx.tools.refreshetao;

import static jlx.tools.research.utils.RegexUtils.getMatchList;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import jlx.tools.research.utils.DebugLogger;
import jlx.tools.research.utils.RegexUtils;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * 正则解析器<br>
 * <p>
 * Create on : 2012-6-1<br>
 * <p>
 * </p>
 * <br>
 * 
 * @version CompanyResearchTool v1.0
 *          <p>
 *          <br>
 *          <strong>Modify History:</strong><br>
 *          user modify_date modify_content<br>
 *          -------------------------------------------<br>
 *          <br>
 */
public class EtaoProcessor {
    
   private final String url = "http://www.etao.com/";
   private final String itemRegex = "<h3 class=\"feed-title\">(.*?)<div class=\"feed-data clearfix\">";
   public String titleRegex = "title=\"(.*?)\"";
   public String contentRegex = "<p>(.*?)</p>";
   public String urlRegex = "<a href=\"(.*?)\" title=";
   public String imageUrlRegex = "src=\"(.*?)\"";
    
    
    /**
     * 获得公司信息
     * 
     * @param key
     * @param url
     * @return
     * @throws Exception
     */
    public List<Goods> process() {
        try {
            long start = System.currentTimeMillis();

            List<Goods> result = new ArrayList<Goods>();
            Connection conn = Jsoup.connect(url);
            // 加上agent，防止返回wap页面的内容
            conn.userAgent("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)");
            conn.timeout(10000);
            conn.followRedirects(true);
            // long t1 = System.currentTimeMillis();
            Document doc1 = conn.get();
            // System.out.println("哈哈：" + (System.currentTimeMillis() - start));
            String page = doc1.html();

            List<String> alist = getMatchList(page, itemRegex);

            // 为main函数加点日志
            if (System.getProperty("TestMain") != null) {
                System.out.println(page);
                System.out.println("网站匹配列表：" + alist);
            }
            
            if (alist == null || alist.isEmpty()) {
                System.err.println("获得网站列表失败，请联系mike:henry.luck@gmail.com！");
                return result;
            }
            result = parse(alist);
            DebugLogger.log("fresh web 商品数量：" + result.size() + ",用时："
                    + (System.currentTimeMillis() - start));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Goods> parse(final List<String> alist) {
        List<Goods> result = new ArrayList<EtaoProcessor.Goods>();
        for (String item : alist) {
            Goods gs = new Goods();
            gs.content = RegexUtils.getMatchString(item,contentRegex);
            gs.imageUrl = RegexUtils.getMatchString(item,imageUrlRegex);
            gs.title = RegexUtils.getMatchString(item,titleRegex);
            gs.url = RegexUtils.getMatchString(item,urlRegex);
            gs.image = getImage(gs.imageUrl);
            result.add(gs);
        }
        
        return result;
    }

    public static  void printPage(final String webURL) throws IOException{
        Document doc1 = Jsoup.connect(webURL).get();
        String page = doc1.html();
        System.out.println(page);
    }
    public BufferedImage getImage(final String imageurl) {
        BufferedImage image = null;
        try {
            URL url = new URL(imageurl);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            image = ImageIO.read(connection.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return image;
    }

    public static void main(final String[] args) throws Exception {
        System.setProperty("TestMain", "true");
        new EtaoProcessor().process();
//        printPage("http://www.51job.com/tianjin");
    }
    class Goods{
        public String title;
        public String content;
        public String url;
        public String imageUrl;
        public BufferedImage image;
        /* (non-Javadoc) 为了collection使用contains方法比较使用
         * @see java.lang.Object#equals(java.lang.Object)
         */
        @Override
        public boolean equals(final Object obj) {
            if(obj instanceof Goods ){
                Goods gs = (Goods)obj;
                if(obj !=null && gs.url.equals(this.url)){
                    return true;
                }
            }
            return false;
        }
        /* (non-Javadoc)
         * @see java.lang.Object#hashCode()
         */
        @Override
        public int hashCode() {
            // TODO Auto-generated method stub
            return super.hashCode();
        }
    }
}