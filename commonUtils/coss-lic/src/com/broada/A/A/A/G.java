package com.broada.A.A.A;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class G extends JDialog
{
  private static Log K = LogFactory.getLog(G.class);
  private JPanel A = new JPanel();
  private JPanel C = new JPanel();
  private DefaultListModel B = new DefaultListModel();
  private JList G = new JList(this.B);
  private JButton E = new JButton("详细信息");
  private JTextArea F = new JTextArea();
  private boolean I = false;
  private String D = "未知错误";
  private Map<Throwable, String> H = new HashMap();
  private static final String J = "错误";

  public static G A(Frame paramFrame, String paramString1, String paramString2, boolean paramBoolean, Throwable paramThrowable)
  {
    return new G(paramFrame, paramString1, paramBoolean, paramString2, paramThrowable);
  }

  public static G A(Dialog paramDialog, String paramString1, String paramString2, boolean paramBoolean, Throwable paramThrowable)
  {
    return new G(paramDialog, paramString1, paramBoolean, paramString2, paramThrowable);
  }

  public static G A(Component paramComponent, String paramString1, String paramString2, boolean paramBoolean, Throwable paramThrowable)
  {
    K.error(paramString2, paramThrowable);
    Window localWindow = F.A(paramComponent);
    if ((localWindow instanceof Frame))
    {
      localG = new G((Frame)localWindow, paramString1, paramBoolean, paramString2, paramThrowable);
      return localG;
    }
    G localG = new G((Dialog)localWindow, paramString1, paramBoolean, paramString2, paramThrowable);
    return localG;
  }

  public static G A(Component paramComponent, String paramString, boolean paramBoolean, Throwable paramThrowable)
  {
    return A(paramComponent, paramString, "", paramBoolean, paramThrowable);
  }

  public static G A(Component paramComponent, String paramString1, String paramString2, Throwable paramThrowable)
  {
    return A(paramComponent, paramString1, paramString2, false, paramThrowable);
  }

  public static G A(Component paramComponent, boolean paramBoolean, Throwable paramThrowable)
  {
    return A(paramComponent, "错误", "", paramBoolean, paramThrowable);
  }

  public static G A(Component paramComponent, String paramString, Throwable paramThrowable)
  {
    return A(paramComponent, paramString, "", false, paramThrowable);
  }

  private G(Dialog paramDialog, String paramString1, boolean paramBoolean, String paramString2, Throwable paramThrowable)
  {
    super(paramDialog, E.H(paramString1) ? "错误" : paramString1, paramBoolean);
    A(paramString2, paramThrowable);
  }

  private G(Frame paramFrame, String paramString1, boolean paramBoolean, String paramString2, Throwable paramThrowable)
  {
    super(paramFrame, E.H(paramString1) ? "错误" : paramString1, paramBoolean);
    A(paramString2, paramThrowable);
  }

  @Deprecated
  public G(Frame paramFrame, String paramString, boolean paramBoolean, Throwable paramThrowable)
  {
    this(paramFrame, paramString, paramBoolean, "", paramThrowable);
  }

  @Deprecated
  public G(Dialog paramDialog, String paramString, boolean paramBoolean, Throwable paramThrowable)
  {
    this(paramDialog, paramString, paramBoolean, "", paramThrowable);
  }

  @Deprecated
  public G(Frame paramFrame, boolean paramBoolean, Throwable paramThrowable)
  {
    this(paramFrame, "错误", paramBoolean, "", paramThrowable);
  }

  @Deprecated
  public G(Dialog paramDialog, boolean paramBoolean, Throwable paramThrowable)
  {
    this(paramDialog, "错误", paramBoolean, "", paramThrowable);
  }

  private void A(String paramString, Throwable paramThrowable)
  {
    while (paramThrowable != null)
    {
      this.H.put(paramThrowable, null);
      this.B.addElement(paramThrowable);
      paramThrowable = paramThrowable.getCause();
    }
    if (!E.H(paramString))
      this.D = paramString;
    else
      for (int i = 0; i < this.B.getSize(); i++)
      {
        Throwable localThrowable = (Throwable)this.B.get(i);
        if (E.H(localThrowable.getMessage()))
          continue;
        this.D = localThrowable.getMessage();
        break;
      }
    A();
  }

  private void A()
  {
    setSize(new Dimension(450, 160));
    getContentPane().setLayout(new BorderLayout(10, 10));
    this.A.setSize(new Dimension(450, 140));
    this.A.setLayout(new BorderLayout(10, 10));
    getContentPane().add(this.A, "Center");
    JPanel localJPanel1 = new JPanel();
    localJPanel1.setPreferredSize(new Dimension(0, 0));
    localJPanel1.setLayout(new GridBagLayout());
    localJPanel1.setOpaque(true);
    this.A.add(localJPanel1, "Center");
    JLabel localJLabel = new JLabel();
    localJLabel.setVerticalAlignment(1);
    localJLabel.setHorizontalAlignment(0);
    localJLabel.setIcon(new ImageIcon(G.class.getResource("/com/broada/swing/util/error.gif")));
    localJPanel1.add(localJLabel, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 10, 3, new Insets(16, 10, 20, 8), 9, 137));
    JTextArea localJTextArea = new JTextArea(this.D);
    localJTextArea.setEditable(false);
    localJTextArea.setLineWrap(true);
    localJTextArea.setBackground(UIManager.getColor("Panel.background"));
    localJPanel1.add(localJTextArea, new GridBagConstraints(1, 0, 1, 1, 1.0D, 1.0D, 10, 1, new Insets(18, 10, 16, 16), 260, 83));
    JPanel localJPanel2 = new JPanel();
    this.A.add(localJPanel2, "South");
    localJPanel2.setBorder(BorderFactory.createEtchedBorder());
    localJPanel2.setLayout(new BorderLayout(10, 10));
    JPanel localJPanel3 = new JPanel(new FlowLayout(0));
    localJPanel3.add(this.E);
    localJPanel2.add(localJPanel3, "West");
    this.E.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramActionEvent)
      {
        G.A(G.this, paramActionEvent);
      }
    });
    JButton localJButton = new JButton("确定");
    JPanel localJPanel4 = new JPanel(new FlowLayout(2));
    localJPanel4.add(localJButton);
    localJPanel2.add(localJPanel4, "East");
    localJPanel2.add(localJPanel4);
    localJButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramActionEvent)
      {
        G.this.dispose();
      }
    });
    if (this.B.size() <= 0)
    {
      this.E.setEnabled(false);
      return;
    }
    this.C.setLayout(new BorderLayout(10, 10));
    this.G.addListSelectionListener(new ListSelectionListener()
    {
      public void valueChanged(ListSelectionEvent paramListSelectionEvent)
      {
        G.A(G.this, paramListSelectionEvent);
      }
    });
    this.G.setCellRenderer(new DefaultListCellRenderer()
    {
      public Component getListCellRendererComponent(JList paramJList, Object paramObject, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
      {
        Component localComponent = super.getListCellRendererComponent(paramJList, paramObject, paramInt, paramBoolean1, paramBoolean2);
        Throwable localThrowable = (Throwable)paramObject;
        if (E.H(localThrowable.getMessage()))
          setText("[异常信息为空]");
        else
          setText(localThrowable.getMessage());
        return localComponent;
      }
    });
    this.G.setSelectionMode(0);
    this.G.setSelectedIndex(0);
    this.G.setBackground(UIManager.getColor("Panel.background"));
    JScrollPane localJScrollPane1 = new JScrollPane(this.G);
    localJScrollPane1.setBorder(BorderFactory.createLoweredBevelBorder());
    localJScrollPane1.setHorizontalScrollBarPolicy(32);
    localJScrollPane1.setVerticalScrollBarPolicy(22);
    localJScrollPane1.setPreferredSize(new Dimension(430, 100));
    this.F.setEditable(false);
    this.F.setBackground(UIManager.getColor("Panel.background"));
    JScrollPane localJScrollPane2 = new JScrollPane(this.F);
    localJScrollPane2.setBorder(BorderFactory.createLoweredBevelBorder());
    localJScrollPane2.setHorizontalScrollBarPolicy(32);
    localJScrollPane2.setVerticalScrollBarPolicy(22);
    localJScrollPane2.setPreferredSize(new Dimension(430, 180));
    this.C.add(localJScrollPane1, "North");
    this.C.add(localJScrollPane2, "Center");
    this.C.setPreferredSize(new Dimension(450, 350));
    this.C.setSize(this.C.getPreferredSize());
    F.A(this);
  }

  private void A(ActionEvent paramActionEvent)
  {
    if (!this.I)
    {
      this.I = true;
      this.E.setText("主要信息");
      setSize(getWidth(), getHeight() + (int)this.C.getSize().getHeight());
      this.A.setPreferredSize(this.A.getSize());
      getContentPane().add(this.A, "North");
      getContentPane().add(this.C, "Center");
    }
    else
    {
      this.I = false;
      this.E.setText("详细信息");
      this.C.setSize(this.C.getWidth(), this.C.getHeight() + 10);
      setSize(getWidth(), getHeight() - (int)this.C.getSize().getHeight());
      this.A.setPreferredSize(this.A.getSize());
      getContentPane().removeAll();
      getContentPane().add(this.A, "Center");
    }
    validate();
    repaint();
  }

  private void A(ListSelectionEvent paramListSelectionEvent)
  {
    Object localObject = this.G.getSelectedValue();
    String str = "";
    if ((localObject != null) && ((localObject instanceof Throwable)))
    {
      str = (String)this.H.get(localObject);
      if (str == null)
      {
        Throwable localThrowable = (Throwable)localObject;
        StringBuilder localStringBuilder = new StringBuilder(localThrowable.getClass().getName() + ": " + localThrowable.getMessage());
        for (StackTraceElement localStackTraceElement : localThrowable.getStackTrace())
          localStringBuilder.append("\n\tat ").append(localStackTraceElement);
        str = localStringBuilder.toString();
      }
    }
    this.F.setText(str);
  }

  public static void A(String[] paramArrayOfString)
  {
    new G(new JDialog(), false, new Exception("", new Exception("异常层次2的Msg"))).show();
  }
}

/* Location:           C:\Users\mike\Desktop\platform.common.l4c.jar
 * Qualified Name:     com.broada.A.A.A.G
 * JD-Core Version:    0.6.0
 */