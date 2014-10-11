import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String input = "职务=GM 薪水=50000 , 姓名=职业经理人 ; 性别=男 年龄=45 ";

		String patternStr = "(\\s*,\\s*)|(\\s*;\\s*)|(\\s+)";

		Pattern pattern = Pattern.compile(patternStr);

		String[] dataArr = pattern.split(input);

		for (int i = 0; i < dataArr.length; i++) {
			System.out.println(dataArr[i]);
		}

	}
}
