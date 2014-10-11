package jlx.tools.refreshhouse;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import jlx.util.pop.TipWindow;

public class AlertMgr {
    private Map<String, String> feaMap = null;
    private Point oldP; // 上一次坐标,拖动窗口时用
    private TipWindow tw = null; // 提示框
    private Image img = null;// 图像组件
    private JLabel imgLabel = null; // 背景图片标签
    private JPanel headPan = null;
    private JPanel feaPan = null;
    private JPanel btnPan = null;
    private JLabel title = null;
    private JLabel close = null;// 关闭按钮
    private JTextArea feature = null;
    private JScrollPane jfeaPan = null;
    private JLabel update = null;
    private SimpleDateFormat sdf = null;
    private String url;

    {
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        feaMap = new HashMap<String, String>();
        feaMap.put("name", "名称：");
        feaMap.put("release", sdf.format(new Date()));

    }

    private AlertMgr(final String msg) {
        feaMap.put("feature", msg);
        this.doIt();
    }

    public AlertMgr(final String msg, final Image image,final String url) {
        feaMap.put("feature", msg);
        this.img = image;
        this.url = url;
        this.doIt();
    }

    public void doIt() {
        init();
        handle();
        tw.setAlwaysOnTop(true);
        tw.setUndecorated(true);
        tw.setResizable(false);
        tw.setVisible(true);
        tw.run();
    }

    /**
     * 产生提醒窗口
     * 
     * @param msg 提醒的文字
     */
    public static void pop(final String msg) {
        new AlertMgr(msg);
    }

    public static void pop(final String msg, final Image image,final String url) {
        new AlertMgr(msg, image,url);
    }

    public void init() {

        // 新建300x220的消息提示框
        int width = 300;
        int height = 400;
        tw = new TipWindow(width, height);

        // 设置各个面板的布局以及面板中控件的边界
        headPan = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        feaPan = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        btnPan = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        title = new JLabel("提示");
        close = new JLabel(" x");// 关闭按钮
        feature = new JTextArea(feaMap.get("feature"));
        jfeaPan = new JScrollPane(feature);
        update = new JLabel();

        // 将各个面板设置为透明，否则看不到背景图片
        // ((JPanel) tw.getContentPane()).setOpaque(false);
        // headPan.setOpaque(false);
        // feaPan.setOpaque(false);
        // btnPan.setOpaque(false);

        // 设置JDialog的整个背景图片
        // tw.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
        headPan.setPreferredSize(new Dimension(300, 30));

        // 设置提示框的边框,宽度和颜色
        tw.getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray));

        title.setPreferredSize(new Dimension(260, 26));
        title.setVerticalTextPosition(JLabel.CENTER);
        title.setHorizontalTextPosition(JLabel.CENTER);
        title.setFont(new Font("宋体", Font.PLAIN, 12));
        title.setForeground(Color.black);

        close.setFont(new Font("Arial", Font.BOLD, 15));
        close.setPreferredSize(new Dimension(20, 20));
        close.setVerticalTextPosition(JLabel.CENTER);
        close.setHorizontalTextPosition(JLabel.CENTER);
        close.setCursor(new Cursor(12));
        close.setToolTipText("关闭");

        feature.setEditable(false);
        feature.setForeground(Color.red);
        feature.setFont(new Font("宋体", Font.PLAIN, 13));
        feature.setBackground(new Color(184, 230, 172));
        // 设置文本域自动换行
        feature.setLineWrap(true);

        jfeaPan.setPreferredSize(new Dimension(width - 10, 150));
        jfeaPan.setBorder(LineBorder.createBlackLineBorder());
        jfeaPan.setBackground(Color.black);

        // 为了隐藏文本域，帮加个空的JLabel将他挤到下面去
        JLabel jsp = new JLabel();
        jsp.setPreferredSize(new Dimension(300, 25));

        update.setPreferredSize(new Dimension(110, 30));
        // 设置标签鼠标手形
        update.setCursor(new Cursor(12));

        if (img == null) {
            try {
                img = ImageIO.read(getRes("jlx/tools/research/pop/bg_u_all.gif"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Image image = changeImageSize(img);
        ImageIcon icon = new ImageIcon(image);

        imgLabel = new JLabel(icon);
        imgLabel.setBounds(0, 0, icon.getIconHeight(), icon.getIconHeight());
        imgLabel.   setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        imgLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                openURL(url);
            }
        });

        headPan.add(title);
        headPan.add(close);
        // headPan.add(head);

        // feaPan.add(jsp);
        feaPan.add(jfeaPan);
        feaPan.add(imgLabel);

        btnPan.add(update);

        tw.add(headPan, BorderLayout.NORTH);
        tw.add(feaPan, BorderLayout.CENTER);
        tw.add(btnPan, BorderLayout.SOUTH);

    }

    /**
     * {method description}.
     * 
     * @param gs
     * @return
     */
    private BufferedImage changeImageSize(final Image input) {
        // 转换图片大小，创建一个BufferedImage
        int imgWidth = 260;
        int imgHeigth = 260;
        BufferedImage image = new BufferedImage(imgWidth, imgHeigth, BufferedImage.TYPE_3BYTE_BGR);
        // 把图片读到bufferedImage中
        image.getGraphics().drawImage(input, 0, 0, imgWidth, imgHeigth, null);
        return image;
    }

    public void handle() {
        // 为更新按钮增加相应的事件
        update.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                // JOptionPane.showMessageDialog(tw, "谢谢，再见");
                tw.close();
            }

            @Override
            public void mouseEntered(final MouseEvent e) {
                update.setBorder(BorderFactory.createLineBorder(Color.gray));
            }

            @Override
            public void mouseExited(final MouseEvent e) {
                update.setBorder(null);
            }
        });
        // 增加鼠标拖动事件
        title.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(final MouseEvent e) {
                // TODO Auto-generated method stub
                Point newP = new Point(e.getXOnScreen(), e.getYOnScreen());
                int x = tw.getX() + (newP.x - oldP.x);
                int y = tw.getY() + (newP.y - oldP.y);
                tw.setLocation(x, y);
                oldP = newP;

            }
        });
        // 鼠标按下时初始坐标,供拖动时计算用
        title.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(final MouseEvent e) {
                oldP = new Point(e.getXOnScreen(), e.getYOnScreen());
            }
        });

        // 右上角关闭按钮事件
        close.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                tw.close();
            }

            @Override
            public void mouseEntered(final MouseEvent e) {
                close.setBorder(BorderFactory.createLineBorder(Color.gray));
            }

            @Override
            public void mouseExited(final MouseEvent e) {
                close.setBorder(null);
            }
        });

    }

    public URL getRes(final String str) {
        return this.getClass().getClassLoader().getResource(str);
    }

    private void openURL(final String url) {
        if (java.awt.Desktop.isDesktopSupported()) {
            try {
                // 创建一个URI实例
                java.net.URI uri = java.net.URI.create(url);
                // 获取当前系统桌面扩展
                java.awt.Desktop dp = java.awt.Desktop.getDesktop();
                // 判断系统桌面是否支持要执行的功能
                if (dp.isSupported(java.awt.Desktop.Action.BROWSE)) {
                    // 获取系统默认浏览器打开链接
                    dp.browse(uri);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(final String args[]) throws IOException {
        BufferedImage image = ImageIO.read(new File("g:/temp/test.jpg"));
        AlertMgr.pop("1.公司1\n2.公司2\n3.公司3\n4.公司4\n", image,"http://www.baidu.com");
    }

}
