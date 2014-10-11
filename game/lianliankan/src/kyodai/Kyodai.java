package kyodai;

import javax.swing.*;
import java.awt.*;
import java.net.*;
import java.awt.event.*;
import javax.swing.border.*;

import kyodai.map.*;
import kyodai.topbar.*;

/**
 * <p>Title: LianLianKan</p>
 * <p>Description: ������</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: www.wuhantech.com</p>
 * @author ZhangJian
 * @version 1.0
 */

public class Kyodai
    extends JFrame
    implements ActionListener {
  public static Color DarkColor = new Color(55, 77, 118); //��ɫ
  public static Color LightColor = new Color(111, 146, 212); //��ɫ
  public static ImageIcon[] BlocksIcon = new ImageIcon[39]; //��Ϸ�з����ͼ��
  public static ImageIcon GuideIcon; //���ߵ�ͼ��
  public static Border unSelected = BorderFactory.createLineBorder(DarkColor, 1); //δѡ��ʱ�ı߿�
  public static Border Selected = BorderFactory.createLineBorder(Color.white, 1); //ѡ�к�ı߿�
  public static Border Hint = BorderFactory.createLineBorder(Color.green, 1); //��ʾ�ı߿�

  Dimension faceSize = new Dimension(780, 500);
  Image icon;
  private int counter = 0;

  JPanel toolBar = new JPanel(); //������
  JPanel actionPanel = new JPanel(); //�û�������
  JPanel contentPanel = new JPanel(); //����
  JPanel statusPanel = new JPanel(); //״̬��
  Border emptyBorder = BorderFactory.createEmptyBorder(); //δѡ��ʱ�ı߿�
  JButton startButton = new JButton(); //"��ʼ"
  JButton refreshButton = new JButton(); //"ˢ��"
  JButton hintButton = new JButton(); //"��ʾ"
  JButton bombButton = new JButton(); //"ը��"
  JButton demoButton = new JButton(); //"��ʾ"

  JButton setupButton = new JButton(); //����
  JButton helpButton = new JButton(); //����
  JButton aboutButton = new JButton(); //����
  JButton goTop10 = new JButton("Go top 10");
  HelpDialog helpDialog; //�����Ի���
  AboutDialog aboutDialog; //���ڶԻ���
  //SetupDialog setupDialog; //���öԻ���

  public static JTextField statusField = new JTextField(
      "Weclome to Kyodai 1.0 alpha");
  ImageIcon imgStart, imgHint, imgRefresh, imgBomb, imgDemo;
  ImageIcon imgSetup, imgHelp, imgAbout;

  JButton[] dots = new JButton[Setting.ROW * Setting.COLUMN];
  Setting setting = new Setting();

  MapUI ui;
  Map map;
  ClockAnimate clock = new ClockAnimate(); //ʱ��
  ScoreAnimate score = new ScoreAnimate(); //����
  AnimateDelete animateDelete = new AnimateDelete(dots);
  Music music = new Music();

  public Kyodai() {
    initResource();
    map = new Map();
    ui = new MapUI(map, dots);
    initUI();
    ui.setScore(score);
    ui.setClock(clock);
    ui.setTop10Button(goTop10);
    animateDelete.setSpeed(setting.Animate);

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.pack();
    this.setSize(faceSize);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    this.setLocation( (int) (screenSize.width - faceSize.getWidth()) / 2,
                     (int) (screenSize.height - faceSize.getHeight()) / 2);
    this.setResizable(false);
    this.setTitle("Kyodai 1.0 alpha"); //���ñ���
    this.setIconImage(icon); //���ó���ͼ��

    //���ö������
    URLClassLoader urlLoader = (URLClassLoader)this.getClass().getClassLoader();
    URL url = urlLoader.findResource("images/cursor.gif");
    Image animateImage = new ImageIcon(url).getImage();
    Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(
        animateImage, new Point(0, 0), "cursor");
    this.setCursor(cursor);
    this.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent we) {
        setting.save();
      }
    });

    if (setting.Music == 1) {
      music.play();
    }
  }

  /**
   * ��ʼ��ϵͳ����Ҫ����Դ
   */
  public void initResource() {
    URLClassLoader urlLoader = (URLClassLoader)this.getClass().getClassLoader();
    URL url;

    //����ͼ��
    icon = getImage("images/kyodai16.gif");

    for (int i = 0; i < BlocksIcon.length; i++) {
      BlocksIcon[i] = new ImageIcon(getImage("images/" + (i + 1) + ".gif"));
    }

    imgStart = new ImageIcon(getImage("images/start.gif"));
    imgRefresh = new ImageIcon(getImage("images/refresh.gif"));
    imgHint = new ImageIcon(getImage("images/hint.gif"));
    imgBomb = new ImageIcon(getImage("images/bomb.gif"));
    imgDemo = new ImageIcon(getImage("images/demo.gif"));

    imgSetup = new ImageIcon(getImage("images/setup.gif"));
    imgHelp = new ImageIcon(getImage("images/help.gif"));
    imgAbout = new ImageIcon(getImage("images/about.gif"));

    GuideIcon = new ImageIcon(getImage("images/dots.gif"));

    //��ʼ������
    for (int i = 0; i < dots.length; i++) {
      dots[i] = new JButton();
      dots[i].setActionCommand("" + i);
      dots[i].setBorder(unSelected);
      dots[i].setBackground(DarkColor);
    }

    //��ȡ�û�����
    setting.load();

    //��ʼ���Ի���
    helpDialog = new HelpDialog(this); //�����Ի���
    aboutDialog = new AboutDialog(this); //���ڶԻ���
  }

  /**
   * ��ʼ���û�����
   */
  public void initUI() {
    //�������岼��
    Border border = BorderFactory.createBevelBorder(BevelBorder.LOWERED,
        new Color(45, 92, 162),
        new Color(43, 66, 97),
        new Color(45, 92, 162),
        new Color(84, 123, 200));
    BorderLayout borderLayout = new BorderLayout();
    toolBar.setBackground(DarkColor);
    toolBar.setBorder(border);
    toolBar.setPreferredSize(new Dimension(780, 48));
    toolBar.setMinimumSize(new Dimension(780, 48));
    toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
    actionPanel.setBackground(LightColor);
    actionPanel.setBorder(border);
    actionPanel.setPreferredSize(new Dimension(160, 380));
    actionPanel.setMinimumSize(new Dimension(160, 380));
    contentPanel.setBackground(DarkColor);
    contentPanel.setBorder(border);
    contentPanel.setPreferredSize(new Dimension(620, 380));
    contentPanel.setMinimumSize(new Dimension(620, 380));

    statusPanel.setBackground(DarkColor);
    statusPanel.setBorder(border);
    statusPanel.setPreferredSize(new Dimension(620, 24));
    statusPanel.setMinimumSize(new Dimension(620, 24));
    statusPanel.setLayout(new BorderLayout());

    this.getContentPane().setLayout(borderLayout);
    this.getContentPane().add(toolBar, BorderLayout.NORTH);
    this.getContentPane().add(actionPanel, BorderLayout.EAST);
    this.getContentPane().add(contentPanel, BorderLayout.CENTER);
    this.getContentPane().add(statusPanel, BorderLayout.SOUTH);

    //�����ͼ
    contentPanel.add(ui);

    //����Ʒ�
    actionPanel.add(score);

    //���뿪ʼ��ť
    actionPanel.add(startButton);
    startButton.setBorder(emptyBorder);
    startButton.setIcon(imgStart);
    startButton.addActionListener(this);

    //����ˢ�°�ť
    actionPanel.add(refreshButton);
    refreshButton.setBorder(emptyBorder);
    refreshButton.setIcon(imgRefresh);
    refreshButton.addActionListener(this);

    //������ʾ��ť
    actionPanel.add(hintButton);
    hintButton.setBorder(emptyBorder);
    hintButton.setIcon(imgHint);
    hintButton.addActionListener(this);

    //����ը����ť
    actionPanel.add(bombButton);
    bombButton.setBorder(emptyBorder);
    bombButton.setIcon(imgBomb);
    bombButton.addActionListener(this);

    //�����Զ���ʾ
    actionPanel.add(demoButton);
    demoButton.setBorder(emptyBorder);
    demoButton.setIcon(imgDemo);
    demoButton.addActionListener(this);

    //��������
    toolBar.add(setupButton);
    setupButton.setBorder(emptyBorder);
    setupButton.setIcon(imgSetup);
    setupButton.addActionListener(this);

    //�������
    toolBar.add(helpButton);
    helpButton.setBorder(emptyBorder);
    helpButton.setIcon(imgHelp);
    helpButton.addActionListener(this);

    //�������
    toolBar.add(aboutButton);
    aboutButton.setBorder(emptyBorder);
    aboutButton.setIcon(imgAbout);
    aboutButton.addActionListener(this);

    //����ʱ��
    actionPanel.add(clock);

    //����״̬��
    statusPanel.add(statusField, BorderLayout.CENTER);
    statusField.setBorder(unSelected);
    statusField.setEditable(false);
    statusField.setForeground(Color.white);
    statusField.setBackground(DarkColor);

    //���뷢�Ͱ�ť
    statusPanel.add(goTop10, BorderLayout.EAST);
    goTop10.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    goTop10.setForeground(Color.white);
    goTop10.setBackground(DarkColor);
    goTop10.setFont(new Font("Serif", Font.PLAIN, 11));
    goTop10.addActionListener(this);
    goTop10.setEnabled(false);
  }

  public static void showHint(String str) {
    statusField.setText(str);
  }

  /**
   * ͨ���������ļ������ͼ��
   * @param filename ����ͼ����ļ���
   * @return ͼ��
   */
  Image getImage(String filename) {
    URLClassLoader urlLoader = (URLClassLoader)this.getClass().
        getClassLoader();
    URL url = null;
    Image image = null;
    url = urlLoader.findResource(filename);
    image = Toolkit.getDefaultToolkit().getImage(url);
    MediaTracker mediatracker = new MediaTracker(this);
    try {
      mediatracker.addImage(image, 0);
      mediatracker.waitForID(0);
    }
    catch (InterruptedException _ex) {
      image = null;
    }
    if (mediatracker.isErrorID(0)) {
      image = null;
    }

    return image;
  }

  public void actionPerformed(ActionEvent e) {
    Object obj = e.getSource();
    if (obj == startButton) { //��ʼ
      map = new Map(Setting.Level[setting.LevelIndex]);
      ui.setMap(map);
      ui.start();
      clock.start();
      score.setScore( -1, 0);
    }
    else if (obj == refreshButton) { //ˢ��
      ui.refresh();
    }
    else if (obj == hintButton) { //��ʾ
      ui.findNext(new Point( -1, -1));
    }
    else if (obj == bombButton) { //ը��
      ui.bomb(new Point( -1, -1), true);
    }
    else if (obj == demoButton) { //�Զ���ʾ
      ui.autoPlay();
    }
    else if (obj == aboutButton) { //����
      aboutDialog.show();
    }
    else if (obj == helpButton) { //����
      //new Help();
      helpDialog.show();
    }
    else if (obj == setupButton) { //����
      SetupDialog setupDialog = new SetupDialog(this); //���öԻ���
      setupDialog.show();

      if (setting.Music == 1) {
        music.play();
      }
      else {
        music.stop();
      }

      animateDelete.setSpeed(setting.Animate);
    }
    else if (obj == goTop10) { //����
      String name = JOptionPane.showInputDialog(this, "please enter your nickname:", "���˷���ȥ");
      if (!"".equals(name.trim())) { //�����������
        new Top10(this, "nickname=" + name + "&" + ui.encode());
        goTop10.setEnabled(false);
      }
    }
  }

  public static void main(String args[]) {
    JFrame.setDefaultLookAndFeelDecorated(true);
    JDialog.setDefaultLookAndFeelDecorated(true);
    Kyodai kyodai = new Kyodai();
    kyodai.show();
  }
}
