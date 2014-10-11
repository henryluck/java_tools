package jlx.tools.refreshhouse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jlx.tools.webfetcher.processor.IProcessor;
import jlx.tools.webfetcher.task.BaseConnInfo;
import jlx.util.RegexUtils;
import jlx.util.WebUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

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
public class Web58HouseProcessor implements IProcessor<HouseVO>{
    
   public static final String url = "http://tj.58.com/jinghaiqu/ershoufang/e3/?key=%E6%B5%B7%E9%A6%A8%E5%9B%AD";//"http://tj.58.com/jinghaiqu/ershoufang/e3/";
   private final String itemRegex = "<tr logr=\"(.*?)</tr>";
   public String titleRegex = "<a href=\"[^\"]*\" target=\"_blank\" class=\"t\">(.*?)</a>";
   public String contentRegex = "<div class=\"qj-listleft\">(.*?)</div>";
   public String urlRegex = "<a href=\"([^\"]*)\" target=\"_blank\" class=\"t\">.*?</a>";
   public String imageUrlRegex = "<img src=\"([^\"]*)";
   public String priceRegex = "&nbsp;&nbsp; (.*?) \\s";
   public String priceAllRegex = "<b class=\"pri\">(.*?)</b>";
   public String mianjiegex = "<span class=\"showroom\">.*?</span>(.*?)\\s"; 
    
    private List<HouseVO> parse(final List<String> alist) {
        List<HouseVO> result = new ArrayList<HouseVO>();
        for (String item : alist) {
            HouseVO vo = new HouseVO();
            Document adoc = Jsoup.parse(RegexUtils.getMatchString(item,contentRegex));
            Element link = adoc.select("body").first();
            vo.content = link.text();
            
            vo.imageUrl = RegexUtils.getMatchString(item,imageUrlRegex);
            vo.title = RegexUtils.getMatchString(item,titleRegex);
            vo.url = RegexUtils.getMatchString(item,urlRegex);
            vo.image = WebUtil.getImage(vo.imageUrl);
            vo.price= RegexUtils.getMatchString(item, priceRegex);
            vo.priceAll = RegexUtils.getMatchString(item, priceAllRegex);
            vo.mianji = RegexUtils.getMatchString(item, mianjiegex);
            result.add(vo);
        }
        
        return result;
    }

    public static  void printPage(final String webURL) throws IOException{
        Document doc1 = Jsoup.connect(webURL).get();
        String page = doc1.html();
        System.out.println(page);
    }
    
    @Override
    public List<HouseVO> process(final String page, final BaseConnInfo connInfo) {
        try {
            List<HouseVO> result = new ArrayList<HouseVO>();

            List<String> alist = RegexUtils.getMatchList(page, itemRegex);

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
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}