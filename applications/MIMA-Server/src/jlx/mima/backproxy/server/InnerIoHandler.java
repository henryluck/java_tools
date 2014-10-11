package jlx.mima.backproxy.server;

import jlx.mima.backproxy.SessionKey;
import jlx.mima.backproxy.util.IoBufferUtils;

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
public class InnerIoHandler extends IoHandlerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(InnerIoHandler.class);

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        // 先暂停读和写，session相互引用之后，再放开
        session.suspendRead();
        session.suspendWrite();
    }
    /*
     * (non-Javadoc)
     * @see org.apache.mina.core.service.IoHandlerAdapter#sessionOpened(org.apache.mina.core.session.IoSession)
     */
    @Override
    public void sessionOpened(IoSession session) throws Exception {
        LOGGER.info("sessionOpened start...");
        // 超过5秒，没有数据，关闭连接
        session.getConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
        // ie有新的请求进来的时候，找outerSession
        IoSession outerSession = OuterServer.popOuterSession();
        outerSession.setAttribute(SessionKey.InnerSession, session);
        // 相互引用
        session.setAttribute(SessionKey.OuterSession, outerSession);

        // 恢复session读写
        outerSession.resumeRead();
        outerSession.resumeWrite();
        session.resumeRead();
        session.resumeWrite();

        LOGGER.info("Session id,in;out::" + session.getId() + ";" + outerSession.getId());
        LOGGER.info("浏览器有新请求,session加相互引用...");

    }

    /*
     * (non-Javadoc)
     * @see org.apache.mina.core.service.IoHandlerAdapter#sessionIdle(org.apache.mina.core.session.IoSession,
     * org.apache.mina.core.session.IdleStatus)
     */
    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        // 超过5秒，没有数据，关闭连接
        session.close(true);
    }
    /*
     * (non-Javadoc)
     * @see org.apache.mina.core.service.IoHandlerAdapter#sessionClosed(org.apache.mina.core.session.IoSession)
     */
    @Override
    public void sessionClosed(IoSession session) throws Exception {
        LOGGER.info("浏览器关闭请求或者连接超时.");
        // 把outerSession的属性删除，放回栈里面
        IoSession outerSession = (IoSession) session.getAttribute(SessionKey.OuterSession);
        if (outerSession != null) {
            outerSession.removeAttribute(SessionKey.InnerSession);
            // 放回栈里面
            // LOGGER.info("外部session放回栈里面.");
            OuterServer.pushOuterSession(outerSession);
            // 直接关闭outSession通道，让远端主动新建session
            // LOGGER.info("外部session关闭，让远端client主动重新连接.");
            // outerSession.close(true);

            OuterServer.printSessionSize();
            // 删除属性
            session.removeAttribute(SessionKey.OuterSession);
        }



    }

    /*
     * (non-Javadoc)
     * @see org.apache.mina.core.service.IoHandlerAdapter#exceptionCaught(org.apache.mina.core.session.IoSession,
     * java.lang.Throwable)
     */
    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        LOGGER.info("浏览器请求处理异常，关闭连接.");
        // 打印错误到浏览器端
        LOGGER.error(cause.toString());
        // cause.printStackTrace();
        IoBufferUtils.write(session, cause.toString());
        // 关闭session，要用false，等wb输出完成
        session.close(false);

    }
//
    public void messageReceived(IoSession session, Object message) throws Exception {
        LOGGER.info("浏览器发送请求内容.");
        IoBuffer rb = (IoBuffer) message;
        IoBufferUtils.write(((IoSession) session.getAttribute(SessionKey.OuterSession)), rb);
        // 打印内容--------------
        IoBufferUtils.printIoBuffer(rb);
    }
}
