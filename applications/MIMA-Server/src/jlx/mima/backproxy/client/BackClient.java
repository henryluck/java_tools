package jlx.mima.backproxy.client;

import java.net.InetSocketAddress;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

/**
 * 在远端运行 <br>
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
public class BackClient {
    private static int PORT = 12312;
    private static String IP = "127.0.0.1";
    private static int ProxyPort = 8888;
    private static String ProxyIP = "127.0.0.1";

    public void start() {
        String serverport = System.getProperty("ServerPort");
        String serverIp = System.getProperty("ServerIP");
        if (serverport != null) {
            PORT = Integer.parseInt(serverport);
        }
        if (serverIp != null) {
            IP = serverIp;
        }
        String proxyPort = System.getProperty("ProxyPort");
        String proxyIP = System.getProperty("ProxyIP");
        if (proxyPort != null) {
            this.ProxyPort = Integer.parseInt(proxyPort);
        }
        if (proxyIP != null) {
            this.ProxyIP = proxyIP;
        }
        // Create TCP/IP connector.
        NioSocketConnector connector = new NioSocketConnector();

        // Set connect timeout.
        connector.setConnectTimeoutMillis(30 * 1000L);

        // Create TCP/IP connector.
        IoConnector connector4Proxy = new NioSocketConnector();

        // Set connect timeout.
        connector4Proxy.setConnectTimeoutMillis(30 * 1000L);

        // Start communication.
        connector
.setHandler(new BackClientHandler(connector4Proxy, new InetSocketAddress(this.ProxyIP,
                this.ProxyPort)));
        ConnectFuture cf = connector.connect(new InetSocketAddress(IP, PORT));

        // Wait for the connection attempt to be finished.
        // cf.awaitUninterruptibly();
        // cf.getSession().getCloseFuture().awaitUninterruptibly();
        // connector.dispose();
    }
}
