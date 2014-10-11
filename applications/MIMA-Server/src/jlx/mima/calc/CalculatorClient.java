package jlx.mima.calc;

import java.net.InetSocketAddress;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.SocketConnector;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

/**
 * {class description}
 * <br>
 *  
 * <p>
 * Create on : 2012-11-29<br>
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
public class CalculatorClient {

    private static final int CONNECT_TIMEOUT = 3000;
    /**
     * Constructors.
     */
    public CalculatorClient() {
        SocketConnector connector = new NioSocketConnector();
        connector.setConnectTimeoutMillis(CONNECT_TIMEOUT);
        connector.getFilterChain().addLast("logger", new LoggingFilter());
        ConnectFuture connectFuture = connector.connect(new InetSocketAddress("127.0.0.1", 10010));
        connectFuture.awaitUninterruptibly();

    }

}
