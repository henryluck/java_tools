package com.jrebel.plugin.dam;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.security.AccessController;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import sun.security.action.GetPropertyAction;

import com.riil.core.dam.ibatis.DAMFactory;

public class SqlMapXmlFilesManager {

    private static String CODING = AccessController.doPrivileged(new GetPropertyAction("file.encoding"));

    private static final Map sqlMapXmlFilesLastModified = new HashMap();
    private static final Map sqlMapXmlFiles = new HashMap();
    private static ClassLoader cloader;
    public static DAMFactory dam;
    public static JrConfiguration config;

    public static void registerXml(final ClassLoader cl) {
        cloader = cl;
        Debuger.log("dam plugin registerXml()");
        try {
            Enumeration enumeration = cl.getResources("entities");
            if (enumeration != null) {
                while (enumeration.hasMoreElements()) {
                    URL url = (URL) enumeration.nextElement();
                    String filename = URLDecoder.decode(url.getFile(), CODING);
                    File file = new File(filename);
                    if (file.isDirectory()) {
                        File[] xmlFiles = file.listFiles(new MyFileFilter());
                        for (int i = 0; i < xmlFiles.length; i++) {
                            sqlMapXmlFiles.put(xmlFiles[i].getName(), xmlFiles[i]);
                            sqlMapXmlFilesLastModified.put(xmlFiles[i].getName(), new Long(xmlFiles[i].lastModified()));
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Debuger.log("DAM sqlmap文件数量："+sqlMapXmlFiles.entrySet().size());
    }

    public static boolean check(final ClassLoader cl) {
        boolean flag = false;
        try {
            //cloader是空的时候，damFactory的init方法还没有执行，没有必要check，直接返回false
            if(cloader == null){
                return false;
            }
            Enumeration enumeration = cl.getResources("entities");
            if (enumeration != null)
                while (enumeration.hasMoreElements()) {
                    URL url = (URL) enumeration.nextElement();
                    String filename = URLDecoder.decode(url.getFile(), CODING);

                    File file = new File(filename);
                    if (file.isDirectory()) {
                        File[] xmlFiles = file.listFiles(new MyFileFilter());
                        for (int i = 0; i < xmlFiles.length; i++) {
                            if (sqlMapXmlFiles.get(xmlFiles[i].getName()) == null) {
                                sqlMapXmlFiles.put(xmlFiles[i].getName(), new File(xmlFiles[i].getName()));
                                flag = true;
                            }
                            long lastModified =
                                ((Long) sqlMapXmlFilesLastModified.get(xmlFiles[i].getName())).longValue();

                            if (xmlFiles[i].lastModified() > lastModified) {
                                sqlMapXmlFilesLastModified.put(xmlFiles[i].getName(),
                                    new Long(xmlFiles[i].lastModified()));
                                flag = true;
                            }
                        }
                    }
                }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return flag;
    }
    
    public static void reload() {
        
        //重新加载
        if(dam == null){
            Debuger.log("dam is null !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
        if(dam !=null){
            try {
                Debuger.log("dam start reload>>>>");
                config.reInitConfig();
                dam.initialize();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
