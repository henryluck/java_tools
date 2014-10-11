package jlx.util.string;

/**
 * {class description}
 * <br>
 *  
 * <p>
 * Create on : 2012-9-6<br>
 */
public class FormatUtil {
    //显示对齐，名称补充空格
    public static String addBlank(final String name){
        int no = 25-name.length();
        if(no<=0){
            return name;
        }
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < no*2; i++) {
            buf.append(" ");
        }
        return name + buf.toString();
    }
}
