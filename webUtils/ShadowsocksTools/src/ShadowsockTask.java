import java.util.Date;
import java.util.TimerTask;

public class ShadowsockTask extends TimerTask {
	private Worker worker = new Worker();

	@Override
	public void run() {
		try {
			System.out.println(new Date() + " start---");
			worker.doWork();
			System.out.println(new Date() + " end---");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
