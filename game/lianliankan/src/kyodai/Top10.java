package kyodai;

import java.net.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.text.html.*;
import java.io.*;

/**
 * <p>Title: Kyodai</p>
 * <p>Description: Á¬Á¬¿´JAVA°æ</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: www.wuhantech.com</p>
 * @author ZhangJian
 * @version 1.0
 */

public class Top10
    extends JDialog
    implements HyperlinkListener {
  JScrollPane ScrollPane = new JScrollPane();
  JEditorPane HelpPane = new JEditorPane();
  Border border1;
  JPanel Panel1 = new JPanel();
  JButton Close = new JButton();
  Border border2;
  String param = "";

  public Top10(JFrame frame, String param) {
    super(frame, true);
    this.param = param;
    try {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    this.setLocation( (int) (screenSize.width - 560) / 2,
                     (int) (screenSize.height - 360) / 2);
    this.setResizable(false);
    this.show();
  }

  private void jbInit() throws Exception {
    border2 = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(
        Color.lightGray, 1), BorderFactory.createEmptyBorder(2, 10, 2, 10));
    this.setSize(new Dimension(560, 360));
    this.setTitle("Top10");
    border1 = BorderFactory.createEmptyBorder();

    try {
      URL url = new URL("http://www.xhai.com/kyodai/top10.asp?" + param);
      HelpPane.setPage(new URL("http://www.xhai.com/kyodai/top10.asp"));
    }
    catch (IOException ex) {
    }
    HelpPane.setEditable(false);
    HelpPane.addHyperlinkListener(this);
    ScrollPane.setHorizontalScrollBarPolicy(JScrollPane.
                                            HORIZONTAL_SCROLLBAR_NEVER);
    ScrollPane.setVerticalScrollBarPolicy(JScrollPane.
                                          VERTICAL_SCROLLBAR_AS_NEEDED);
    ScrollPane.setBorder(border1);
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
    this.getContentPane().add(ScrollPane, BorderLayout.CENTER);
    this.getContentPane().add(Panel1, BorderLayout.SOUTH);
    ScrollPane.getViewport().add(HelpPane, null);
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