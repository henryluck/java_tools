package com.jlx.exequeue;

import java.util.LinkedList;

/**
 * 
 */

/**
 * @author jianglx
 * 
 */
public class EventQueue {

	// 实例
	private static EventQueue queue = new EventQueue();

	// 队列
	LinkedList list = new LinkedList();

	/** sync */
	private Object queueLock = new Object();

	/** this is true if there is no worker thread. */
	private boolean destroyed = true;

	/** the thread that works the queue. */
	private Thread processorThread;

	/** default */
	private static final int DEFAULT_WAIT_TO_DIE_MILLIS = 10000;

	/** Number of items in the queue */
	private int size = 0;

	/**
	 * time to wait for an event before snuffing the background thread if the
	 * queue is empty. make configurable later
	 */
	private int waitToDieMillis = DEFAULT_WAIT_TO_DIE_MILLIS;

	private EventQueue() {
	}

	public static EventQueue getInstance() {
		return queue;
	}

	/**
	 * If they queue has an active thread it is considered alive.
	 * <p>
	 * 
	 * @return The alive value
	 */
	public synchronized boolean isAlive() {
		return (!destroyed);
	}

	/**
	 * Sets whether the queue is actively processing -- if there are working
	 * threads.
	 * <p>
	 * 
	 * @param aState
	 */
	public synchronized void setAlive(boolean aState) {
		destroyed = !aState;
	}

	/**
	 * Event Q is emtpy.
	 * <p>
	 * Calling destroy interupts the processor thread.
	 */
	public synchronized void destroy() {
		if (!destroyed) {
			destroyed = true;

			// sychronize on queue so the thread will not wait forever,
			// and then interrupt the QueueProcessor

			if (processorThread != null) {
				synchronized (queueLock) {
					processorThread.interrupt();
				}
			}
			processorThread = null;

			System.out.println("Cache event queue destroyed: " + this);
		} else {
			System.out
					.println("Destroy was called after queue was destroyed.  Doing nothing. ");

		}
	}

	/**
	 * 放对象进去
	 * 
	 * @param str
	 */
	public void put(Object str) {
		synchronized (queueLock) {
			list.addLast(str);
			synchronized (queueLock) {
				size++;
				if (!isAlive()) {
					System.out.println("需要启动队列处理器");
					destroyed = false;
					processorThread = new QProcessor(this);
					processorThread.start();
					System.out.println("Cache event queue created: " + this);
				} else {
					System.out.println("队列处理器已经运行，尝试激活等待的线程");
					// 尝试激活等待在queueLock处的线程
					queueLock.notify();
				}
			}
		}
	}

	/**
	 * 取对象
	 * 
	 * @param str
	 */
	public Object take() {
		synchronized (queueLock) {
			Object obj = null;
			if (list.size() > 0) {
				obj = list.getFirst();
				list.removeFirst();
			}
			return obj;
		}
	}

	/**
	 * Returns the time to wait for events before killing the background thread.
	 * 
	 * @return int
	 */
	public int getWaitToDieMillis() {
		return waitToDieMillis;
	}

	/**
	 * Kill the processor thread and indicate that the queue is detroyed and no
	 * longer alive, but it can still be working.
	 */
	public synchronized void stopProcessing() {
		destroyed = true;
		processorThread = null;
	}

	private class QProcessor extends Thread {
		/** The queue to work */
		EventQueue queue;

		/**
		 * Constructor for the QProcessor object
		 * <p>
		 * 
		 * @param aQueue
		 *            the event queue to take items from.
		 */
		QProcessor(EventQueue aQueue) {
			super("EventQueue.QProcessor");
			// 设置
			setDaemon(true);
			queue = aQueue;
		}

		/**
		 * Main processing method for the QProcessor object.
		 * <p>
		 * Waits for a specified time (waitToDieMillis) for something to come in
		 * and if no new events come in during that period the run method can
		 * exit and the thread is dereferenced.
		 */
		public void run() {
			Object obj = null;
			System.out.println("队列处理器启动...");
			while (queue.isAlive()) {
				obj = queue.take();

				if (obj == null) {
					System.out.println("队列为空了，等待一会");
					synchronized (queueLock) {
						try {
							queueLock.wait(queue.getWaitToDieMillis());
						} catch (InterruptedException e) {
							System.out
									.println("Interrupted while waiting for another event to come in before we die.");
							return;
						}
						obj = queue.take();
					}
					if (obj == null) {
						System.out.println("队列为空，停止处理线程");
						queue.stopProcessing();
					}
				}

				if (queue.isAlive() && obj != null) {
					// 串行操作
					System.out.println("当前处理的队列中的对象：" + obj);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					/**
					 * 这个地方如果是并行的话，可以考虑用注释的方法
					 **/
					// ((Runnable) obj).run();
				}

			}
			System.out.println("队列处理器退出： " + queue);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue queue = EventQueue.getInstance();

		try {
			for (int i = 0; i < 5; i++) {
				queue.put(new Integer(i));
			}
			// 等待一会，在队列里面放
			Thread.sleep(10000);

			for (int i = 0; i < 5; i++) {
				queue.put(new Integer(i));
			}
			// 等待一会，在队列里面放
			Thread.sleep(20000);

			for (int i = 0; i < 5; i++) {
				queue.put(new Integer(i));
			}
			// 等待子线程结束
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
