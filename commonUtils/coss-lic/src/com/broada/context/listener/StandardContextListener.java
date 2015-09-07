package com.broada.context.listener;

import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.core.StandardContext;

public class StandardContextListener
  implements LifecycleListener
{

  public void lifecycleEvent(final LifecycleEvent event) {
    if ("stop".equals(event.getType())) {
      Lifecycle ctx = event.getLifecycle();
      if ((ctx instanceof StandardContext)) {
        StandardContext sct = (StandardContext)ctx;
        System.out.println("WEB应用[" + sct.getDisplayName() + "]上下文初始化失败,系统自动退出,hack了，不会退出");
      }
    }
  }
}