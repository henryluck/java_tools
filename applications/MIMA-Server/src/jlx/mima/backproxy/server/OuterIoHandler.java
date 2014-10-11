package jlx.mima.backproxy.server;

import jlx.mima.backproxy.SessionKey;
import jlx.mima.backproxy.util.IoBufferUtils;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
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
public class OuterIoHandler extends IoHandlerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(OuterIoHandler.class);

    /*
     * (non-Javadoc)
     * @see org.apache.mina.core.service.IoHandlerAdapter#sessionCreated(org.apache.mina.core.session.IoSession)
     */
    @Override
    public void sessionCreated(IoSession session) throws Exception {
        // create的时候，把session放进去
        // 先暂停读和写，session相互引用之后，再放开
        // session.suspendRead();
        // session.suspendWrite();
        OuterServer.pushOuterSession(session);
        LOGGER.info("sessionCreated Open一个Session,加入栈。");
        LOGGER.info("sessionCreated OuterServer目前管理的session数量："
                + session.getService().getManagedSessionCount());
        LOGGER.info("sessionCreated OuterServer内部栈outerSession会话数量：" + OuterServer.outerSessionSize());
    }
    /*
     * (non-Javadoc)
     * @see org.apache.mina.core.service.IoHandlerAdapter#sessionOpened(org.apache.mina.core.session.IoSession)
     */
    @Override
    public void sessionOpened(IoSession session) throws Exception {

    }

    /*
     * (non-Javadoc)
     * @see org.apache.mina.core.service.IoHandlerAdapter#sessionClosed(org.apache.mina.core.session.IoSession)
     */
    @Override
    public void sessionClosed(IoSession session) throws Exception {
        super.sessionClosed(session);
        // 关闭的时候删除栈里面的session，清除session里面的属性
        OuterServer.removeOuterSession(session);
        IoSession innerSession = (IoSession) session.getAttribute(SessionKey.InnerSession);
        if (innerSession != null) {
            session.removeAttribute(SessionKey.InnerSession);
        }
        LOGGER.info("外部通道，关闭一个Session");
        LOGGER.info("OuterServer目前管理的session数量：" + session.getService().getManagedSessionCount());
        LOGGER.info("OuterServer内部栈outerSession会话数量：" + OuterServer.outerSessionSize());
    }

    /*
     * (non-Javadoc)
     * @see org.apache.mina.core.service.IoHandlerAdapter#exceptionCaught(org.apache.mina.core.session.IoSession,
     * java.lang.Throwable)
     */
    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        LOGGER.error("出错了：" + cause.toString());
        session.close(false);
    }

    public void messageReceived(IoSession session, Object message) throws Exception {
        LOGGER.info("客户端发来的新数据,session。");
        IoBuffer rb = (IoBuffer) message;
        rb.mark();
        // debug调试
        // IoBufferUtils.printIoBuffer(rb);
        rb.reset();

        IoSession innerSession = (IoSession) session.getAttribute(SessionKey.InnerSession);
        if(innerSession != null){
            IoBufferUtils.write(innerSession, rb);

            //取最后面的几位，判断连接是不是关闭了
            //是在BackClientProxyIoHandler里面放的结束标志

            //
            // int flagLength = Constants.END_FLAG.length;
            // // 不用判断结束标志的情况
            // if (rb.limit() < flagLength) {
            // rb.reset();
            // IoBufferUtils.write(innerSession, rb);
            // }
            // byte[] tmp = new byte[flagLength];
            // rb.position(rb.limit() - flagLength - 1);
            // rb.get(tmp);
            // // 如果是结束标志
            // if (Arrays.equals(tmp, Constants.END_FLAG)) {
            // rb.reset();
            // rb.limit(rb.limit() - flagLength);
            // IoBufferUtils.write(innerSession, rb);
            // innerSession.close(true);
            // } else {
            // // 写回浏览器端
            // rb.reset();
            // IoBufferUtils.write(innerSession, rb);
            // }
        }

    }
}
