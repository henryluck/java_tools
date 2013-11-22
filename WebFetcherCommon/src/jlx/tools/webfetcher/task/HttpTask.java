package jlx.tools.webfetcher.task;

import java.util.List;
import java.util.concurrent.Callable;

import jlx.tools.webfetcher.processor.IProcessor;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * T:task执行完，返回的结果对象
 * @author jianglx
 */
public class HttpTask<T> implements Callable<List<T>>{

    /**
     * 可以extends BaseConnInfo，加别的属性
     */
    final  BaseConnInfo connInfo;
    final  IProcessor<T>  processor;
    
    public HttpTask(final BaseConnInfo connInfo,final IProcessor<T> processor){
        this.connInfo = connInfo;
        this.processor = processor;
    }

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public List<T> call() throws Exception{
        Connection conn = Jsoup.connect(this.connInfo.getUrl());
        // 加上agent，防止返回wap页面的内容
        conn.userAgent("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)");
        conn.timeout(10000);
        conn.followRedirects(true);
        Document doc1 = conn.get();
        String page = doc1.html();
        return processor.process(page,connInfo);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((connInfo.getUrl() == null) ? 0 : connInfo.getUrl().hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        HttpTask other = (HttpTask) obj;
        String url = connInfo.getUrl();
        if (url == null) {
            if (other.connInfo.getUrl() != null)
                return false;
        } else if (!url.equals(other.connInfo.getUrl()))
            return false;
        return true;
    }

}
