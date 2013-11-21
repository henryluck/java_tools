package jlx.tools.research.utils;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * {class description} <br>
 * <p>
 * Create on : 2012-6-2<br>
 * <p>
 * </p>
 * <br>
 * 
 * @version CompanyResearchTool v1.0
 *          <p>
 *          <br>
 *          <strong>Modify History:</strong><br>
 *          user modify_date modify_content<br>
 *          -------------------------------------------<br>
 *          <br>
 */
public class ConfigUtil {
    private static ResourceBundle rbInfo = ResourceBundle.getBundle("config");
    
    // private static Properties rbInfo = new Properties();
    //
    // static {
    // properties.load(ConfigUtil.class.getClassLoader().getResourceAsStream("config.properties"));
    // }
    
    /**
     * {method description}.
     * 
     * @param key
     * @return
     */
    public static String getProperty(String key) {
        try {
            return rbInfo.getString(key);
        } catch (MissingResourceException e) {
            return null;
        }

    }

    /**
     * {method description}.
     * 
     * @param string
     */
    public static String getDefaultURLByKey(String key) {
        return ConfigUtil.getProperty(key + ".defaultURL");

    }

    /**
     * {method description}.
     * 
     * @param key
     * @return
     */
    public static String getComInfoHtmlListByKey(String key) {
        return ConfigUtil.getProperty(key + ".ComInfoHtmlListPattern");
    }

    /**
     * {method description}.
     * 
     * @param key
     * @return
     */
    public static String getParserByKey(String key) {
        return ConfigUtil.getProperty(key + ".parser");
    }

    /**
     * {method description}.
     * 
     * @param key
     * @return
     */
    public static String getRegexNamePatternByKey(String key) {
        return ConfigUtil.getProperty(key + ".regexNamePattern");
    }

    /**
     * {method description}.
     * 
     * @param key
     * @return
     */
    public static String getRegexURLPatternByKey(String key) {
        return ConfigUtil.getProperty(key + ".regexURLPattern");
    }
    
    /**
     * {method description}.
     * 
     * @param key
     * @return
     */
    public static String getAnchorNamePatternWhenNullByKey(String key) {
        return ConfigUtil.getProperty(key + ".AnchorNamePatternWhenNull");
    }
}
