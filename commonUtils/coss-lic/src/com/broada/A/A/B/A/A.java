package com.broada.A.A.B.A;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class A extends SqlMapClientDaoSupport
  implements com.broada.A.A.B.A
{
  public String A()
  {
    return (String)getSqlMapClientTemplate().queryForObject("getValueByKey", "licenseInfo");
  }

  public String B()
  {
    return (String)getSqlMapClientTemplate().queryForObject("getValueByKey", "lastRunTime");
  }

  public String E()
  {
    return (String)getSqlMapClientTemplate().queryForObject("getValueByKey", "firstRunTime");
  }

  public String C()
  {
    return (String)getSqlMapClientTemplate().queryForObject("getValueByKey", "licensee");
  }

  public void C(String paramString)
  {
    String str = A();
    if ((str != null) && (!"".equals(str)))
      getSqlMapClientTemplate().update("updateLicenseInfo", paramString);
    else
      getSqlMapClientTemplate().insert("insertLicenseInfo", paramString);
  }

  public void D(String paramString)
  {
    String str = B();
    if ((str != null) && (!"".equals(str)))
      getSqlMapClientTemplate().update("updateLastRunTime", paramString);
    else
      getSqlMapClientTemplate().insert("insertLastRunTime", paramString);
  }

  public void B(String paramString)
  {
    String str = E();
    if ((str != null) && (!"".equals(str)))
      getSqlMapClientTemplate().update("updateFirstRunTime", paramString);
    else
      getSqlMapClientTemplate().insert("insertFirstRunTime", paramString);
  }

  public void A(String paramString)
  {
    String str = C();
    if ((str != null) && (!"".equals(str)))
      getSqlMapClientTemplate().update("updateLicensee", paramString);
    else
      getSqlMapClientTemplate().insert("insertLicensee", paramString);
  }

  public void D()
  {
    getSqlMapClientTemplate().delete("deleteAll", null);
  }
}

/* Location:           C:\Users\mike\Desktop\platform.common.l4c.jar
 * Qualified Name:     com.broada.A.A.B.A.A
 * JD-Core Version:    0.6.0
 */