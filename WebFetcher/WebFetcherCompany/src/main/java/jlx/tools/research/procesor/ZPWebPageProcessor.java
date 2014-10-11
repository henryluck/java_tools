package jlx.tools.research.procesor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jlx.tools.research.ZPWebConnInfo;
import jlx.tools.research.vo.CompanyInfo;
import jlx.tools.webfetcher.processor.IProcessor;
import jlx.tools.webfetcher.task.BaseConnInfo;
import jlx.util.ConfigUtil;
import jlx.util.RegexUtils;
import jlx.util.log.DebugLogger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * <br>
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
public class ZPWebPageProcessor implements IProcessor<CompanyInfo>{
    
    @Override
    public List<CompanyInfo> process(final String page, final BaseConnInfo connInfo) {
        try {
            long start = System.currentTimeMillis();
            
            List<CompanyInfo> result = new ArrayList<CompanyInfo>();
            ZPWebConnInfo conn = (ZPWebConnInfo)connInfo;
            String key = conn.getWebKey();
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
            result = getParser(key).parse(alist, key, conn.getUrl());
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
    
    public static  void printPage(final String webURL) throws IOException{
        Document doc1 = Jsoup.connect(webURL).get();
        String page = doc1.html();
        System.out.println(page);
    }

    public static void main(final String[] args) throws Exception {
        System.setProperty("TestMain", "true");
        new WebProcessor().parseWeb("yicai", ConfigUtil.getDefaultURLByKey("yicai"));
//        printPage("http://www.51job.com/tianjin");
    }
}