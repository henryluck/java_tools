package jlx.tools.research;

import jlx.tools.webfetcher.task.BaseConnInfo;

/**
 * {class description}
 * <br>
 *  
 * <p>
 * Create on : 2013-11-22<br>
 * </p>
 * <br>
 * @author henry<br>
 * @version WebFetcherCompany v6.3.0
 * <br>
 * <strong>Modify History:</strong><br>
 * user     modify_date    modify_content<br>
 * -------------------------------------------<br>
 * <br>
 */
public class ZPWebConnInfo extends BaseConnInfo {
    
    /**
     * <code>webKey</code> - {description}.
     */
    private String webKey;
    /**
     * @return webKey - {return content description}
     */
    public String getWebKey() {
        return webKey;
    }
    /**
     * @param webKey - {parameter description}.
     */
    public void setWebKey(final String webKey) {
        this.webKey = webKey;
    }
    /**
     * Constructors.
     */
    public ZPWebConnInfo() {
        // TODO Auto-generated constructor stub
    }

}
