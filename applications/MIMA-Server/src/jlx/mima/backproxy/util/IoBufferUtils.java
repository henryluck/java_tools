package jlx.mima.backproxy.util;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

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
public class IoBufferUtils {

    /**
     * 打印buffer内容
     * 
     * @param buf
     */
    public static void printIoBuffer(IoBuffer rb) {
        rb.reset();
        while (rb.hasRemaining()) {
            System.out.print((char) rb.get());
        }
        System.out.flush();
        System.out.println();
    }

    /**
     * 把buffer写到session里面
     * 
     * @param wb
     * @param wSession
     */
    public static void write(IoSession wSession, IoBuffer rb) {
        IoBuffer wb = IoBuffer.allocate(rb.remaining());
        rb.mark(); // 这句需要，给print留数据
        wb.put(rb);
        wb.flip();
        wSession.write(wb);
    }

    /**
     * session里面写字符串
     * 
     * @param session
     * @param str
     */
    public static void write(IoSession session, String str) {
        byte[] msg = str.getBytes();
        write(session, msg);
    }

    /**
     * session里面写字符串
     * 
     * @param session
     * @param str
     */
    public static void write(IoSession session, byte[] msg) {
        IoBuffer wb = IoBuffer.allocate(msg.length);
        wb.put(msg);
        wb.flip();
        session.write(wb);
    }
}
