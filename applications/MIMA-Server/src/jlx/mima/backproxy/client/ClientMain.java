package jlx.mima.backproxy.client;

/**
 * {class description}
 * <br>
 *  
 * <p>
 * Create on : 2012-11-30<br>
 * <p>
 * </p>
 * <br>
 * @author jianglinxue<br>
 * @version MIMA-Server v1.0
 * <p>
 *<br>
 * <strong>Modify History:</strong><br>
 * user     modify_date    modify_content<br>
 * -------------------------------------------<br>
 * <br>
 */
public class ClientMain {
    private static int no = 50;
    /**
     * {method description}.
     * @param args
     */
    public static void main(String[] args) {
        String noConfig = System.getProperty("ChannelNo");
        if (noConfig != null) {
            no = Integer.parseInt(noConfig);
        }

        for (int i = 0; i < no; i++) {
            new BackClient().start();
        }
    }

}
