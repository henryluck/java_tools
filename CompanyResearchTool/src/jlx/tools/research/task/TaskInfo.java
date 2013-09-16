package jlx.tools.research.task;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import jlx.tools.research.MainFrame;
import jlx.tools.research.procesor.WebProcessor;
import jlx.tools.research.vo.CompanyInfo;

/**
 * @author jianglx
 */
public class TaskInfo implements Callable<List<CompanyInfo>>{

    /**
     * 任务url
     */
    String url;
    /**
     * key，为了和配置文件绑定
     */
    String key;
    
    public TaskInfo(String key,String url){
        this.key = key;
        this.url = url;
    }

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public List<CompanyInfo> call() throws Exception{
        List<CompanyInfo> result = new WebProcessor().parseWeb(key, url);
        //mainFrame.updateAreaTxt(map.values());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        result = prime * result + ((url == null) ? 0 : url.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TaskInfo other = (TaskInfo) obj;
        if (key == null) {
            if (other.key != null)
                return false;
        } else if (!key.equals(other.key))
            return false;
        if (url == null) {
            if (other.url != null)
                return false;
        } else if (!url.equals(other.url))
            return false;
        return true;
    }

}
