package jlx.tools.research.procesor;

import java.util.List;

import jlx.tools.research.vo.CompanyInfo;

/**
 * {class description}
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
public interface IParser {


    /**
     * {method description}.
     * 
     * @param contentList
     * @param key
     * @param webURL
     * @return
     */
    List<CompanyInfo> parse(List<String> contentList, String key, String webURL);
}
