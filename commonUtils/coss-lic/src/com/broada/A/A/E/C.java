package com.broada.A.A.E;

import com.broada.A.A.A.F;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public final class C extends JDialog
{
  private static final long serialVersionUID = 8636471044574787938L;
  private boolean F = false;
  private B C = new B();
  private BorderLayout H = new BorderLayout();
  private JPanel D = new JPanel();
  private JButton I = new JButton();
  private JButton B = new JButton();
  private JButton G = new JButton();
  private boolean A = false;
  private JLabel E = new JLabel();

  private C(Frame paramFrame, String paramString, boolean paramBoolean)
  {
    super(paramFrame, paramString, paramBoolean);
    C();
  }

  private C(Dialog paramDialog, String paramString, boolean paramBoolean)
  {
    super(paramDialog, paramString, paramBoolean);
    C();
  }

  public void C()
  {
    try
    {
      B();
      F.A(this);
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public static C A(Component paramComponent, String paramString, boolean paramBoolean)
  {
    Window localWindow = F.A(paramComponent);
    C localC = null;
    if ((localWindow instanceof Frame))
      localC = new C((Frame)localWindow, paramString, true);
    else
      localC = new C((Dialog)localWindow, paramString, true);
    localC.F = paramBoolean;
    localC.G.setVisible(true);
    if (paramBoolean)
      localC.B.setToolTipText("结束系统的运行");
    return localC;
  }

  public static C A(Component paramComponent, boolean paramBoolean)
  {
    return A(paramComponent, "导入产品License", paramBoolean);
  }

  public static C A(Component paramComponent)
  {
    return A(paramComponent, "导入产品License", false);
  }

  private void B()
    throws Exception
  {
    setSize(new Dimension(450, 195));
    getContentPane().setLayout(this.H);
    this.C.setBorder(BorderFactory.createEtchedBorder());
    this.G.setText("申请License");
    this.G.setVisible(false);
    this.G.setToolTipText("进入申请License画面");
    this.G.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramActionEvent)
      {
        C.this.B(paramActionEvent);
      }
    });
    this.I.setToolTipText("导入License");
    this.I.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramActionEvent)
      {
        C.this.A(paramActionEvent);
      }
    });
    this.B.setToolTipText("暂时不导入");
    this.E.setText("");
    this.E.setVisible(false);
    this.B.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramActionEvent)
      {
        C.this.C(paramActionEvent);
      }
    });
    getContentPane().add(this.C, "Center");
    this.I.setText("确 定");
    this.B.setText("取 消");
    this.D.add(this.G);
    this.D.add(this.I);
    this.D.add(this.B);
    getContentPane().add(this.D, "South");
    getContentPane().add(this.E, "North");
    setDefaultCloseOperation(2);
  }

  protected void processWindowEvent(WindowEvent paramWindowEvent)
  {
    super.processWindowEvent(paramWindowEvent);
    if (paramWindowEvent.getID() == 201)
      C(null);
  }

  public void A(String paramString)
  {
    this.E.setText("<html><br>&nbsp;&nbsp;&nbsp;&nbsp;<font color=#ff0000>" + paramString + "</font></html>");
    this.E.setVisible(true);
  }

  public void C(ActionEvent paramActionEvent)
  {
    if (this.F)
      System.exit(0);
    else
      dispose();
  }

  public void B(ActionEvent paramActionEvent)
  {
    this.A = true;
    A localA = A.A(this, true);
    localA.setVisible(true);
  }

  public void A(ActionEvent paramActionEvent)
  {
    com.broada.A.A.D.B localB = this.C.C();
    if (localB != null)
    {
      com.broada.A.A.C.C.D().B(localB);
      dispose();
    }
  }

  public boolean A()
  {
    return this.A;
  }

  public static void A(String[] paramArrayOfString)
  {
    A(null).setVisible(true);
  }
}

/* Location:           C:\Users\mike\Desktop\platform.common.l4c.jar
 * Qualified Name:     com.broada.A.A.E.C
 * JD-Core Version:    0.6.0
 */