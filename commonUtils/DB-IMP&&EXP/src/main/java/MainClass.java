import kaka.DataMaker;

/**
 * 
 */

/**
 * @author jianglx
 * 
 */
public class MainClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		
		DataMaker dm = new DataMaker();

		if (args == null || args.length == 0) {
			System.out
					.println("Useage : java [-DisDebug=true -DisDel=true] MainClass [import] [export]");
			return;
		}

		if ("export".equals(args[0])) {
			dm.readTableData2Disk();
		} else if ("import".equals(args[0])) {
			dm.writeTableData2DB();
		} else {
			System.out.println("unknown perameter");
			return;
		}
		System.out.println("All done!");
	}
}
