package com.broada._abc_;

import com.broada.A.A.A.I;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public final class lml
  implements ServletContextListener
{
  private static final I A = new I();

  public void contextDestroyed(ServletContextEvent paramServletContextEvent)
  {
    A.contextDestroyed(paramServletContextEvent);
  }

  public void contextInitialized(ServletContextEvent paramServletContextEvent)
  {
    A.contextInitialized(paramServletContextEvent);
  }
}

/* Location:           C:\Users\mike\Desktop\platform.common.l4c.jar
 * Qualified Name:     com.broada._abc_.lml
 * JD-Core Version:    0.6.0
 */