package kyodai;

import java.awt.*;
import javax.swing.*;

/**
 * <p>Title: LianLianKan</p>
 * <p>Description: ������</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: www.wuhantech.com</p>
 * @author ZhangJian
 * @version 1.0
 */

public class ClockAnimate
    extends JPanel
    implements Runnable {

  private volatile Thread thread;
  long startTime = 0l; //��ʼʱ��
  long usedTime = 0l; //ʹ��ʱ��

  Color color = new Color(212, 255, 200);
  //Color jfcBlue = new Color(255, 255, 0);
  //Color jfcBlue = new Color(55, 77, 118);
  Font font48 = new Font("serif", Font.PLAIN, 28);
  java.text.DecimalFormat df = new java.text.DecimalFormat("000");
  java.text.DecimalFormat df2 = new java.text.DecimalFormat("0");

  public ClockAnimate() {
    this.setMinimumSize(new Dimension(156, 48));
    this.setPreferredSize(new Dimension(156, 48));
  }

  /**
   * ����ʱ��
   * @param g
   */
  public void paint(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    Dimension d = getSize();
    g2.setBackground(new Color(111, 146, 212));
    g2.clearRect(0, 0, d.width, d.height);
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
    g2.setColor(color);
    g2.setFont(font48);

    g2.drawString("Time:" + getTime(), 16, 40);
  }

  /**
   * ȡ��ʹ��ʱ���ʽ������ַ���
   * @param time
   * @return
   */
  String getTime() {
    int sec, ms;
    long time;
    time = usedTime;
    sec = Math.round(time / 1000);
    time -= sec * 1000;
    ms = Math.round(time / 100);
    return (df.format(sec) + "." + df2.format(ms));
  }

  public void start() {
    startTime = System.currentTimeMillis();
    thread = new Thread(this);
    thread.start();
  }

  public void run() {
    Thread currentThread = Thread.currentThread();

    while (thread == currentThread) {
      long time = System.currentTimeMillis();
      usedTime = time - startTime;
      try {
        repaint();
        thread.sleep(100l);
      }
      catch (InterruptedException ex) {
      }
    }
  }

  public void stop() {
    if (thread != null) {
      thread = null;
    }
  }

  /**
   * ȡ���û�ʹ�õ�ʱ��
   * @return
   */
  public int getUsedTime() {
    return Math.round(usedTime / 1000);
  }

}