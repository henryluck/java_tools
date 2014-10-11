package kyodai.map;

import java.awt.*;
import javax.swing.*;

import kyodai.*;

/**
 * <p>Title: LianLianKan</p>
 * <p>Description: ������</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: www.wuhantech.com</p>
 * @author ZhangJian
 * @version 1.0
 */

public class AnimateDelete
    implements Runnable {
  static JButton[] dots;
  static long delay = 20l;
  int[] array = new int[44]; //������ֻ����Ϊ2��1��
  private int count = 0;
  private volatile Thread thread;

  public AnimateDelete(JButton[] dots) {
    this.dots = dots;
    array = new int[0];
  }

  /**
   * ��ʼ��
   * @param direct ����1��ʾa, b��ͬһֱ���ϣ�0��ʾa, b��ͬһ������
   * @param a
   * @param b
   */
  public AnimateDelete(int direct, Point a, Point b) {
    initArray();

    calcTwoPoint(direct, a, b);
    start();
  }

  /**
   *
   * @param direct ����1��ʾa, b��ͬһֱ���ϣ�b, c��ͬһ�����ϣ�
   * 0��ʾa, b��ͬһ�����ϣ�b, c��ͬһֱ����
   * @param a
   * @param b
   * @param c
   */
  public AnimateDelete(int direct, Point a, Point b, Point c) {
    initArray();

    if (direct == 1) { //�Ⱥ����
      calcTwoPoint(1, a, b);
      count--;
      calcTwoPoint(0, b, c);
    }
    else {
      calcTwoPoint(0, a, b);
      count--;
      calcTwoPoint(1, b, c);
    }
    start();
  }

  /**
   *
   * @param direct 1��ʾa, bΪ���ߣ�b, cΪ����, c, dΪ����
   * 0��ʾa, bΪ���ߣ�b, cΪ���ߣ�c, dΪ����
   * @param a
   * @param b
   * @param c
   * @param d
   */
  public AnimateDelete(int direct, Point a, Point b, Point c, Point d) {
    initArray();

    if (direct == 1) { //�ᡢ�����᷽ʽ����
      calcTwoPoint(1, a, b);
      count--;
      calcTwoPoint(0, b, c);
      count--;
      calcTwoPoint(1, c, d);
    }
    else { //�����ᡢ����ʽ����
      calcTwoPoint(0, a, b);
      count--;
      calcTwoPoint(1, b, c);
      count--;
      calcTwoPoint(0, c, d);
    }
    start();
  }

  private void calcTwoPoint(int direct, Point a, Point b) {
    int offset = 0;
    if (direct == 1) { //��������
      if (a.y > b.y) { //a����b���Ǵ���������ˮƽ��������
        for (int y = a.y; y >= b.y; y--) {
          offset = a.x * Setting.COLUMN + y;
          array[count] = offset;
          count++;
        }
      }
      else { //a����b���Ǵ���������ˮƽ��������
        for (int y = a.y; y <= b.y; y++) {
          offset = a.x * Setting.COLUMN + y;
          array[count] = offset;
          count++;
        }
      }
    }
    else { //��������
      if (a.x > b.x) { //a����b���Ǵ������ϴ�ֱ����
        for (int x = a.x; x >= b.x; x--) {
          offset = x * Setting.COLUMN + a.y;
          array[count] = offset;
          count++;
        }
      }
      else { //a����b���Ǵ������´�ֱ����
        for (int x = a.x; x <= b.x; x++) {
          offset = x * Setting.COLUMN + a.y;
          array[count] = offset;
          count++;
        }
      }
    }
  }

  /**
   * ���ö����ٶ�
   * @param speed
   */
  public void setSpeed(int speed) {
    delay = speed * 10;
  }

  private void initArray() {
    if (array == null || array.length == 0) {
      return;
    }
    for (int i = 0; i < array.length; i++) {
      array[i] = -1;
    }
  }

  /*public int[] getArray() {
    return array;
     }*/

  public void test() {
    if (array == null || array.length == 0) {
      return;
    }

    for (int i = 0; i < array.length; i++) {
      if (array[i] != -1) {
        message("[" + array[i] + "]  ");
      }
    }
    System.out.println();
  }

  public void start() {
    thread = new Thread(this);
    thread.start();
  }

  public void run() {
    if (count < 2) {
      return;
    }

    Thread currentThread = Thread.currentThread();
    boolean animate = true;
    while (thread == currentThread && animate) {
      for (int i = 1; i < count - 1; i++) {
        dots[array[i]].setEnabled(true);
        dots[array[i]].setIcon(Kyodai.GuideIcon);
        try {
          thread.sleep(delay);
        }
        catch (InterruptedException ex) {
        }
      }

      for (int i = 1; i < count - 1; i++) {
        dots[array[i]].setIcon(null);
        dots[array[i]].setEnabled(false);
        try {
          thread.sleep(delay);
        }
        catch (InterruptedException ex) {
        }
      }

      dots[array[0]].setIcon(null);
      dots[array[0]].setEnabled(false);
      dots[array[count - 1]].setIcon(null);
      dots[array[count - 1]].setEnabled(false);

      animate = false;
    }

    stop();
  }

  public void stop() {
    if (thread != null) {
      thread = null;
    }
  }

  void message(String str) {
    System.out.println(str);
  }
}