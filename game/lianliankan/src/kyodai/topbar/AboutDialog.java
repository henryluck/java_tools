package kyodai.topbar;

import java.awt.*;
import javax.swing.border.*;
import java.net.*;
import javax.swing.text.html.*;
import javax.swing.event.*;
import javax.swing.*;
import java.awt.event.*;


/**
 * <p>Title: Kyodai</p>
 * <p>Description: 连连看JAVA版</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: www.wuhantech.com</p>
 * @author ZhangJian
 * @version 1.0
 */

public class AboutDialog extends JDialog
    implements HyperlinkListener {
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  JTextArea jTextArea = new JTextArea();
  BorderLayout borderLayout1 = new BorderLayout();
  Border border1;
  BorderLayout borderLayout2 = new BorderLayout();
  JLabel MyImage = new JLabel();
  JPanel Panel1 = new JPanel();
  JButton Close = new JButton();
  Border border2;

  public AboutDialog(JFrame frame) throws HeadlessException {
    super(frame, true);
    try {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    this.setLocation( (int) (screenSize.width - 416) / 2,
                     (int) (screenSize.height - 310) / 2);
    this.setResizable(false);
  }

  private void jbInit() throws Exception {
    border1 = BorderFactory.createEmptyBorder(20,25,50,40);
    border2 = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.lightGray,1),BorderFactory.createEmptyBorder(2,10,2,10));
    this.setSize(new Dimension(416, 310));
    this.setTitle("About");
    this.getContentPane().setBackground(Color.white);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    jPanel2.setBackground(Color.white);
    jPanel2.setMinimumSize(new Dimension(260, 28));
    jPanel2.setPreferredSize(new Dimension(260, 28));
    jPanel2.setLayout(borderLayout1);
    jPanel1.setBackground(Color.white);
    jPanel1.setMinimumSize(new Dimension(160, 10));
    jPanel1.setPreferredSize(new Dimension(160, 10));
    jPanel1.setLayout(borderLayout2);
    jTextArea.setBorder(border1);
    jTextArea.setEditable(false);
    jTextArea.setText("连连看 1.0 alpha\n\n张剑  2004.11\n\nleftmoon@163.com\n\n"
                      + "完全仿照QQ游戏中的连连看做的，"
                      + "其实JAVA也能进行桌面开发的：)"
                      + "\n\n注：程序中的一切资源均不属于本人所拥有！");
    jTextArea.setLineWrap(true);
    jTextArea.setForeground(new Color(55, 77, 118));
    this.getContentPane().add(jPanel1, BorderLayout.WEST);
    jPanel1.add(MyImage,  BorderLayout.EAST);
    this.getContentPane().add(jPanel2, BorderLayout.CENTER);
    jPanel2.add(jTextArea, BorderLayout.CENTER);

    URLClassLoader urlLoader = (URLClassLoader)this.getClass().getClassLoader();
    URL url;
    url = urlLoader.findResource("images/me.gif");
    ImageIcon icon = new ImageIcon(url);
    MyImage.setIcon(icon);

    Close.setBackground(Color.white);
    Close.setBorder(border2);
    Close.setActionCommand("jButton1");
    Close.setText("Close");
    Close.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        setVisible(false);
      }
    });
    Panel1.setBackground(Color.white);
    this.getContentPane().add(Panel1,  BorderLayout.SOUTH);
    Panel1.add(Close, null);
  }


  public void hyperlinkUpdate(HyperlinkEvent e) {
    if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
      JEditorPane pane = (JEditorPane) e.getSource();
      if (e instanceof HTMLFrameHyperlinkEvent) {
        HTMLFrameHyperlinkEvent evt = (HTMLFrameHyperlinkEvent) e;
        HTMLDocument doc = (HTMLDocument) pane.getDocument();
        doc.processHTMLFrameHyperlinkEvent(evt);
      }
      else {
        try {
          pane.setPage(e.getURL());
        }
        catch (Throwable t) {
          t.printStackTrace();
        }
      }
    }
  }


}