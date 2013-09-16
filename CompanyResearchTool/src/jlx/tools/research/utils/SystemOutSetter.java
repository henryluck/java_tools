package jlx.tools.research.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

import jlx.tools.research.Main;

/**
 * {class description}
 * <br>
 *  
 * <p>
 * Create on : 2012-6-30<br>
 * <p>
 * </p>
 * <br>
 * @author henry.luck@gmail.com<br>
 * @version CompanyResearchTool v1.0
 * <p>
 *<br>
 * <strong>Modify History:</strong><br>
 * user     modify_date    modify_content<br>
 * -------------------------------------------<br>
 * <br>
 */
public class SystemOutSetter {
    public static String getRealPath() {
        String realPath = Main.class.getClassLoader().getResource("").getFile();
        java.io.File file = new java.io.File(realPath);
        realPath = file.getAbsolutePath();
        try {
            realPath = java.net.URLDecoder.decode(realPath, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return realPath;
    }
    public static void setSystOut() {
        String useFileString = System.getProperty("useLogFile");
        if(useFileString == null){
            setSystOut("log.log");
        }
    }
    public static void setSystOut(String logFile) {
        PrintStream myout;
        try {
            DebugLogger.log(getRealPath());
            myout = new PrintStream(new FileOutputStream(new File(getRealPath() + "/" + logFile)), true,
                    "GBK");
            System.setOut(myout);    
            System.setErr(myout);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
