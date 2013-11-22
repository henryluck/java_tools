package jlx.tools.research;

import java.util.ArrayList;
import java.util.List;

import jlx.tools.research.vo.CompanyInfo;
import jlx.tools.webfetcher.processor.IProcessor;
import jlx.tools.webfetcher.task.BaseConnInfo;
import jlx.util.RegexUtils;

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
public class ZPWebPageProcessor implements IProcessor<CompanyInfo>{
    
    @Override
    public List<CompanyInfo> process(final String page, final BaseConnInfo connInfo) {
        try {
            List<CompanyInfo> result = new ArrayList<CompanyInfo>();

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