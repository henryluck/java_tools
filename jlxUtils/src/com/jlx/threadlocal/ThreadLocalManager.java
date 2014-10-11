package com.jlx.threadlocal;

/**
 * 
 */

/**
 * @author jianglx
 * 
 */
public class ThreadLocalManager {

	/** <code>S_INSTANCE</code>-该类的唯一一个实例. */
	private static final ThreadLocalManager S_INSTANCE = new ThreadLocalManager();

	/** <code>THREAD_LOCAL_DATA</code>-Data的ThreadLocal. */
	private static final ThreadLocal THREAD_LOCAL_DATA = new ThreadLocal();

	/**
	 * 构造函数
	 */
	private ThreadLocalManager() {
		super();
	}

	/**
	 * 获取该类的唯一一个实例的方法
	 * 
	 * @return <code>ThreadLocalManager</code>
	 */
	public static ThreadLocalManager getInstance() {

		return S_INSTANCE;

	}

	/**
	 * 获取当前的数据对象的方法. <BR>
	 * 
	 * @return <code>data</code>
	 */
	public synchronized Object getCurrentData() {

		Object data = THREAD_LOCAL_DATA.get();

		return data;
	}

	/**
	 * 设置Data的方法. <BR>
	 * 必须传入Data.
	 * 
	 * @param Data
	 */
	public synchronized void setCurrentData(Object data) {

		THREAD_LOCAL_DATA.set(data);

	}

	/**
	 * 清除当前线程的Data引用
	 */
	public synchronized void clearCurrenData() {

		THREAD_LOCAL_DATA.set(null);

	}

	public static void main(String[] args) {
		ThreadLocalManager.getInstance().setCurrentData("hahaha");

		System.out.println(ThreadLocalManager.getInstance().getCurrentData());
	}
} // END CLASS OF ThreadLocalManager