package moe.neptunenoire.web;

/**
 * 这是一个后台线程的处理程序
 */
public class ThreadRun implements Runnable {

	/** 线程的停止时间（睡眠时间） */
	private static Long time = 1000L;

	/**
	 * @param time the time to set
	 */
	public static void setTime(Long time) {
		ThreadRun.time = time;
	}
	/**
	 * @return the time
	 */
	public static Long getTime() {
		return time;
	}



	/** 默默的进行一些后台的处理 */
	@Override
	public void run() {
		try {
			while (true) {
				Thread.sleep(time);
			}
		} catch (InterruptedException e) {

		}
	}

}
