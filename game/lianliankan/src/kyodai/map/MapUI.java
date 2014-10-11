package kyodai.map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import kyodai.*;

/**
 * <p>Title: LianLianKan</p>
 * <p>Description: ������</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: www.wuhantech.com</p>
 * @author ZhangJian
 * @version 1.0
 */

public class MapUI
    extends JPanel
    implements ActionListener, Runnable {
  private Map map;
  private JButton[] dots;

  private Point lastPoint = new Point(0, 0); //��һ���������
  private boolean isSelected = false; //�Ƿ��Ѿ�ѡ����һ����

  private int score = 0; //��¼�û��ĵ÷�
  private ClockAnimate clockAnimate; //ͬ����ʾʱ��
  //AnimateDelete animate; //����
  JButton goTop10;

  private ScoreAnimate scoreAnimate;
  int stepScore = 0; //�������ķ�
  int limitTime = 0; //�޶�Ѱ�ҵ�ʱ��(��)

  private boolean isPlaying = false; //��ǰ�Ƿ�������Ϸ��

  public MapUI(Map map, JButton[] dots) {
    this.map = map;
    this.dots = dots;

    //animate = new AnimateDelete(dots);

    GridLayout gridLayout = new GridLayout();
    this.setLayout(gridLayout);
    gridLayout.setRows(Setting.ROW);
    gridLayout.setColumns(Setting.COLUMN);
    gridLayout.setHgap(2);
    gridLayout.setVgap(2);
    this.setLayout(gridLayout);
    this.setBackground(Kyodai.DarkColor);

    for (int row = 0; row < Setting.ROW; row++) {
      for (int col = 0; col < Setting.COLUMN; col++) {
        int index = row * Setting.COLUMN + col;
        dots[index].addActionListener(this);
        this.add(dots[index]);
      }
    }
  }

  public void setMap(Map map) {
    this.map = map;
  }

  /**
   * ��ȡ�������ϵ�goTop10��ť���Ա����
   * @param goTop10
   */
  public void setTop10Button(JButton goTop10) {
    this.goTop10 = goTop10;
  }

  /**
   * �������������û���
   */
  private void paint() {
    for (int row = 0; row < Setting.ROW; row++) {
      for (int col = 0; col < Setting.COLUMN; col++) {
        int index = row * Setting.COLUMN + col;
        /*message("row = " + row + ", col = " + col + ", index = " + index
                + ", map[][] = " + map.getMap()[row][col]);*/
        if (map.getMap()[row][col] > 0) {
          dots[index].setIcon(Kyodai.BlocksIcon[map.getMap()[row][col] - 1]);
          dots[index].setEnabled(true);
        }
        else {
          dots[index].setIcon(null);
          dots[index].setEnabled(false);
        }
      }
    }
  }

  public void repaint(Graphics g) {
    paint();
  }

  /**
   * �жϵ�ǰ�Ƿ��Ѿ�û�п������ķ���
   * @param a
   * @return
   */
  private boolean validMap(Point a) {
    if (map.getCount() == 0) {
      return true;
    }

    Line line = new Line(0, new Point(), new Point());
    map.setTest(true); //ֻ����
    line = map.findNext(a);
    int offset = 0;
    if (line.direct == 1) { //�ҵ��˿�������
      return true;
    }
    else {
      return false;
    }
  }

  /**
   * ���µ�ǰ��ʾ�ķ���
   * @param l
   * @param c
   */
  private void showScore(int l, int c) {
    if (scoreAnimate == null) {
      return;
    }
    scoreAnimate.setScore(l, c);
  }

  /**
   * ˢ�µ�ǰ�����з�ʽ
   */
  public void refresh() {
    if (!isPlaying) { //������Ϸ��,����
      return;
    }
    if (map.getCount() == 0) {
      Kyodai.showHint("��ˢ����û�ˣ�");
    }
    if (Setting.Sound == 1) {
      new Sound(Sound.REFRESH);
    }

    if (validMap(new Point( -1, -1))) {
      score -= Setting.freshScore;
      showScore(score - 1, score);
    }
    else {
      showScore(score, score + Setting.freshScore);
      score += Setting.freshScore;
    }

    score -= Setting.freshScore;
    showScore(score - 1, score);

    map.refresh();
    paint();
  }

  /**
   * ����������
   * @param a
   * @param b
   */
  void earse(Point a, Point b) {
    //paint();

    int offset;
    offset = a.x * Setting.COLUMN + a.y;
    dots[offset].setIcon(null);
    dots[offset].setEnabled(false);

    offset = b.x * Setting.COLUMN + b.y;
    dots[offset].setIcon(null);
    dots[offset].setEnabled(false);

    //�����ͼ�����ɣ��ر�
    if (map.getCount() == 0) {
      int remainTime = limitTime - clockAnimate.getUsedTime();
      message("remainTime = " + remainTime);
      if (remainTime > 0) {
        showScore(score, score + remainTime * Setting.timeScore);
        score += remainTime * Setting.timeScore;
      }
      isPlaying = false;
      stop();
      Kyodai.showHint("ʱ��+ " + remainTime * Setting.timeScore + "���뿴�����������");
      goTop10.setEnabled(true);
    }
    else {
      //test1(map.getDeleteArray());
    }
  }

  /**
   * �Զ�Ѱ����Ѵ�
   * @param a
   */
  public void findNext(Point a) {
    if (!isPlaying) { //������Ϸ��,����
      return;
    }
    if (map.getCount() == 0) {
      Kyodai.showHint("���һ���ͷ�ɣ�û�ˣ�");
      return;
    }
    Line line = new Line(0, new Point(), new Point());
    map.setTest(true); //����map��ǰֻ�ǲ��ԣ�������Ҫ����ɾ������
    line = map.findNext(a);
    int offset = 0;
    if (line.direct == 1) { //�ҵ��˿�������
      if (Setting.Sound == 1) {
        new Sound(Sound.HINT);
      }
      offset = line.a.x * Setting.COLUMN + line.a.y;
      dots[offset].setBorder(Kyodai.Hint);
      offset = line.b.x * Setting.COLUMN + line.b.y;
      dots[offset].setBorder(Kyodai.Hint);
      score -= Setting.hintScore;
      showScore(score - 1, score);
    }
    else {
      Kyodai.showHint("�Ҳ�������ˢ��");
    }
  }

  /**
   * �Զ��ҳ���������ͼ�ϵ�������
   * @param a
   * @param showMessage
   * @return
   */
  public boolean bomb(Point a, boolean showMessage) {
    if (!isPlaying) { //������Ϸ��,����
      return false;
    }
    if (map.getCount() == 0) {
      Kyodai.showHint("��ը����ͷ�ɣ�û�ˣ�");
      return false;
    }
    Line line = new Line(0, new Point(), new Point());
    map.setTest(false);
    line = map.findNext(a);
    int offset = 0;
    if (line.direct == 1) { //�ҵ��˿�������
      if (Setting.Sound == 1) {
        new Sound(Sound.BOMB);
      }
      offset = line.a.x * Setting.COLUMN + line.a.y;
      dots[offset].setBorder(Kyodai.unSelected);
      offset = line.b.x * Setting.COLUMN + line.b.y;
      dots[offset].setBorder(Kyodai.unSelected);
      map.earse(line.a, line.b);
      earse(line.a, line.b);

      score -= Setting.bombScore;
      showScore(score - 1, score);
      return true;
    }
    else {
      if (showMessage) {
        Kyodai.showHint("ը���ò��ˣ���ˢ��");
      }
      return false;
    }
  }

  private void message(String str) {
    //System.out.println(str);
    Kyodai.showHint(str);
  }

  /**
   * �Զ���Ϸ
   */
  public void autoPlay() {
    if (!isPlaying) { //������Ϸ��,����
      return;
    }
    //���ʹ�øù��ܣ�����ʱ���
    limitTime = 0;

    while (map.getCount() > 0) {
      if (bomb(new Point( -1, -1), false)) {
        message("ը��ʹ�óɹ���");
      }
      else {
        message("�Ҳ������õ㣬ˢ�¡���");
        refresh();
      }
    }
  }

  /**
   * ��ȡϵͳ�ļƷְ�
   * @param score
   */
  public void setScore(ScoreAnimate score) {
    this.scoreAnimate = score;
  }

  /**
   * ��ȡϵͳ�ļ�ʱ��
   * @param clock
   */
  public void setClock(ClockAnimate clock) {
    this.clockAnimate = clock;
  }

  public void actionPerformed(ActionEvent e) {
    JButton button = (JButton) e.getSource();
    int offset = Integer.parseInt(button.getActionCommand());
    int row, col;
    row = Math.round(offset / Setting.COLUMN);
    col = offset - row * Setting.COLUMN;
    if (map.getMap()[row][col] < 1) { //�������û��ͼƬ
      return;
    }

    if (Setting.Sound == 1) {
      new Sound(Sound.SELECT);
    }

    if (isSelected) {
      message("�ϴ��Ѿ�ѡ����һ����");
      message("�ϴ�ѡ��������Ϊ�� " + lastPoint.x + ", " + lastPoint.y +
              " ֵΪ�� " + map.getMap()[lastPoint.x][lastPoint.y] +
              " λ��Ϊ�� " + (lastPoint.x * Setting.COLUMN + lastPoint.y));

      if (lastPoint.x == row && lastPoint.y == col) { //���ϴ�ѡ��ĵ�
        message("���ѡ��ĵ���ϴε���ͬһ�㣬ȡ��ѡ��״̬");
        button.setBorder(Kyodai.unSelected);
        isSelected = false;
      }
      else {
        //�ж��Ƿ��������
        message("���ѡ��ĵ���ϴεĵ㲢����ͬ");
        Point current = new Point(row, col);
        message("���ѡ��ĵ������Ϊ�� " + row + ", " + col + " ֵΪ�� " +
                map.getMap()[row][col] +
                " λ��Ϊ�� " + (row * Setting.COLUMN + col));
        map.setTest(false);
        if (map.test(lastPoint, current)) {
          message("�������������ִ������");
          //����ǰ��ȡ����ǰѡ���ı߿���Ϊ�п�������ʾ
          dots[row * Setting.COLUMN + col].setBorder(Kyodai.unSelected);

          map.earse(current, lastPoint);
          earse(current, lastPoint);
          dots[lastPoint.x * Setting.COLUMN +
              lastPoint.y].setBorder(Kyodai.unSelected);
          lastPoint = new Point(0, 0);
          isSelected = false;

          showScore(score, score + Setting.correctScore + stepScore);
          score += Setting.correctScore + stepScore;

          if (Setting.Sound == 1) {
            new Sound(Sound.EARSE);
          }
        }
        else {
          message("���ѡ��ĵ����ϴ�ѡ��ĵ��޽⣬�ı�ѡ��Ϊ��ǰ��");
          dots[lastPoint.x * Setting.COLUMN +
              lastPoint.y].setBorder(Kyodai.unSelected);
          button.setBorder(Kyodai.Selected);
          lastPoint.x = row;
          lastPoint.y = col;
          isSelected = true;

          score -= Setting.wrongScore;
          showScore(score - 1, score);
        }
      }
    }
    else {
      message("�ϴβ�δѡ��ĵ㣬�õ�ǰ��Ϊѡ���");
      message("��ǰ������Ϊ�� " + row + ", " + col + " ֵΪ�� " +
              map.getMap()[row][col] +
              " λ��Ϊ�� " + (row * Setting.COLUMN + col));
      button.setBorder(Kyodai.Selected);
      lastPoint.x = row;
      lastPoint.y = col;
      isSelected = true;
    }
  }

  /**
   * ���ܵ�ǰ�ķ���
   * @param score
   * @return
   */
  public String encode() {
    if (score < 0) {
      Kyodai.showHint("���ֻ������������´�Ŭ���ɣ�");
    }

    int level, f1, f2;
    level = Setting.LevelIndex;
    f1 = (score - 102) * (score - 102);
    f2 = (1978 - score) * (1978 - score);
    return "s=" + score + "&l=" + level + "&f1=" + f1 + "&f2=" + f2;
  }

  public void start() {
    goTop10.setEnabled(false);

    isPlaying = true;
    limitTime = map.getCount() * Setting.limitScore;
    message("limitTime = " + limitTime);
    paint();
  }

  public void run() {

  }

  public void stop() {
    clockAnimate.stop();
  }

}