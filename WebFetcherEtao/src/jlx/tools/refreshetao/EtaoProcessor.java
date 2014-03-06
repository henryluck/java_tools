package jlx.tools.refreshetao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jlx.tools.webfetcher.processor.IProcessor;
import jlx.tools.webfetcher.task.BaseConnInfo;
import jlx.util.RegexUtils;
import jlx.util.WebUtil;

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
public class EtaoProcessor implements IProcessor<GoodsVO>{
    
   private final String url = "http://www.etao.com/";
   private final String itemRegex = "<h3 class=\"feed-title\">(.*?)<div class=\"feed-data clearfix\">";
   public String titleRegex = "title=\"(.*?)\"";
   public String contentRegex = "<p>(.*?)</p>";
   public String urlRegex = "<a href=\"(.*?)\" title=";
   public String imageUrlRegex = "src=\"(.*?)\"";
    
    
    private List<GoodsVO> parse(final List<String> alist) {
        List<GoodsVO> result = new ArrayList<GoodsVO>();
        for (String item : alist) {
            GoodsVO gs = new GoodsVO();
            gs.content = RegexUtils.getMatchString(item,contentRegex);
            gs.imageUrl = RegexUtils.getMatchString(item,imageUrlRegex);
            gs.title = RegexUtils.getMatchString(item,titleRegex);
            gs.url = RegexUtils.getMatchString(item,urlRegex);
            gs.image = WebUtil.getImage(gs.imageUrl);
            result.add(gs);
        }
        
        return result;
    }

    public static  void printPage(final String webURL) throws IOException{
        Document doc1 = Jsoup.connect(webURL).get();
        String page = doc1.html();
        System.out.println(page);
    }
    
    @Override
    public List<GoodsVO> process(final String page, final BaseConnInfo connInfo) {
        try {
            List<GoodsVO> result = new ArrayList<GoodsVO>();

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