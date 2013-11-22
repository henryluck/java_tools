package jlx.tools.webfetcher;

import java.util.List;

/**
 * 可以更新内容的frame接口
 * <br>
 *  
 * <p>
 * Create on : 2013-4-8<br>
 * <p>
 * </p>
 * <br>
 * @author mike<br>
 * @version CompanyResearchTool v1.0
 * <p>
 *<br>
 * <strong>Modify History:</strong><br>
 * user     modify_date    modify_content<br>
 * -------------------------------------------<br>
 * <br>
 */
public interface IUpdateTextFrame<T> {
    /**
     * {更新內容 }.
     * 
     * @param collection
     */
    public void updateAreaTxt(final List<T>  collection);

}
