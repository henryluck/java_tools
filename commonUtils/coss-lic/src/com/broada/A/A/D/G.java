package com.broada.A.A.D;

import com.broada.A.A.C.A;
import com.broada.A.A.C.D;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class G
  implements Serializable
{
  private static final long serialVersionUID = -2610117002154671330L;
  public static final int B = 0;
  public static final int L = 1;
  public static final int I = -1;
  public static final int A = -2;
  private int M = 1;
  private int F = -1;
  private int H = -1;
  private int C = -1;
  private Date G = null;
  private String D = "false";
  private Date E = null;
  private String J;
  private Date K;
  Date N;

  public boolean R()
  {
    return this.M == 1;
  }

  public int C()
  {
    Object localObject = null;
    Date localDate = new Date();
    if (localDate.getTime() < this.E.getTime())
      return -2;
    if ((this.K == null) || (A.A()))
      this.K = A.B().B();
    if ((this.N == null) || (A.A()))
      this.N = A.B().D();
    if ((this.K != null) && (localDate.getTime() < this.K.getTime()))
      localObject = this.K;
    else
      localObject = localDate;
    if ((this.N != null) && (((Date)localObject).getTime() < this.N.getTime()))
      localObject = this.N;
    if (((Date)localObject).getTime() < this.E.getTime())
      return -2;
    float f1 = 0.0F;
    Calendar localCalendar = Calendar.getInstance();
    if (this.G != null)
    {
      localCalendar.setTime((Date)localObject);
      l1 = localCalendar.getTimeInMillis();
      localCalendar.setTime(this.G);
      l2 = localCalendar.getTimeInMillis();
      f1 = (float)(l2 - l1);
      if (f1 < 0.0F)
        return -2;
      f2 = f1 / 86400000.0F + 0.5F;
      return Math.round(f2);
    }
    if (this.C == -1)
      return -1;
    if (this.C == 0)
      return -2;
    localCalendar.setTime(this.E);
    localCalendar.add(5, this.C);
    long l1 = localCalendar.getTimeInMillis();
    localCalendar.setTime((Date)localObject);
    long l2 = localCalendar.getTimeInMillis();
    f1 = (float)(l1 - l2);
    if (f1 < 0.0F)
      return -2;
    float f2 = f1 / 86400000.0F + 0.5F;
    return Math.round(f2);
  }

  public String A(E paramE)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("<authInfo");
    localStringBuffer.append(" authorizedType=\"" + this.M + "\"");
    localStringBuffer.append(" webConcurrClientNum=\"" + this.F + "\"");
    if (paramE != E.F)
      localStringBuffer.append(" userNum=\"" + this.H + "\"");
    if ((this.J != null) && (this.J.trim().length() > 0))
      localStringBuffer.append(" authModuleIds=\"" + this.J + "\"");
    if (this.G != null)
      localStringBuffer.append(" expirationTime=\"" + L() + "\"");
    else
      localStringBuffer.append(" expirationDates=\"" + this.C + "\"");
    localStringBuffer.append(" authorizedTime=\"" + D() + "\"");
    localStringBuffer.append(" isVldMachCode=\"" + this.D + "\"/>");
    return localStringBuffer.toString();
  }

  public String G()
  {
    String str = "\r\n";
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("授权类型：" + K() + "\r\n");
    localStringBuffer.append("WEB并发客户端数：" + V() + "\r\n");
    localStringBuffer.append("有效用户数：" + Q() + "\r\n");
    localStringBuffer.append("授权模块：" + T());
    localStringBuffer.append("有效期限：");
    if (this.G != null)
      localStringBuffer.append(O() + "\r\n");
    else
      localStringBuffer.append(P() + "\r\n");
    localStringBuffer.append("是否校验机器码：" + H());
    return localStringBuffer.toString();
  }

  public int N()
  {
    return this.M;
  }

  public void C(int paramInt)
  {
    this.M = paramInt;
  }

  public int S()
  {
    return this.C;
  }

  public void D(int paramInt)
  {
    this.C = paramInt;
  }

  public Date A()
  {
    return this.G;
  }

  public String L()
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    if (this.G == null)
      return "";
    return localSimpleDateFormat.format(this.G);
  }

  public void A(Date paramDate)
  {
    this.G = paramDate;
  }

  public String F()
  {
    return this.D;
  }

  public boolean M()
  {
    return (E()) || ("MAC".equalsIgnoreCase(this.D));
  }

  public boolean U()
  {
    return (E()) || ("IP".equalsIgnoreCase(this.D));
  }

  public boolean E()
  {
    return Boolean.valueOf(this.D).booleanValue();
  }

  public void B(String paramString)
  {
    this.D = paramString;
  }

  public int J()
  {
    return this.F;
  }

  public void B(int paramInt)
  {
    this.F = paramInt;
  }

  public int I()
  {
    return this.H;
  }

  public void A(int paramInt)
  {
    this.H = paramInt;
  }

  public Date B()
  {
    return this.E;
  }

  public String D()
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    if (this.E == null)
      return "";
    return localSimpleDateFormat.format(this.E);
  }

  public void B(Date paramDate)
  {
    this.E = paramDate;
  }

  public String K()
  {
    if (this.M == 0)
      return "正式授权";
    if (this.M == 1)
      return "试用授权";
    return "无效的授权类型：" + this.M;
  }

  public String V()
  {
    if (this.F == -1)
      return "无限制";
    return this.F + " 个";
  }

  public String Q()
  {
    if (this.H == -1)
      return "无限制";
    return this.H + " 个";
  }

  public String P()
  {
    if (this.C == -1)
      return "无期限";
    return this.C + " 天";
  }

  public String O()
  {
    return "到 " + L() + " 止";
  }

  public String H()
  {
    if (E())
      return "验证所有";
    if ("MAC".equalsIgnoreCase(this.D))
      return "验证MAC地址";
    if ("IP".equalsIgnoreCase(this.D))
      return "验证IP地址";
    return "不验证";
  }

  public String T()
  {
    return this.J;
  }

  public void C(String paramString)
  {
    this.J = paramString;
  }

  public boolean A(String paramString)
  {
    String str = this.J;
    if (str == null)
      return false;
    str = "," + str.trim() + ",";
    return str.indexOf("," + paramString + ",") >= 0;
  }
}

/* Location:           C:\Users\mike\Desktop\platform.common.l4c.jar
 * Qualified Name:     com.broada.A.A.D.G
 * JD-Core Version:    0.6.0
 */