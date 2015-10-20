package com.jlr.sdd.core.actions;


public class SpxInterceptor extends AbstractInterceptor
{ 
  
  @Override
public String intercept(final ActionInvocation aInvocation)
    throws Exception, AuthenticationException
  {         
      
    return aInvocation.invoke();
  }

}