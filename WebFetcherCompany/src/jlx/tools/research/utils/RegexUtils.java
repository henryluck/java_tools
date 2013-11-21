package jlx.tools.research.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * {class description} <br>
 * <p>
 * Create on : 2012-11-7<br>
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
public final class RegexUtils {
    /**
     * Constructors.
     */
    private RegexUtils(){
        
    }

    /**
     * 正则取截取的值
     * 
     * @param input 需要处理的字符串
     * @param regex 正则
     * @return 返回第一个匹配的值
     */
    public static String getMatchString(final String input, final String regex) {
        if(input == null || input.equals("")){
            return null;
        }
        Pattern t_pattern = Pattern.compile(regex,Pattern.DOTALL);
        Matcher t_matcher = t_pattern.matcher(input);
        if (t_matcher.find()) {
            return t_matcher.group(1);
        }
        return null;
    }
    /**
     * 正则取截取的值
     * 
     * @param input 需要处理的字符串
     * @param regex 正则
     * @return 返回第一个匹配的值
     */
    public static List<String> getMatchList(final String input, final String regex) {
        if(input == null || input.equals("")){
            return null;
        }
        List<String> result = new ArrayList<String>(); 
        Pattern t_pattern = Pattern.compile(regex,Pattern.DOTALL);
        Matcher t_matcher = t_pattern.matcher(input);
        while (t_matcher.find()) {
            result.add(t_matcher.group(1));
        }
        return result;
    }

    /**
     * 正则取截取的值
     * 
     * @param input 需要处理的字符串
     * @param regex 正则
     * @return 返回匹配map
     */
    public static Map<String, String> getMatchMap(final String input, final String regex) {
        Pattern t_pattern = Pattern.compile(regex,Pattern.DOTALL);
        Matcher t_matcher = t_pattern.matcher(input);
        Map<String, String> t_map = new HashMap<String, String>();
        while (t_matcher.find()) {
            t_map.put(t_matcher.group(1), t_matcher.group(2));
        }
        return t_map;
    }

    /**
     * {method description}.
     * @param input String
     * @param header String
     * @param content String
     * @return input
     */
    public static List<Map<String, String>> getMatchMapList(final String input, final String header,
            final String content) {
        List<Map<String, String>> t_result = new ArrayList<Map<String, String>>();
        String t_valuePattern = "\\s*\\[?(-?\\w+)\\]?\\s*";
        Pattern t_pattern = Pattern.compile(header,Pattern.UNIX_LINES);
        Matcher t_matcher = t_pattern.matcher(input);
        //处理头
        List<String> t_keys = new ArrayList<String>();
        if (t_matcher.find()) {
            String t_headerLine = t_matcher.group(1);
            Pattern t_h = Pattern.compile(t_valuePattern,Pattern.UNIX_LINES);
            Matcher t_m = t_h.matcher(t_headerLine);
            while (t_m.find()) {
                t_keys.add(t_m.group(1));
            }
        }
        //处理值
        Pattern t_patternContent = Pattern.compile(content,Pattern.UNIX_LINES);
        Matcher t_matcherContent = t_patternContent.matcher(input);
        while (t_matcherContent.find()) {
            String t_contentLine = t_matcherContent.group(1);
            Pattern t_h = Pattern.compile(t_valuePattern,Pattern.UNIX_LINES);
            Matcher t_m = t_h.matcher(t_contentLine);
            Map<String,String> t_map = new HashMap<String,String>();
            int t_i=0;
            while (t_m.find()) {
                t_map.put(t_keys.get(t_i++), t_m.group(1));                
            }
            t_result.add(t_map);
        }
        return t_result;
    }
}
