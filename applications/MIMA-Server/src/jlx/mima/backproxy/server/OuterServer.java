package jlx.mima.backproxy.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Stack;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 对外的服务器，远程连接这个服务进来 <br>
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
public class OuterServer {

    private static final int PORT = 12312;
    private static Stack<IoSession> outerSession = new Stack<IoSession>();
    private static final Logger LOGGER = LoggerFactory.getLogger(OuterServer.class);
    private NioSocketAcceptor acceptor = new NioSocketAcceptor();

    private static final OuterServer instance = new OuterServer();
    /**
     * Constructors.
     */
    private OuterServer() {
        // TODO Auto-generated constructor stub
    }

    public static OuterServer getInstance() {
        return instance;
    }

    public static void printSessionSize() {
        LOGGER.info("OuterServer目前管理的session数量：" + instance.getAcceptor().getManagedSessionCount());
        LOGGER.info("OuterServer内部栈outerSession会话数量：" + OuterServer.outerSessionSize());
    }

    public static IoSession popOuterSession() {
        LOGGER.info("outerSession session出栈");
        synchronized (outerSession) {
            IoSession session = outerSession.pop();
            if(session.isClosing()){
                LOGGER.error("错误，Session已经关闭，但是还在栈中！！！");
            }
            return session;
        }
    }

    public static void pushOuterSession(IoSession session) {
        LOGGER.info("outerSession session入栈");
        synchronized (outerSession) {
            // 加一个校验
            if (!session.isClosing())
                outerSession.push(session);
        }
    }

    public void start() throws IOException {
        OuterIoHandler handler = new OuterIoHandler();

        // Start proxy.
        acceptor.setHandler(handler);
        acceptor.bind(new InetSocketAddress(PORT));

        System.out.println("Outer Server Listening On Port " + PORT);
    }

    /**
     * {method description}.
     * 
     * @return
     */
    public static int outerSessionSize() {
        return outerSession.size();
    }

    /**
     * {method description}.
     * 
     * @param session
     */
    public static void removeOuterSession(IoSession session) {
        LOGGER.info("删除栈outerSession中的session.");
        synchronized (outerSession) {
            outerSession.remove(session);
        }

    }

    /**
     * @return acceptor - {return content description}
     */
    public NioSocketAcceptor getAcceptor() {
        return acceptor;
    }

}
