package kyodai;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * <p>Title: LianLianKan</p>
 * <p>Description: ������</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: www.wuhantech.com</p>
 * @author ZhangJian
 * @version 1.0
 */

public class Setting {
  public final static int COLUMN = 17; //������
  public final static int ROW = 10; //������
  public final static int limitScore = 4; //ÿ�������޶���ʱ��
  public final static int timeScore = 2; //ʱ�佱���ķ���
  public final static int wrongScore = 1; //ѡ��ʧ�ܿ۷�
  public final static int freshScore = 8; //ˢ�¹��ܿ۷�
  public final static int hintScore = 10; //��ʾ���ܿ۷�
  public final static int bombScore = 12; //ը�����ܿ۷�
  public final static int correctScore = 10; //�ɹ�������ӷ�
  public final static int MaxBlocks = 156; //�����ܳ��ֵķ������
  public final static int[] Level = {
      16, 22, 28, 34, 39}; //Level = Nʱ��Ҫ��ͼ����

  public static int Music = 1, Sound = 1, LevelIndex = 2, Animate = 1;

  public Setting() {
  }

  public void load() {
    Properties pro;
    FileInputStream read = null;

    String Music = "1", Sound = "1", LevelIndex = "2", Animate = "2";

    try {
      read = new FileInputStream("kyodai.ini");
      pro = new Properties();
      pro.load(read);

      Music = pro.getProperty("Music");
      Sound = pro.getProperty("Sound");
      LevelIndex = pro.getProperty("LevelIndex");
      Animate = pro.getProperty("Animate");
      read.close();
    }
    catch (IOException ex) {
      Music = "1";
      Sound = "1";
      LevelIndex = "2";
      Animate = "1";
    }

    try {
      this.Music = Integer.parseInt(Music);
    }
    catch (NumberFormatException ex1) {
      this.Music = 1;
    }

    try {
      this.Sound = Integer.parseInt(Sound);
    }
    catch (NumberFormatException ex1) {
      this.Sound = 1;
    }


    try {
      this.LevelIndex = Integer.parseInt(LevelIndex);
    }
    catch (NumberFormatException ex1) {
      this.LevelIndex = 1;
    }
    this.LevelIndex = this.LevelIndex < 0 ? 0 : this.LevelIndex;
    this.LevelIndex = this.LevelIndex > 4 ? 4 : this.LevelIndex;

    try {
      this.Animate = Integer.parseInt(Animate);
    }
    catch (NumberFormatException ex1) {
      this.Animate = 1;
    }
    this.Animate = this.Animate < 1 ? 1 : this.Animate;
    this.Animate = this.Animate > 10 ? 10 : this.Animate;
  }

  public void save() {
    Properties pro;
    FileOutputStream save = null;
    try {
      save = new FileOutputStream("kyodai.ini");
      pro = new Properties();
      pro.setProperty("Music", "" + Music);
      pro.setProperty("Sound", "" + Sound);
      pro.setProperty("LevelIndex", "" + LevelIndex);
      pro.setProperty("Animate", "" + Animate);
      pro.store(save, "Kyodai 1.0 alpha\r\n#by ZhangJian 2004.11\r\n#leftmoon@163.com");
      save.close();
    }
    catch (IOException ex) {
    }

  }

}