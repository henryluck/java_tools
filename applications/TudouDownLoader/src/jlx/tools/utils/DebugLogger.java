package jlx.tools.utils;

import javax.swing.JTextArea;

/**
 * {class description} <br>
 * <p>
 * Create on : 2012-7-1<br>
 * <p>
 * </p>
 * <br>
 * 
 * @author jianglinxue@ruijie.com.cn<br>
 * @version CompanyResearchTool v1.0
 *          <p>
 *          <br>
 *          <strong>Modify History:</strong><br>
 *          user modify_date modify_content<br>
 *          -------------------------------------------<br>
 *          <br>
 */
public class DebugLogger {
    private static JTextArea TXT_AREA;

    /**
     * @param tXT_AREA - {parameter description}.
     */
    public static void setTXT_AREA(JTextArea txtArea) {
        TXT_AREA = txtArea;
    }

    public static void log(String log) {
        TXT_AREA.setText(TXT_AREA.getText() + log + "\n");
    }

    public static void log(int log) {
        TXT_AREA.setText(TXT_AREA.getText() + log + "\n");
    }
}
