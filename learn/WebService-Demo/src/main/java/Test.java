/**
 * <strong>Title : Test<br>
 * </strong> <strong>Description : </strong><br>
 * <strong>Create on : 2008-3-3<br>
 * </strong>
 * <p>
 * <strong>Copyright (C) Mocha Software Co.,Ltd.<br>
 * </strong>
 * <p>
 * 
 * @author jianglx jianglx@mochasoft.com.cn<br>
 * @version <strong>Mocha BPM v6.4.0</strong><br>
 *          <br>
 *          <strong>修改历史:</strong><br>
 *          修改人 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */
public class Test {

	/**
	 * 方法描述
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String s = "abcd";
		String t = "1234";
		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			if(s.intern()==t.intern()){
				;
			}
		}
		System.out.println("用时："+(System.currentTimeMillis()-start));
	}

}
