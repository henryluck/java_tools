package org.zeroturnaround.jrebel.mybatis;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.security.AccessController;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.executor.ErrorContext;
import org.zeroturnaround.javarebel.ConfigurationFactory;
import org.zeroturnaround.javarebel.Logger;
import org.zeroturnaround.javarebel.LoggerFactory;
import org.zeroturnaround.javarebel.integration.monitor.MonitoredResource;
import org.zeroturnaround.javarebel.integration.util.MonitorUtil;
import org.zeroturnaround.javarebel.integration.util.ResourceUtil;

import sun.security.action.GetPropertyAction;

public class SqlMapReloader {
    private static final Logger log = LoggerFactory.getLogger("MyBatis");
    private static final String MONITOR_KEY = String.valueOf(System.identityHashCode(SqlMapReloader.class));
    private static final int CHECK_INTERVAL = ConfigurationFactory.getInstance().getCheckInterval();

    private final Map<URL, MonitoredResource> monitoredFiles = Collections.synchronizedMap(new LinkedHashMap());
    private final List<ResourceDesc> additionalMappings = Collections.synchronizedList(new ArrayList(1));
    private static final ThreadLocal<Set<String>> reloadedResources = new ThreadLocal();
    private final JrConfiguration conf;
    private JrXMLConfigBuilder confBuilder;
    private volatile long lastCheck;
    

    public SqlMapReloader(final JrConfiguration conf) {
        this.conf = conf;
        this.lastCheck = System.currentTimeMillis();
    }

    public void setConfigBuilder(final JrXMLConfigBuilder confBuilder) {
        this.confBuilder = confBuilder;
    }

    public void reload() {
        System.out.println("SqlMapReloader.reload**********************************************************");
        synchronized (this) {
//            if (this.lastCheck + CHECK_INTERVAL < System.currentTimeMillis()) {
                if (check()){//hasChanged()) {
                    reconfigure();
                }
//                this.lastCheck = System.currentTimeMillis();
//            }
        }
    }

    public static boolean isReloading() {
        return MonitorUtil.isActive(MONITOR_KEY);
    }

    public static boolean doReload(final String s) {
        if (isReloading()) {
            Set set = reloadedResources.get();
            if ((set != null) && (!set.contains(s))) {
                set.add(s);
                return true;
            }
        }
        return false;
    }

    private void reconfigure() {
        log.infoEcho("Reloading SQL maps");

        this.conf.reinit();

        reloadedResources.set(Collections.synchronizedSet(new HashSet()));
        MonitorUtil.enter(MONITOR_KEY);
        try {
            if (this.confBuilder != null)
                this.confBuilder.reinit();
            reconfigureAdditionalMappings();

            MonitorUtil.exit(MONITOR_KEY);
            reloadedResources.remove();
        } finally {
            MonitorUtil.exit(MONITOR_KEY);
            reloadedResources.remove();
        }
    }

    private void reconfigureAdditionalMappings() {
        for (ResourceDesc rd : this.additionalMappings.toArray(new ResourceDesc[0]))
            reconfigureMapping(rd);
    }

    private void reconfigureMapping(final ResourceDesc rd) {
        org.apache.ibatis.session.Configuration c = (org.apache.ibatis.session.Configuration) this.conf;
        try {
            XMLMapperBuilder xmlMapperBuilder =
                new XMLMapperBuilder(ResourceUtil.asInputStream(rd.url), c, rd.path, c.getSqlFragments());

            xmlMapperBuilder.parse();
        } catch (Exception e) {
            if ((e.getCause() instanceof FileNotFoundException))
                removeMappingForDeletedResource(rd);
            else throw new RuntimeException("Failed to parse mapping resource: '" + rd.url + "'", e);
        } finally {
            ErrorContext.instance().reset();
        }
    }

    private void removeMappingForDeletedResource(final ResourceDesc rd) {
        log.info("Stopped monitoring resource " + rd.url);
        this.monitoredFiles.remove(rd.url);
        this.additionalMappings.remove(rd);
    }

