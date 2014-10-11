package jlx.util;

import jlx.util.log.DebugLogger;

/**
 * {class description}
 * <br>
 *  
 * <p>
 * Create on : 2012-6-1<br>
 * <p>
 * </p>
 * <br>
 * @author henry.luck@gmail.com<br>
 * @version CompanyResearchTool v1.0
 * <p>
 *<br>
 * <strong>Modify History:</strong><br>
 * user     modify_date    modify_content<br>
 * -------------------------------------------<br>
 * <br>
 */
public class UrlStrUtil {
    /**
     * http://search.51job.com
     * @param url
     * @return
     */
    public static String getBaseUri(final String url){
        if(url == null){
            return null;
        }
        return RegexUtils.getMatchString(url, "(http://.*?)/");
    }
    /**
     * 得到当前的url，就是去掉最后的资源名称后的地址
     * @param url
     * @return
     */
    public static String getCurrentUri(final String url){
        if(url == null){
            return null;
        }
        return RegexUtils.getMatchString(url, "(http://.*/)");
    }
    
    public static void main(final String[] args) {
        DebugLogger.log(UrlStrUtil.getCurrentUri("http://search.51job.com/list/co,c,2532882,0000,10,1.html"));
    }
}
