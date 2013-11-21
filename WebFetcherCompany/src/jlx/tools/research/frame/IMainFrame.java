package jlx.tools.research.frame;

import java.util.List;

/**
 * {class description}
 * <br>
 *  
 * <p>
 * Create on : 2013-4-8<br>
 * <p>
 * </p>
 * <br>
 * @author jianglinxue<br>
 * @version CompanyResearchTool v1.0
 * <p>
 *<br>
 * <strong>Modify History:</strong><br>
 * user     modify_date    modify_content<br>
 * -------------------------------------------<br>
 * <br>
 */
public interface IMainFrame<T> {
    /**
     * {更新內容 }.
     * 
     * @param collection
     */
    public void updateAreaTxt(final List<T>  collection);

}
