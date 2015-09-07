package com.broada.A.A.A;

import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class F
{
  public static void A(Window paramWindow)
  {
    Dimension localDimension1 = paramWindow.getSize();
    Dimension localDimension2 = Toolkit.getDefaultToolkit().getScreenSize();
    Point localPoint1;
    if ((paramWindow.getOwner() != null) && (paramWindow.getOwner().isShowing()))
    {
      Point localPoint2 = paramWindow.getOwner().getLocationOnScreen();
      Dimension localDimension3 = paramWindow.getOwner().getSize();
      localPoint1 = new Point(localPoint2.x + localDimension3.width / 2 - localDimension1.width / 2, localPoint2.y + localDimension3.height / 2 - localDimension1.height / 2);
    }
    else
    {
      localPoint1 = new Point(localDimension2.width / 2 - localDimension1.width / 2, localDimension2.height / 2 - localDimension1.height / 2);
    }
    if (localPoint1.y < 0)
      localPoint1.y = 0;
    if (localPoint1.x > localDimension2.width - localDimension1.width)
      localPoint1.x = (localDimension2.width - localDimension1.width);
    paramWindow.setLocation(localPoint1);
  }

  public static Window A(Component paramComponent)
  {
    if (paramComponent == null)
      return JOptionPane.getRootFrame();
    if (((paramComponent instanceof Frame)) || ((paramComponent instanceof Dialog)))
      return (Window)paramComponent;
    return A(paramComponent.getParent());
  }

  public static boolean A(Component paramComponent1, Component paramComponent2, String paramString)
  {
    JOptionPane localJOptionPane = new JOptionPane(paramComponent2, -1, 2, null, null);
    JDialog localJDialog = localJOptionPane.createDialog(paramComponent1, paramString);
    localJDialog.setBounds(localJDialog.getX(), localJDialog.getY() + 50, localJDialog.getWidth() - 50, localJDialog.getHeight() - 100);
    localJDialog.setModal(true);
    localJDialog.setResizable(true);
    localJDialog.setVisible(true);
    if (localJOptionPane.getValue() == null)
      return false;
    int i = ((Integer)localJOptionPane.getValue()).intValue();
    return i == 0;
  }
}

/* Location:           C:\Users\mike\Desktop\platform.common.l4c.jar
 * Qualified Name:     com.broada.A.A.A.F
 * JD-Core Version:    0.6.0
 */