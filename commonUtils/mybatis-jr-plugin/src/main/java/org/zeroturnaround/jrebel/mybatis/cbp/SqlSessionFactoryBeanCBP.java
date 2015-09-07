/*    */ package org.zeroturnaround.jrebel.mybatis.cbp;
/*    */ 
/*    */ import org.zeroturnaround.bundled.javassist.CannotCompileException;
/*    */ import org.zeroturnaround.bundled.javassist.ClassPool;
/*    */ import org.zeroturnaround.bundled.javassist.CtClass;
/*    */ import org.zeroturnaround.bundled.javassist.CtMethod;
/*    */ import org.zeroturnaround.bundled.javassist.expr.ExprEditor;
/*    */ import org.zeroturnaround.bundled.javassist.expr.MethodCall;
/*    */ import org.zeroturnaround.bundled.javassist.expr.NewExpr;
/*    */ import org.zeroturnaround.javarebel.integration.support.JavassistClassBytecodeProcessor;
/*    */ import org.zeroturnaround.jrebel.mybatis.JrConfiguration;
/*    */ import org.zeroturnaround.jrebel.mybatis.SqlMapReloader;
/*    */ 
/*    */ public class SqlSessionFactoryBeanCBP extends JavassistClassBytecodeProcessor
/*    */ {
/*    */   public void process(ClassPool cp, ClassLoader cl, CtClass ctClass)
/*    */     throws Exception
/*    */   {
/* 21 */     CtMethod m = ctClass.getDeclaredMethod("buildSqlSessionFactory");
/* 22 */     m.addLocalVariable("__resource", cp.get("org.springframework.core.io.Resource"));
/* 23 */     m.insertBefore("{  __resource = null;}");
/*    */ 
/* 28 */     m.instrument(new ExprEditor()
/*    */     {
/*    */       public void edit(NewExpr e) throws CannotCompileException {
/* 31 */         if ("org.apache.ibatis.builder.xml.XMLMapperBuilder".equals(e.getClassName()))
/* 32 */           e.replace("{  $_ = $proceed($$);  if ($2 instanceof " + JrConfiguration.class
/* 35 */             .getName() + ") {" + "    " + SqlMapReloader.class
/* 36 */             .getName() + " reloader = ((" + JrConfiguration.class.getName() + ") $2).getReloader();" + "    if (reloader != null && __resource != null) {" + "      reloader.addMapping(__resource.getURL(), $3);" + "    }" + "  }" + "}");
/*    */       }
/*    */ 
/*    */       public void edit(MethodCall m)
/*    */         throws CannotCompileException
/*    */       {
/* 47 */         String methodName = m.getMethodName();
/* 48 */         if (("getInputStream".equals(methodName)) && ("org.springframework.core.io.Resource".equals(m.getClassName())))
/* 49 */           m.replace("{  __resource = $0;  $_ = $proceed($$);}");
/*    */       }
/*    */     });
/*    */   }
/*    */ }

/* Location:           F:\eclipse-jee-juno-SR2-win32\plugins\org.zeroturnaround.eclipse.embedder_5.3.1.RELEASE-201307081557\jrebel\mybatis-jr-plugin-5.6.3a.jar
 * Qualified Name:     org.zeroturnaround.jrebel.mybatis.cbp.SqlSessionFactoryBeanCBP
 * JD-Core Version:    0.6.0
 */