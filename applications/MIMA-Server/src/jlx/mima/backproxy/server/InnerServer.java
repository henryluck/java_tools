package jlx.mima.backproxy.server;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

/**
 * 浏览器使用的内部代理服务器 <br>
 * <p>
 * Create on : 2012-11-29<br>
 * <p>
 * </p>
 * <br>
 * 
 * @author jianglinxue<br>
 * @version MIMA-Server v1.0
 *          <p>
 *          <br>
 *          <strong>Modify History:</strong><br>
 *          user modify_date modify_content<br>
 *          -------------------------------------------<br>
 *          <br>
 */
public class InnerServer {
    private static final int PORT = 12311;
    /**
     * Constructors.
     */
    public InnerServer() {

    }

    public void start() throws IOException {
        NioSocketAcceptor acceptor = new NioSocketAcceptor();

        InnerIoHandler handler = new InnerIoHandler();

        // Start proxy.
        acceptor.setHandler(handler);
        acceptor.bind(new InetSocketAddress(PORT));

        System.out.println("Inner Server Listening On Port " + PORT);
    }

}
