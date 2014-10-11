package com.mdcl.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 该类负责读取系统的配置文件。
 * 配置文件的名字为SSOConfig.properties，放置在系统的类路径下。SystemProperties
 * 负责将配置信息从文件中读出来，提供系统获得配置信息。
 * @author liujiang
 * @version 1.0
 */
public class SystemProperties {
    private static Properties coreProp;

    static {

        File f = null;
        InputStream fis = null;
        try {
            try {
                coreProp = new Properties();

                String propfile = "SSOConfig.properties";
                fis = getResourceAsStream(propfile);
                coreProp.load(fis);
                fis.close();

            }
            catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        finally {
            if (fis != null)
                try {
                    fis.close();
                }
                catch (Exception e) {
                    System.err.println(e.getMessage());
                }
        }
    }


    public SystemProperties() {
    }

    /**
     * 得到配置属性的值
     * @param key 配置属性的名称
     * @return 配置属性的值，如果配置属性没有设置值，则返回null
     */
    public static String get(String key) {
        return coreProp.getProperty(key, null);
    }
    /**
     * 得到配置属性的值
     * @param key 配置属性的名称
     * @param defaultvalue 默认值
     * @return 配置属性的值
     */
    public static String get(String key, String defaultvalue) {
        return coreProp.getProperty(key, defaultvalue);
    }

    /**
     * 得到全部的属性配置信息
     * @return 返回通过配置文件生成的properties。
     */
    public static Properties getAll() {
        return coreProp;
    }

    /**
     * 从资源文件中得到文件的输入流
     * @param resource 资源文件的名称，这个文件应该存在classpath下
     * @return 文件的输入流
     * @throws IOException 如果读取文件错误，则抛出该异常。
     */
    public static InputStream getResourceAsStream(String resource) throws
        IOException {
        InputStream in = SystemProperties.class.getClassLoader().
            getResourceAsStream(resource);
        if (in == null)
            in = ClassLoader.getSystemResourceAsStream(resource);
        if (in == null)
            throw new IOException("Could not find resource " + resource);
        return in;
    }


}
