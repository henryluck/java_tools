package jlx.tools.research.procesor;

import java.util.ArrayList;
import java.util.List;

import jlx.tools.research.vo.CompanyInfo;
import jlx.util.ConfigUtil;
import jlx.util.RegexUtils;
import jlx.util.UrlStrUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * 解析超链接
 * <br>
 *  
 * <p>
 * Create on : 2012-9-18<br>
 * <p>
 * </p>
 * <br>
 * @version CompanyResearchTool v1.0
 * <p>
 *<br>
 * <strong>Modify History:</strong><br>
 * user     modify_date    modify_content<br>
 * -------------------------------------------<br>
 * <br>
 */
public class AnchorParser implements IParser {

    /* (non-Javadoc)
     * @see jlx.tools.research.procesor.IParser#parse(java.util.List)
     */
    @Override
    public List<CompanyInfo> parse(final List<String> contentList, final String key, final String webURL) {
        List<CompanyInfo> result = new ArrayList<CompanyInfo>();

        for (String aStr : contentList) {
            Document adoc = Jsoup.parse(aStr);
            Element link = adoc.select("a").first();
            String href = link.attr("href");
            if (href.indexOf("http:") == -1) {
                if (href.startsWith("/")) {
                    href = UrlStrUtil.getBaseUri(webURL) + href;
                } else {
                    href = UrlStrUtil.getCurrentUri(webURL) + href;
                }
            }

            CompanyInfo info = new CompanyInfo();
            String name = link.text();
            //当那么是null的时候，用jmrc.home.AnchorNamePatternWhenNull解析一次试试
            if(name == null || name.equals("")){
                name = RegexUtils.getMatchString(aStr, ConfigUtil.getAnchorNamePatternWhenNullByKey(key));
            }
            info.setName(name);
            info.setDetailURL(href);
            if (!result.contains(info)) {
                result.add(info);
            }
        }

        return result;
    }

}
