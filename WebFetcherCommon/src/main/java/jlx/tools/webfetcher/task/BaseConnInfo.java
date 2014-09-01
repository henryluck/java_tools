package jlx.tools.webfetcher.task;

/**
 *  链接信息抽象，默认只有url属性
 * <br>
 *  
 * <p>
 * Create on : 2013-11-21<br>
 * </p>
 * <br>
 * @author henry<br>
 * @version WebFetcherCommon v6.3.0
 * <br>
 * <strong>Modify History:</strong><br>
 * user     modify_date    modify_content<br>
 * -------------------------------------------<br>
 * <br>
 */
public class BaseConnInfo {
    
    private String url;

    /**
     * Constructors.
     */
    public BaseConnInfo() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @return url - {return content description}
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url - {parameter description}.
     */
    public void setUrl(final String url) {
        this.url = url;
    }

}
