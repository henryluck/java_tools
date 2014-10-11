package com.mdcl.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * ���ฺ���ȡϵͳ�������ļ���
 * �����ļ�������ΪSSOConfig.properties��������ϵͳ����·���¡�SystemProperties
 * ����������Ϣ���ļ��ж��������ṩϵͳ���������Ϣ��
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
     * �õ��������Ե�ֵ
     * @param key �������Ե�����
     * @return �������Ե�ֵ�������������û������ֵ���򷵻�null
     */
    public static String get(String key) {
        return coreProp.getProperty(key, null);
    }
    /**
     * �õ��������Ե�ֵ
     * @param key �������Ե�����
     * @param defaultvalue Ĭ��ֵ
     * @return �������Ե�ֵ
     */
    public static String get(String key, String defaultvalue) {
        return coreProp.getProperty(key, defaultvalue);
    }

    /**
     * �õ�ȫ��������������Ϣ
     * @return ����ͨ�������ļ����ɵ�properties��
     */
    public static Properties getAll() {
        return coreProp;
    }

    /**
     * ����Դ�ļ��еõ��ļ���������
     * @param resource ��Դ�ļ������ƣ�����ļ�Ӧ�ô���classpath��
     * @return �ļ���������
     * @throws IOException �����ȡ�ļ��������׳����쳣��
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
