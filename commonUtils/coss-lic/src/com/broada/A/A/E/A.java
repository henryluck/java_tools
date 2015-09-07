package com.broada.A.A.E;

import com.broada.A.A.A.E;
import com.broada.A.A.A.F;
import com.broada.A.A.C.B;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class A extends JDialog
{
  private static final Log Q = LogFactory.getLog(A.class);
  private static final long serialVersionUID = -8950570307627231179L;
  private JLabel I = new JLabel();
  private JLabel A = new JLabel();
  private JLabel O = new JLabel();
  private JTextArea J = new JTextArea();
  private JTextField C = new JTextField();
  private JScrollPane H = new JScrollPane();
  private JPanel G = new JPanel();
  private JPanel N = new JPanel();
  private JPanel M = new JPanel();
  private JButton E = new JButton();
  private JButton B = new JButton();
  private JButton L = new JButton();
  private boolean P = false;
  private String K = "";
  private boolean D = false;
  private B F = com.broada.A.A.C.A.D();

  private A(Frame paramFrame, String paramString, boolean paramBoolean)
  {
    super(paramFrame, paramString, paramBoolean);
    A();
  }

  private A(Dialog paramDialog, String paramString, boolean paramBoolean)
  {
    super(paramDialog, paramString, paramBoolean);
    A();
  }

  public static A A(Component paramComponent)
  {
    return A(paramComponent, "许可申请号获取", false);
  }

  public static A A(Component paramComponent, boolean paramBoolean)
  {
    A localA = A(paramComponent, "许可申请号获取", true);
    localA.D = paramBoolean;
    return localA;
  }

  public static A A(Component paramComponent, String paramString, boolean paramBoolean)
  {
    Window localWindow = F.A(paramComponent);
    A localA = null;
    if ((localWindow instanceof Frame))
      localA = new A((Frame)localWindow, paramString, paramBoolean);
    else
      localA = new A((Dialog)localWindow, paramString, paramBoolean);
    localA.setSize(480, 410);
    F.A(localA);
    return localA;
  }

  private void A()
  {
    C();
    E();
    this.K = this.F.A();
  }

  private void C()
  {
    getContentPane().setLayout(new BorderLayout());
    this.I.setText("客户名称");
    this.I.setBounds(new Rectangle(46, 35, 52, 26));
    this.C.setText("");
    this.C.setBounds(new Rectangle(110, 37, 286, 25));
    this.A.setBounds(new Rectangle(32, 75, 70, 32));
    this.A.setText("许可申请号");
    this.J.setText("");
    this.J.setEditable(false);
    this.J.setLineWrap(true);
    this.J.setToolTipText("拷贝许可申请号时请使用快捷键：Ctrl+C");
    this.H.setBounds(new Rectangle(110, 85, 286, 119));
    this.H.getViewport().add(this.J, null);
    this.H.setVerticalScrollBarPolicy(20);
    this.H.setHorizontalScrollBarPolicy(31);
    this.O.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;许可申请号是系统根据主机硬件设备信息、产品信息和客户的名称生成的一段文本，这段文本主要用来生成正式的许可文件，您只要把这段文本保存为文件，然后发送给公司即可。<br></html>");
    this.G.setBorder(new TitledBorder("操作说明"));
    this.G.setLayout(new BorderLayout());
    this.G.add(this.O, "Center");
    this.N.setLayout(null);
    this.N.add(this.C, null);
    this.N.add(this.I, null);
    this.N.add(this.A, null);
    this.N.add(this.H, null);
    this.N.setPreferredSize(new Dimension(10, 210));
    this.E.setBounds(new Rectangle(166, 10, 115, 25));
    this.E.setText("申请号另存为");
    this.B.setBounds(new Rectangle(56, 10, 100, 25));
    this.B.setText("保存名称");
    this.L.setBounds(new Rectangle(284, 10, 80, 25));
    this.L.setText("退出");
    this.M.setBorder(BorderFactory.createEtchedBorder());
    this.M.setLayout(null);
    this.M.setPreferredSize(new Dimension(420, 45));
    this.M.add(this.E, null);
    this.M.add(this.B, null);
    this.M.add(this.L, null);
    this.E.setEnabled(false);
    this.B.setEnabled(false);
    getContentPane().add(this.N, "North");
    getContentPane().add(this.G, "Center");
    getContentPane().add(this.M, "South");
  }

  private void E()
  {
    A localA = this;
    this.C.addKeyListener(new KeyAdapter(localA)
    {
      public void keyReleased(KeyEvent paramKeyEvent)
      {
        A.A(A.this, this.B);
      }
    });
    this.C.addMouseListener(new MouseAdapter(localA)
    {
      public void mouseReleased(MouseEvent paramMouseEvent)
      {
        A.A(A.this, this.B);
      }
    });
    this.E.addActionListener(new ActionListener(localA)
    {
      public void actionPerformed(ActionEvent paramActionEvent)
      {
        JFileChooser localJFileChooser = new JFileChooser();
        localJFileChooser.setFileSelectionMode(0);
        localJFileChooser.setAcceptAllFileFilterUsed(false);
        localJFileChooser.addChoosableFileFilter(new A._A(A.this));
        int i = localJFileChooser.showSaveDialog(this.B);
        if (i == 0)
        {
          boolean bool = false;
          try
          {
            bool = A.A(A.this).A(A.D(A.this).getText().trim());
          }
          catch (Exception localException1)
          {
            JOptionPane.showMessageDialog(A.this, "保存客户名称错误:" + localException1.getMessage(), "错误", 0);
            return;
          }
          if (!bool)
          {
            JOptionPane.showMessageDialog(A.this, "保存客户名称失败。", "错误", 0);
            return;
          }
          A.C(A.this).setEnabled(false);
          try
          {
            A.A(A.this, localJFileChooser, localJFileChooser.getSelectedFile());
          }
          catch (Exception localException2)
          {
            JOptionPane.showMessageDialog(A.this, "保存文件出错：" + localException2.getMessage(), "错误", 0);
          }
          A.A(A.this, true);
        }
      }
    });
    this.B.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramActionEvent)
      {
        boolean bool = false;
        try
        {
          bool = A.A(A.this).A(A.D(A.this).getText().trim());
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
          JOptionPane.showMessageDialog(A.this, "保存客户名称错误:" + localException.getMessage(), "错误", 0);
          return;
        }
        if (!bool)
        {
          JOptionPane.showMessageDialog(A.this, "保存客户名称失败。", "错误", 0);
          return;
        }
        A.C(A.this).setEnabled(false);
      }
    });
    this.L.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramActionEvent)
      {
        A.B(A.this);
      }
    });
    addWindowListener(new WindowAdapter()
    {
      public void windowClosing(WindowEvent paramWindowEvent)
      {
        A.B(A.this);
      }
    });
  }

  public String A(File paramFile)
  {
    String str1 = paramFile.getPath();
    String str2 = null;
    int i = str1.lastIndexOf('.');
    if ((i > 0) && (i < str1.length() - 1))
      str2 = str1.substring(i + 1).toLowerCase();
    return str2;
  }

  private void B(Component paramComponent)
  {
    String str1 = this.C.getText().trim();
    if (E.H(str1))
    {
      this.J.setText("");
      this.E.setEnabled(false);
      this.B.setEnabled(false);
    }
    else
    {
      com.broada.A.A.D.A localA = this.F.A(str1, this.K);
      String str2 = "";
      try
      {
        str2 = localA.G();
      }
      catch (Exception localException)
      {
        Q.error(String.format("解析申请信息失败。错误：", new Object[0]), localException);
        JOptionPane.showMessageDialog(paramComponent, "解析申请信息失败，原因：" + localException.getMessage(), "错误", 0);
        return;
      }
      this.J.setText(str2);
      this.E.setEnabled(true);
      this.B.setEnabled(true);
    }
  }

  private void A(Component paramComponent, File paramFile)
    throws Exception
  {
    if (!"req".equals(A(paramFile)))
      paramFile = new File(paramFile.getAbsolutePath() + ".req");
    if (paramFile.exists())
    {
      int i = JOptionPane.showConfirmDialog(paramComponent, "文件已经存在，确定覆盖？", "确认", 0, 3);
      if (i == 1)
        return;
    }
    else
    {
      localObject = paramFile.getParentFile();
      ((File)localObject).mkdirs();
    }
    paramFile.createNewFile();
    Object localObject = new FileWriter(paramFile);
    ((FileWriter)localObject).write(this.J.getText());
    ((FileWriter)localObject).close();
    JOptionPane.showMessageDialog(this, "许可申请号文件导出成功！", "提示", 1);
  }

  private void D()
  {
    if (this.D)
      setVisible(false);
    else
      System.exit(0);
  }

  public boolean B()
  {
    return this.P;
  }

  public static void A(String[] paramArrayOfString)
  {
    A localA = A(null);
    localA.setVisible(true);
  }

  class _A extends FileFilter
  {
    public _A()
    {
    }

    public boolean accept(File paramFile)
    {
      return (paramFile.isDirectory()) || ("req".equals(A.this.A(paramFile)));
    }

    public String getDescription()
    {
      return "许可申请号存储文件（*.req）";
    }
  }
}

/* Location:           C:\Users\mike\Desktop\platform.common.l4c.jar
 * Qualified Name:     com.broada.A.A.E.A
 * JD-Core Version:    0.6.0
 */