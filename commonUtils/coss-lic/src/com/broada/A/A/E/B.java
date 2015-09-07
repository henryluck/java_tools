package com.broada.A.A.E;

import com.broada.A.A.C.A;
import com.broada.A.A.C.D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public final class B extends JPanel
{
  private static final long serialVersionUID = 5472645561615313253L;
  private JLabel C = new JLabel();
  private JTextField B = new JTextField();
  private JButton A = new JButton();

  public B()
  {
    try
    {
      B();
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  private void B()
    throws Exception
  {
    setLayout(null);
    this.C.setText("请选择License文件:");
    this.C.setBounds(new Rectangle(11, 10, 200, 20));
    this.B.setText("");
    this.B.setBounds(new Rectangle(11, 47, 290, 23));
    this.A.setBounds(new Rectangle(305, 46, 66, 23));
    this.A.setText("浏览");
    this.A.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramActionEvent)
      {
        B.this.A(paramActionEvent);
      }
    });
    add(this.C);
    add(this.B);
    add(this.A);
  }

  public void A(ActionEvent paramActionEvent)
  {
    File localFile = new File(".");
    if (A().length() > 0)
      localFile = new File(A());
    JFileChooser localJFileChooser = new JFileChooser(localFile);
    localJFileChooser.setFileSelectionMode(0);
    localJFileChooser.setAcceptAllFileFilterUsed(false);
    localJFileChooser.addChoosableFileFilter(new com.broada.A.A.A.B("lic", "授权许可文件（*.lic）"));
    int i = localJFileChooser.showOpenDialog(this);
    if (i == 0)
    {
      String str = localJFileChooser.getSelectedFile().getPath();
      this.B.setText(str);
    }
  }

  private String A()
  {
    return this.B.getText().trim();
  }

  public com.broada.A.A.D.B C()
  {
    String str1 = A();
    if (str1.length() <= 0)
    {
      A("请先输入或选择要导入的License文件！");
      return null;
    }
    File localFile = new File(str1);
    if (!localFile.exists())
    {
      A("文件\"" + str1 + "\"不存在，请重新输入或选择！");
      return null;
    }
    com.broada.A.A.D.B localB = null;
    try
    {
      String str2 = com.broada.A.A.A.C.B(str1);
      localB = com.broada.A.A.A.C.G(str2);
      if (localB == null)
      {
        A("该文件不是有效的产品License文件，请重新输入或选择！");
        return null;
      }
      String str3 = com.broada.A.A.A.C.A(localB);
      if (str3 != null)
      {
        A("无效的License：" + str3 + "。");
        return null;
      }
      com.broada.A.A.D.G localG = localB.B("CARRIER").B();
      if (localG.R())
      {
        int i = JOptionPane.showConfirmDialog(this, "您导入的License只是试用版的授权,您确认导入吗？", "提问", 2);
        if (i != 0)
          return null;
      }
      com.broada.A.A.A.C.A(str2);
      A.B().A(new Date());
      A.B().B(new Date());
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      A("文件\"" + str1 + "\"不存在，请重新输入或选择！", localFileNotFoundException);
      return null;
    }
    catch (IOException localIOException)
    {
      A("写入License文件失败,请重试！", localIOException);
      return null;
    }
    catch (Exception localException)
    {
      A(localException.getMessage(), localException);
      return null;
    }
    return localB;
  }

  private void A(String paramString)
  {
    JOptionPane.showMessageDialog(this, paramString, "错误", 0);
    this.B.selectAll();
    this.B.requestFocus();
  }

  private void A(String paramString, Exception paramException)
  {
    com.broada.A.A.A.G.A(this, "错误", paramString, paramException).show();
    this.B.selectAll();
    this.B.requestFocus();
  }
}

/* Location:           C:\Users\mike\Desktop\platform.common.l4c.jar
 * Qualified Name:     com.broada.A.A.E.B
 * JD-Core Version:    0.6.0
 */