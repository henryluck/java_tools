package jlx.tools.research.task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import jlx.tools.research.ResearchConstants;
import jlx.tools.research.frame.IMainFrame;
import jlx.tools.research.utils.DebugLogger;
import jlx.tools.research.vo.CompanyInfo;

/**
 *
 * @author jianglx
 */
public class TaskManager implements Runnable{
    
    private ExecutorService pool = Executors.newFixedThreadPool(ResearchConstants.THERAD_POOL_SIZE);
    private List<TaskInfo> taskList = new ArrayList<TaskInfo>();
    private IMainFrame mainFrame;
    //是否在运行
    private boolean enable = false;
    
    public TaskManager(IMainFrame mainFrame){
        this.mainFrame = mainFrame;
    }
    
    public void addTask(TaskInfo task){
        taskList.add(task);
        
    }
    public void removeTask(TaskInfo task){
        taskList.remove(task);
        
    }
    
    public void start() {
        enable = true;
        new Thread(this).start();
    }
    public void stop(){
        enable = false;
    }

    /**
     * {method description}.
     */
    public void clear() {
        taskList.clear();
    }

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        while(enable){
            try {
                long start = System.currentTimeMillis();
               
                List<Future<List<CompanyInfo>>> results = pool.invokeAll(taskList);
                //刷新内容
                for (Future<List<CompanyInfo>> future : results) {
                    List<CompanyInfo> result = future.get();
                    if(result != null){
                        mainFrame.updateAreaTxt(result);
                    }
                }
                
                DebugLogger.log("一次用时："+(System.currentTimeMillis()-start));
                Thread.sleep(10000);
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
