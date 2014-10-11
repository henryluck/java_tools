package jlx.mima.calc;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {class description} <br>
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
public class CalculatorServer {

    private static final int PORT = 10010;

    private static final Logger LOGGER = LoggerFactory.getLogger(CalculatorServer.class);

    public static void main(String[] args) throws IOException {
        IoAcceptor acceptor = new NioSocketAcceptor();

        acceptor.getFilterChain().addLast("logger", new LoggingFilter());
        acceptor.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));

        acceptor.setHandler(new CalculatorHandler());
        acceptor.bind(new InetSocketAddress(PORT));

        LOGGER.info("计算器服务已启动，端口是" + PORT);
    }

}
