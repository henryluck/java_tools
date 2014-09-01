package jlx.tools.webfetcher.processor;

import java.util.List;

import jlx.tools.webfetcher.task.BaseConnInfo;

/**
 * 解析器 <br>
 * <p>
 * Create on : 2013-11-21<br>
 * </p>
 * <br>
 * 
 * @author henry<br>
 * @version WebFetcherCommon v6.3.0 <br>
 *          <strong>Modify History:</strong><br>
 *          user modify_date modify_content<br>
 *          -------------------------------------------<br>
 * <br>
 */
public interface IProcessor<T> {
    /**
     * 解析页面返回List<T>
     * {method description}.
     * @param page
     * @return
     */
    List<T> process(String page,BaseConnInfo connInfo);
}
