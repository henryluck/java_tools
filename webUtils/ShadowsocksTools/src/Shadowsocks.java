import java.util.Timer;

public class Shadowsocks {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Timer timer = new Timer();
		long p = Long.valueOf(System.getProperty("period", "30000")).longValue();
		System.out.println("运行周期：" + p + "ms，可以通过-Dperiod参数修改。");
		timer.schedule(new ShadowsockTask(), 1000, p);
	}

}
