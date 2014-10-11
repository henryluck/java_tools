package jlx.mima.backproxy.server;

import java.io.IOException;

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
public class ServerMain {

    /**
     * {method description}.
     * 
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        new InnerServer().start();
        // 启动外部服务器
        OuterServer.getInstance().start();

    }

}
