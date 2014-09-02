package jlx.tools.research.zhaopin;

import java.util.List;

import jlx.tools.research.vo.CompanyInfo;
import jlx.util.RegexUtils;
import jlx.util.log.DebugLogger;

import org.apache.commons.httpclient.HttpClient;

/**
 * {class description} <br>
 * <p>
 * Create on : 2012-6-30<br>
 * <p>
 * </p>
 * <br>
 * 
 * @author henry.luck@gmail.com<br>
 * @version CompanyResearchTool v1.0
 *          <p>
 *          <br>
 *          <strong>Modify History:</strong><br>
 *          user modify_date modify_content<br>
 *          -------------------------------------------<br>
 *          <br>
 */
public class CorSearcher {
    
    public static void main(final String[] args) {
        String comName = "天津聚龙嘉华投资集团有限公司";
        comName = RegexUtils.getMatchString(comName, "([^(（]*)");
        
        
        System.out.println(delStrFromStart("天津聚龙嘉华投资集团有限公司...","天津市"));
    }

    /**
     * {method description}.
     * 
     * @param needAdd
     */
    public static void search(final List<CompanyInfo> needAdd) {
        WebRoboter robot = new WebRoboter();
        try {
            HttpClient client = robot.login();
            for (CompanyInfo companyInfo : needAdd) {
                search(companyInfo, client);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /**
     * {method description}.
     * 
     * @param com
     * @return 如果有人负责返回false
     */
    public static boolean search(final CompanyInfo com) {
        WebRoboter robot = new WebRoboter();
        try {
            HttpClient client = robot.login();
            return search(com, client);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * {method description}.
     * 
     * @param com
     * @return 如果有人负责返回false
     */
    public static boolean search(final CompanyInfo com, final HttpClient client) {
        long start = System.currentTimeMillis();
        WebRoboter robot = new WebRoboter();
        try {
            String comName = prcessName(com);

            DebugLogger.log("开始搜索公司：" + comName + ",原始名称：" + com.getName());

            String page = robot.search(comName, client);

            String ownerUser = RegexUtils.getMatchString(page, "responseMan=\"([^\"]*)\"");
            String ownerDep = RegexUtils.getMatchString(page, "responseDept=\"([^\"]*)\"");

            ownerUser = ownerUser == null ? "无负责人员" : ownerUser;
            ownerDep = ownerDep == null ? "无部门" : ownerDep;
            // 做字符转换 TJ_\u4E2D\u7EA7_1\u90E8
            ownerDep = loadConvert(ownerDep.toCharArray(), 0, ownerDep.toCharArray().length, new char[0]);
            ownerUser = loadConvert(ownerUser.toCharArray(), 0, ownerUser.toCharArray().length, new char[0]);

            // DebugLogger.log(com.getName() + "负责人员：" + ownerUser);
            // DebugLogger.log(com.getName() + "所属部门：" + ownerDep);

            com.setOwnerUser(ownerUser);
            com.setOwnerDep(ownerDep);

            if (ownerUser == null || ownerUser.indexOf('.') != -1) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DebugLogger.log("搜索公司：" + com.getName() + ",花费时间：" + (System.currentTimeMillis() - start));
        }
        return true;
    }


    /**
     * {method description}.
     * @param com
     * @return
     */
    private static String prcessName(final CompanyInfo com) {
        //做一些字符处理
        String comName = com.getName();
        //全角括号改半角
        comName = comName.replaceAll("（", "(");
        comName = comName.replaceAll("）", ")");
        
        //取括号前面的 ，信誉通力（天津）信息技术有限公司，只取信誉通力
        comName = RegexUtils.getMatchString(comName, "([^(（]*)");
        //删除结尾的...
        comName = delStr2End(comName,"...");
        //删掉有限公司及其后面的部分
        comName = delStr2End(comName,"有限公司");
        //删除开始的“天津”和“天津市”
        comName = delStrFromStart(comName,"天津");
        comName = delStrFromStart(comName,"天津市");
        comName = delStrFromStart(comName,"北京");
        comName = delStrFromStart(comName,"北京市");
        return comName;
    }
    
    /**
     * 删除字符串里面的字符，从出现的位置开始到结尾
     * 如 delStr2End("天津聚龙嘉华投资集团有限公司","有限公司天津分公司")，结果是“天津聚龙嘉华投资集团”
     * @param src
     * @param delStr
     * @return
     */
    private static String delStr2End(final String src,final String delStr){
        int index = src.indexOf(delStr);
        if(index != -1){
            return src.substring(0, index);
        }
        return src;
    }
    private static String delStrFromStart(final String src,final String delStr){
        if(src.startsWith(delStr)){
            return src.substring(delStr.length());
        }
        return src;
    }
    
    private static String loadConvert (final char[] in, int off, final int len, char[] convtBuf) {
        if (convtBuf.length < len) {
            int newLen = len * 2;
            if (newLen < 0) {
            newLen = Integer.MAX_VALUE;
        } 
        convtBuf = new char[newLen];
        }
        char aChar;
        char[] out = convtBuf; 
        int outLen = 0;
        int end = off + len;

        while (off < end) {
            aChar = in[off++];
            if (aChar == '\\') {
                aChar = in[off++];   
                if(aChar == 'u') {
                    // Read the xxxx
                    int value=0;
            for (int i=0; i<4; i++) {
                aChar = in[off++];  
                switch (aChar) {
                  case '0': case '1': case '2': case '3': case '4':
                  case '5': case '6': case '7': case '8': case '9':
                     value = (value << 4) + aChar - '0';
                 break;
              case 'a': case 'b': case 'c':
                          case 'd': case 'e': case 'f':
                 value = (value << 4) + 10 + aChar - 'a';
                 break;
              case 'A': case 'B': case 'C':
                          case 'D': case 'E': case 'F':
                 value = (value << 4) + 10 + aChar - 'A';
                 break;
              default:
                              throw new IllegalArgumentException(
                                           "Malformed \\uxxxx encoding.");
                        }
                     }
                    out[outLen++] = (char)value;
                } else {
                    if (aChar == 't') aChar = '\t'; 
                    else if (aChar == 'r') aChar = '\r';
                    else if (aChar == 'n') aChar = '\n';
                    else if (aChar == 'f') aChar = '\f'; 
                    out[outLen++] = aChar;
                }
            } else {
            out[outLen++] = aChar;
            }
        }
        return new String (out, 0, outLen);
    }


}
