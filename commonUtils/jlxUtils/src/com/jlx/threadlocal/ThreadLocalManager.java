package com.jlx.threadlocal;

/**
 * 
 */

/**
 * @author jianglx
 * 
 */
public class ThreadLocalManager {

	/** <code>S_INSTANCE</code>-�����Ψһһ��ʵ��. */
	private static final ThreadLocalManager S_INSTANCE = new ThreadLocalManager();

	/** <code>THREAD_LOCAL_DATA</code>-Data��ThreadLocal. */
	private static final ThreadLocal THREAD_LOCAL_DATA = new ThreadLocal();

	/**
	 * ���캯��
	 */
	private ThreadLocalManager() {
		super();
	}

	/**
	 * ��ȡ�����Ψһһ��ʵ���ķ���
	 * 
	 * @return <code>ThreadLocalManager</code>
	 */
	public static ThreadLocalManager getInstance() {

		return S_INSTANCE;

	}

	/**
	 * ��ȡ��ǰ�����ݶ���ķ���. <BR>
	 * 
	 * @return <code>data</code>
	 */
	public synchronized Object getCurrentData() {

		Object data = THREAD_LOCAL_DATA.get();

		return data;
	}

	/**
	 * ����Data�ķ���. <BR>
	 * ���봫��Data.
	 * 
	 * @param Data
	 */
	public synchronized void setCurrentData(Object data) {

		THREAD_LOCAL_DATA.set(data);

	}

	/**
	 * �����ǰ�̵߳�Data����
	 */
	public synchronized void clearCurrenData() {

		THREAD_LOCAL_DATA.set(null);

	}

	public static void main(String[] args) {
		ThreadLocalManager.getInstance().setCurrentData("hahaha");

		System.out.println(ThreadLocalManager.getInstance().getCurrentData());
	}
} // END CLASS OF ThreadLocalManager