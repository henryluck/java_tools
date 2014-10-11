/**
 * 
 */
package org.jlx.tools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author jianglx
 * 
 */
public class ThreadPoolService {
	private static int POOL_SIZE = 12;// 线程池大小
	// 线程池
	private static ExecutorService executorService = Executors
			.newFixedThreadPool(POOL_SIZE);;

	private ThreadPoolService() {
	}

	public static ExecutorService getExecutorInstance(){
		return executorService;
	}

	public ExecutorService getExecutorService() {
		return executorService;
	}

	public int getPOOL_SIZE() {
		return POOL_SIZE;
	}

	public void setPOOL_SIZE(int POOL_SIZE) {
		ThreadPoolService.POOL_SIZE = POOL_SIZE;
	}

}
