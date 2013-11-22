package jlx.tools.webfetcher.task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import jlx.tools.webfetcher.IUpdateTextFrame;
import jlx.tools.webfetcher.processor.IProcessor;
import jlx.util.log.DebugLogger;

/**
 * T: 执行任务返回的结果对象
 * @author jianglx
 */
public class TaskManager<T> implements Runnable{
    
    private final ExecutorService pool = Executors.newFixedThreadPool(4);
    private final List<HttpTask<T>> taskList = new ArrayList<HttpTask<T>>();
    private final IUpdateTextFrame<T> mainFrame;
    //是否在运行
    private boolean enable = false;
    
    public TaskManager(final IUpdateTextFrame<T> mainFrame){
        this.mainFrame = mainFrame;
    }
    
    public void addTask(final HttpTask<T> task){
        taskList.add(task);
        
    }
    public void removeTask(final HttpTask<T> task){
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
               
                List<Future<List<T>>> results = pool.invokeAll(taskList);
                //刷新内容
                for (Future<List<T>> future : results) {
                    List<T> result = future.get();
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
    

    public static void main(final String[] args) {
        //主窗口,Integer是页面内容处理后返回的对象，一般是自己定义的VO
        IUpdateTextFrame<Integer> mainFrame = new IUpdateTextFrame<Integer>(){
            @Override
            public void updateAreaTxt(final List<Integer> result) {
                System.out.println("result=" + result);
                
            }};
           //处理器，处理页面字符串，返回结果
        IProcessor<Integer> process = new IProcessor<Integer>() {
            @Override
            public List<Integer> process(final String page,final BaseConnInfo connInfo) {
                List<Integer> result = new ArrayList<Integer>();
                result.add(new Integer(page.length()));
                return result;
            }
        };
        BaseConnInfo connInfo= new BaseConnInfo();
        connInfo.setUrl("http://www.baidu.com");
        
        TaskManager<Integer> mgrManager = new TaskManager<Integer>(mainFrame);
        HttpTask<Integer> task = new HttpTask<Integer>(connInfo,process);
        
        mgrManager.addTask(task);
        
        mgrManager.start();
        
        
    }
}
