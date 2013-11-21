package jlx.tools.research.utils;

/**
 * {class description}
 * <br>
 *  
 * <p>
 * Create on : 2012-9-6<br>
 * <p>
 * </p>
 * <br>
 * @version CompanyResearchTool v1.0
 * <p>
 *<br>
 * <strong>Modify History:</strong><br>
 * user     modify_date    modify_content<br>
 * -------------------------------------------<br>
 * <br>
 */
public class FormatUtil {
    //显示对齐，公司名称补充空格
    public static String addBlank(String name){
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
