package jlx.tools.research.procesor;

import java.util.ArrayList;
import java.util.List;

import jlx.tools.research.vo.CompanyInfo;
import jlx.util.ConfigUtil;
import jlx.util.RegexUtils;
import jlx.util.UrlStrUtil;

/**
 * 特殊超链接 <br>
 * <p>
 * Create on : 2012-9-18<br>
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
public class RegexParser implements IParser {

    /* (non-Javadoc)
     * @see jlx.tools.research.procesor.IParser#parse(java.util.List)
     */
    @Override
    public List<CompanyInfo> parse(final List<String> contentList, final String key, final String webURL) {
        List<CompanyInfo> result = new ArrayList<CompanyInfo>();

        for (String aStr : contentList) {
            String name = RegexUtils.getMatchString(aStr, ConfigUtil.getRegexNamePatternByKey(key));
            String href = RegexUtils.getMatchString(aStr, ConfigUtil.getRegexURLPatternByKey(key));
            
            if(href ==null){
                href = "";
            }
            
            if (href.indexOf("http:") == -1) {
                if (href.startsWith("/")) {
                    href = UrlStrUtil.getBaseUri(webURL) + href;
                } else {
                    href = UrlStrUtil.getCurrentUri(webURL) + href;
                }
            }
            
           
            CompanyInfo info = new CompanyInfo();
            info.setName(name);
            info.setDetailURL(href);
            if (!result.contains(info)) {
                result.add(info);
            }
        }

        return result;
    }

}
