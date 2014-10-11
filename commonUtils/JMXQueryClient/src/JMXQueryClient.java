import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.management.MBeanAttributeInfo;
import javax.management.MBeanInfo;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanServerConnection;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

/**
 * {class description} <br>
 * <p>
 * Create on : 2012-8-17<br>
 * <p>
 * </p>
 * <br>
 * 
 * @author jianglinxue@ruijie.com.cn<br>
 * @version test v1.0
 *          <p>
 *          <br>
 *          <strong>Modify History:</strong><br>
 *          user modify_date modify_content<br>
 *          -------------------------------------------<br>
 *          <br>
 */
public class JMXQueryClient {

    private static String url = "service:jmx:rmi:///jndi/rmi://172.17.160.177:3355/management/rmi-jmx-connector";
    private static String username = "kpsunadmin";
    private static String password = "password";
    private static String queryString = "jdbc";
    private static Map<String,ObjectName> resultMap = new HashMap<String,ObjectName>();

    /**
     * {method description}.
     * 
     * @param args
     * @throws MalformedURLException
     */
    public static void main(String[] args) throws Exception {
        JMXConnector t_connector = null;
        try {
            JMXServiceURL t_url = new JMXServiceURL(url);

            Map<String, String[]> t_env = new HashMap<String, String[]>();
            String[] t_creds = { username, password };
            t_env.put(JMXConnector.CREDENTIALS, t_creds);

            t_connector = JMXConnectorFactory.connect(t_url, t_env);
            MBeanServerConnection t_mbsc = t_connector.getMBeanServerConnection();

            String domains[] = t_mbsc.getDomains();
            System.out.println("# of domains=" + domains.length);
//            for (int i = 0; i < domains.length; i++) {
//                System.out.println("Domain[" + i + "]=" + domains[i]);
//            }

            Integer MBeansCount = t_mbsc.getMBeanCount();
//            System.out.println("MBeansCount : " + MBeansCount.intValue());

            Set MBeanset = t_mbsc.queryMBeans(null, null);
            System.out.println("MBeanset.size() : " + MBeanset.size());
            Iterator MBeansetIterator = MBeanset.iterator();
            while (MBeansetIterator.hasNext()) {
                ObjectInstance objectInstance = (ObjectInstance) MBeansetIterator.next();
                ObjectName objectName = objectInstance.getObjectName();

                String canonicalName = objectName.getCanonicalName();
                String canonicalKeyPropList = objectName.getCanonicalKeyPropertyListString();
                String keyPropertyListString = objectName.getKeyPropertyListString();

                // System.out.println("canonicalKeyPropList:" + canonicalKeyPropList);
                // System.out.println("canonicalName : " + canonicalName);
                // System.out.println("keyPropertyListString:" + keyPropertyListString);

                if (checkString(canonicalName, canonicalName,objectName)) {
                    continue;
                }

                MBeanInfo mbInfo = t_mbsc.getMBeanInfo(objectName);
                MBeanOperationInfo[] mbOperationInfos = mbInfo.getOperations();
                for (MBeanOperationInfo mbOperationInfo : mbOperationInfos) {
                    String name = mbOperationInfo.getName();
                    if (checkString(name, canonicalName,objectName)) {
                        continue;
                    }
                }

                MBeanAttributeInfo[] mbAttributes = mbInfo.getAttributes();

                for (MBeanAttributeInfo mBeanAttributeInfo : mbAttributes) {
                    String name = mBeanAttributeInfo.getName();
                    if (checkString(name, canonicalName,objectName)) {
                        continue;
                    }
                }

                // System.out.println("currentThreadCount:" + t_mbsc.getAttribute(threadObjName, attrName));

            }
            // 打印结果
            for (Iterator<Entry<String, ObjectName>> iterator = resultMap.entrySet().iterator(); iterator.hasNext();) {
                Entry<String, ObjectName> entry = iterator.next();
                ObjectName objectName = entry.getValue();
                
                System.out.println(objectName.getCanonicalName());
                
                MBeanInfo mbInfo = t_mbsc.getMBeanInfo(objectName);
                MBeanAttributeInfo[] mbAttributes = mbInfo.getAttributes();
                System.out.println("\t属性名称列表：");
                for (MBeanAttributeInfo mBeanAttributeInfo : mbAttributes) {
                    String name = mBeanAttributeInfo.getName();
                    System.out.println("\t\t" + name + " = " + t_mbsc.getAttribute(objectName, name));
                }
                
                System.out.println("\t方法名称列表：");
                
                MBeanOperationInfo[] mbOperationInfos = mbInfo.getOperations();
                for (MBeanOperationInfo mbOperationInfo : mbOperationInfos) {
                    String name = mbOperationInfo.getName();
                    System.out.println("\t\t" + name);
                }
                
            }

            // ObjectName t_serverVersion = new ObjectName("amx:j2eeType=J2EEServer,name=" + this.m_instanceName);
            // Object t_sv = t_mbsc.getAttribute(t_serverVersion, "serverVersion");
            // return t_sv;
        } catch (Throwable t_e) {
            t_e.printStackTrace();
        } finally {
            try {
                t_connector.close();
            } catch (IOException t_e) {
                t_e.printStackTrace();
            }
        }

    }

    /**
     * {method description}.
     * 
     * @param name
     * @param queryString
     */
    private static boolean checkString(String name, String objName,ObjectName obj) {
        if (name.indexOf(queryString) != -1) {
            resultMap.put(objName, obj);
            return true;
        }
        return false;
    }
}