    private boolean hasChanged() {
        boolean hasChanged = false;
        Collection<MonitoredResource> files =
            Arrays.asList(this.monitoredFiles.values().toArray(new MonitoredResource[0]));

        for (MonitoredResource file : files) {
            hasChanged |= file.modified();
        }

        return hasChanged;
    }

    public void enterConf() {
        ResourceContext.enter();
    }

    public void exitConf() {
        Collection<URL> resources = ResourceContext.exit();
        for (URL url : resources)
            if (!this.monitoredFiles.containsKey(url)) {
                log.info("Monitoring resource " + url);

                MonitoredResource file = new MonitoredResource(ResourceUtil.asResource(url));
                this.monitoredFiles.put(url, file);
            }
    }

    public void setMappers(final Map<String, File> t_mappers) {
        try {
            for (Iterator<Map.Entry<String, File>> t_itr = t_mappers.entrySet().iterator(); t_itr.hasNext();) {
                Map.Entry<String, File> t_ent = t_itr.next();
                String path = t_ent.getKey();
                URL url = new URL(path);
                this.addMapping(url, path);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void addMapping(final URL url, final String path) {
        if (this.monitoredFiles.containsKey(url)) {
            return;
        }
        MonitoredResource file = new MonitoredResource(ResourceUtil.asResource(url));
        log.info("Monitoring resource '" + url + "' from '" + path + "'");
        this.monitoredFiles.put(url, file);
        this.additionalMappings.add(new ResourceDesc(url, path));
    }

    public boolean mappingsLoadedFromSameLocation() {
        if (this.additionalMappings.isEmpty())
            return false;

        boolean result = true;
        String firstMappingBaseUrl = this.additionalMappings.get(0).getBaseUrl();
        for (ResourceDesc rd : this.additionalMappings) {
            result &= rd.getBaseUrl().equalsIgnoreCase(firstMappingBaseUrl);
        }

        return result;
    }

    public String buildUrlBasedOnFirstMapping(final Class<?> type) {
        if (this.additionalMappings.isEmpty())
            return "";

        return this.additionalMappings.get(0).getBaseUrl() + type.getSimpleName() + ".xml";
    }

    public String buildPathBasedOnFirstMapping(final Class<?> type) {
        if (this.additionalMappings.isEmpty())
            return "";

        String firstMappingPath = this.additionalMappings.get(0).path;
        int extensionIndex = firstMappingPath.lastIndexOf(".xml");

        return this.additionalMappings.get(0).getBasePath() + type.getSimpleName()
            + firstMappingPath.substring(extensionIndex);
    }

    private static class ResourceDesc {
        final URL url;
        final String path;

        ResourceDesc(final URL url, final String path) {
            this.url = url;
            this.path = path;
        }

        String getBaseUrl() {
            String url = this.url.toString();
            int lastSeparatorIndex = url.lastIndexOf('/');
            return url.substring(0, lastSeparatorIndex) + "/";
        }

        String getBasePath() {
            String separator = "/";
            int lastSeparatorIndex = this.path.lastIndexOf(separator);

            if (lastSeparatorIndex < 0) {
                separator = "\\";
                lastSeparatorIndex = this.path.lastIndexOf(separator);
            }

            return this.path.substring(0, lastSeparatorIndex) + separator;
        }
    }
    

    private static final Map sqlMapXmlFilesLastModified = new HashMap();
    private static final Map sqlMapXmlFiles = new HashMap();
    private static String CODING = AccessController.doPrivileged(new GetPropertyAction("file.encoding"));
    private static ClassLoader cloader;
    
    public static void registerXml(final ClassLoader cl) {
        cloader = cl;
        System.out.println("dam plugin registerXml()****************************");
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
        System.out.println("DAM sqlmap文件数量："+sqlMapXmlFiles.entrySet().size());
    }
    

    public static boolean check() {
        boolean flag = false;
        try {
            //cloader是空的时候，damFactory的init方法还没有执行，没有必要check，直接返回false
            if(cloader == null){
                return false;
            }
            Enumeration enumeration = cloader.getResources("entities");
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
}
