package com.broada.A.A.A;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public final class B extends FileFilter
{
  private String B = "";
  private String A = "";

  public B(String paramString1, String paramString2)
  {
    this.B = paramString1;
    this.A = paramString2;
  }

  public boolean accept(File paramFile)
  {
    return (paramFile.isDirectory()) || (this.B.equals(A(paramFile)));
  }

  public String getDescription()
  {
    return this.A;
  }

  private String A(File paramFile)
  {
    String str1 = paramFile.getPath();
    String str2 = null;
    int i = str1.lastIndexOf('.');
    if ((i > 0) && (i < str1.length() - 1))
      str2 = str1.substring(i + 1).toLowerCase();
    return str2;
  }
}

/* Location:           C:\Users\mike\Desktop\platform.common.l4c.jar
 * Qualified Name:     com.broada.A.A.A.B
 * JD-Core Version:    0.6.0
 */