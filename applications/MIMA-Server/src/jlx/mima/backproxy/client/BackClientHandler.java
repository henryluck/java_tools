package jlx.mima.backproxy.client;

import java.net.SocketAddress;

import jlx.mima.backproxy.SessionKey;
import jlx.mima.backproxy.util.IoBufferUtils;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class BackClientHandler extends IoHandlerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(BackClientHandler.class);

    private final BackClientProxyIoHandler connectorHandler = new BackClientProxyIoHandler();

    private final IoConnector connector;

    private final SocketAddress remoteAddress;

    public BackClientHandler(IoConnector connector, SocketAddress remoteAddress) {
        this.connector = connector;
        this.remoteAddress = remoteAddress;
        this.connector.setHandler(connectorHandler);
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {

    }
    @Override
    public void sessionOpened(IoSession session) {
        // Set reader idle time to 10 seconds.
        // sessionIdle(...) method will be invoked when no data is read
        // for 10 seconds.
        session.getConfig().setIdleTime(IdleStatus.READER_IDLE, Integer.MAX_VALUE);

        LOGGER.info("open一个新请求。");
    }

    @Override
    public void sessionClosed(IoSession session) {
        // Print out total number of bytes read from the remote peer.
        // System.err.println("Total " + session.getReadBytes() + " byte(s)");
        // 关闭到代理服务器的session
        IoSession futrueSession = (IoSession) session.getAttribute(SessionKey.BackClient);
        if (futrueSession != null) {
            futrueSession.removeAttribute(SessionKey.BackClient);
            futrueSession.close(true);
        }
        session.removeAttribute(SessionKey.BackClient);

        LOGGER.info("关闭一个请求，应该是长连接不应该关闭！！！，自动创建一个新的");
        // 创建一个新的
        new BackClient().start();
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) {
        LOGGER.info("触发idle方法，应该是长连接不应该关闭！！！");
        // Close the connection if reader is idle.
        session.close(true);
    }

    @Override
    public void messageReceived(final IoSession session, Object message) {
        LOGGER.info("收到来自外网ie的一个请求..");
        IoSession futrueSession = (IoSession) session.getAttribute(SessionKey.BackClient);
        // 处理一次请求多次交换的情况
        // 如果不是null，证明是一次请求数据没发送完成
        if (futrueSession == null) {
            ConnectFuture future = connector.connect(remoteAddress);
            future.awaitUninterruptibly(); // 连接完成

            futrueSession = future.getSession();
            futrueSession.setAttribute(SessionKey.BackClient, session);
            session.setAttribute(SessionKey.BackClient, future.getSession());
        }
        // 写给proxy
        IoBufferUtils.write(futrueSession, (IoBuffer) message);
        IoBufferUtils.printIoBuffer((IoBuffer) message);
    }

}
