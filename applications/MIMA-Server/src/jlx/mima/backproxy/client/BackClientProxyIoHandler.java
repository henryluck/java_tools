package jlx.mima.backproxy.client;

import jlx.mima.backproxy.SessionKey;

import org.apache.mina.core.buffer.IoBuffer;
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
public class BackClientProxyIoHandler extends IoHandlerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(BackClientProxyIoHandler.class);

    /*
     * (non-Javadoc)
     * @see org.apache.mina.core.service.IoHandlerAdapter#sessionOpened(org.apache.mina.core.session.IoSession)
     */
    @Override
    public void sessionOpened(IoSession session) throws Exception {
        // 超过5秒，没有数据，关闭连接
        // 代理服务器的连接可以不关
        // session.getConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
    }

    /*
     * (non-Javadoc)
     * @see org.apache.mina.core.service.IoHandlerAdapter#sessionIdle(org.apache.mina.core.session.IoSession,
     * org.apache.mina.core.session.IdleStatus)
     */
    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        session.close(false);
    }

    /*
     * (non-Javadoc)
     * @see org.apache.mina.core.service.IoHandlerAdapter#sessionClosed(org.apache.mina.core.session.IoSession)
     */
    @Override
    public void sessionClosed(IoSession session) throws Exception {

        LOGGER.info("代理服务器连接关闭，或者连接超时关闭.");
        IoSession sessionTo = (IoSession) session.getAttribute(SessionKey.BackClient);
        if (sessionTo != null) {
            // 请求关闭的时候加一个特殊的处理，写一个结束的标志
            // OuterIoHandler做这个判断
            // IoBufferUtils.write(sessionTo, Constants.END_FLAG);
            sessionTo.removeAttribute(SessionKey.BackClient);
        }
        session.removeAttribute(SessionKey.BackClient);

    }
    @Override
    public void messageReceived(final IoSession session, Object message) {
        LOGGER.info("收到一个请求.");
        IoSession sessionTo = (IoSession) session.getAttribute(SessionKey.BackClient);
        if (sessionTo != null) {
            // 写给proxy
            IoBuffer rb = (IoBuffer) message;
            IoBuffer wb = IoBuffer.allocate(rb.remaining());
            rb.mark();
            wb.put(rb);
            wb.flip();
            sessionTo.write(wb);
            rb.reset();
        }

    }
}
