package jlx.tools.research.procesor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jlx.tools.research.vo.CompanyInfo;
import jlx.util.ConfigUtil;
import jlx.util.RegexUtils;
import jlx.util.log.DebugLogger;

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
public class WebProcessor {
    /**
     * 获得公司信息
     * 
     * @param key
     * @param url
     * @return
     * @throws Exception
     */
    public List<CompanyInfo> parseWeb(final String key, final String webURL) {
        try {
            long start = System.currentTimeMillis();

            List<CompanyInfo> result = new ArrayList<CompanyInfo>();
            Connection conn = Jsoup.connect(webURL);
            // 加上agent，防止返回wap页面的内容
            conn.userAgent("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)");
            conn.timeout(10000);
            conn.followRedirects(false);
            // long t1 = System.currentTimeMillis();
            Document doc1 = conn.get();
            // System.out.println("哈哈：" + (System.currentTimeMillis() - start));
            String page = doc1.html();

            List<String> alist = RegexUtils.getMatchList(page, ConfigUtil.getComInfoHtmlListByKey(key));

            // 为main函数加点日志
            if (System.getProperty("TestMain") != null) {
                System.out.println(page);
                System.out.println("网站匹配列表：" + alist);
            }
            
            if (alist == null || alist.isEmpty()) {
                System.err.println("获得：" + key + "网站列表失败，请联系mike:henry.luck@gmail.com！");
                return result;
            }
            result = getParser(key).parse(alist, key, webURL);
            DebugLogger.log("fresh web：" + key + ",公司数量：" + result.size() + ",用时："
                    + (System.currentTimeMillis() - start));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * {method description}.
     * @param key
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     */
    private IParser getParser(final String key) throws InstantiationException, IllegalAccessException,
            ClassNotFoundException {
        String parserClass = ConfigUtil.getParserByKey(key);
        IParser parser = null;
        if (parserClass == null) {
            parser = new AnchorParser();
        } else {
            parser = (IParser) WebProcessor.class.getClassLoader().loadClass(parserClass).newInstance();
        }
        return parser;
    }


    //
    // /**
    // * 获得公司信息
    // *
    // * @param key
    // * @param url
    // * @return
    // * @throws Exception
    // */
    // public Map<String, CompanyInfo> parseWeb1(String key, String url) {
    // try {
    // long start = System.currentTimeMillis();
    //
    // final WebClient webClient = new WebClient(BrowserVersion.INTERNET_EXPLORER_8);
    // final HtmlPage page = webClient.getPage(url);
    // // 得到当前页连接公司列表连接对象
    // final List<?> anchorList = page.getByXPath(ConfigUtil.getAnchorListXPathByKey(key));
    // // 公司超级链接放到map里面，可以排除重复
    // Map<String, HtmlAnchor> comMap = new HashMap<String, HtmlAnchor>();
    // for (Object object : anchorList) {
    // HtmlAnchor a = (HtmlAnchor) object;
    // // 处理url链接，如果A是相当链接，拼成绝对的
    // String href = a.getHrefAttribute();
    // if (href.indexOf("http:") == -1) {
    // if (href.startsWith("/")) {
    // a.setAttribute("href", UrlStrUtil.getBaseUri(url) + href);
    // } else {
    // a.setAttribute("href", UrlStrUtil.getCurrentUri(url) + href);
    // }
    //
    // }
    // comMap.put(a.asText(), a); // 公司名称->HtmlAnchor
    // }
    // webClient.closeAllWindows();
    // DebugLogger.log("刷新网站：" + key + ",用时：" + (System.currentTimeMillis() - start));
    // return new CompanyInfoSpider().pareseCompanys(comMap);
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // return null;
    // }

    public static  void printPage(final String webURL) throws IOException{
        Document doc1 = Jsoup.connect(webURL).get();
        String page = doc1.html();
        System.out.println(page);
    }

    public static void main(final String[] args) throws Exception {
        System.setProperty("TestMain", "true");
        new WebProcessor().parseWeb("chinahr", ConfigUtil.getDefaultURLByKey("chinahr"));
//        printPage("http://www.51job.com/tianjin");
    }
}