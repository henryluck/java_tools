/*    */ package org.zeroturnaround.jrebel.mybatis.cbp;
/*    */ 
/*    */ import org.zeroturnaround.bundled.javassist.ClassPool;
/*    */ import org.zeroturnaround.bundled.javassist.CtClass;
/*    */ import org.zeroturnaround.bundled.javassist.CtMethod;
/*    */ import org.zeroturnaround.javarebel.integration.support.JavassistClassBytecodeProcessor;
/*    */ import org.zeroturnaround.jrebel.mybatis.ResourceContext;
/*    */ 
/*    */ public class ResourcesCBP extends JavassistClassBytecodeProcessor
/*    */ {
/*    */   public void process(ClassPool cp, ClassLoader cl, CtClass ctClass)
/*    */     throws Exception
/*    */   {
/* 16 */     cp.importPackage("java.net");
/*    */ 
/* 18 */     ctClass.getDeclaredMethod("getResourceURL", cp.get(new String[] { ClassLoader.class.getName(), String.class.getName() })).insertAfter("if ($_ != null) {  " + ResourceContext.class
/* 20 */       .getName() + ".addUrl($_);" + "}");
/*    */ 
/* 24 */     ctClass.getDeclaredMethod("getResourceAsStream", cp.get(new String[] { ClassLoader.class.getName(), String.class.getName() })).insertAfter("if ($_ != null) {  getResourceURL($1, $2);}");
/*    */ 
/* 30 */     ctClass.getDeclaredMethod("getUrlAsStream", cp.get(new String[] { String.class.getName() })).insertAfter("if ($_ != null) {  " + ResourceContext.class
/* 32 */       .getName() + ".addUrl(new URL($1));" + "}");
/*    */   }
/*    */ }

/* Location:           F:\eclipse-jee-juno-SR2-win32\plugins\org.zeroturnaround.eclipse.embedder_5.3.1.RELEASE-201307081557\jrebel\mybatis-jr-plugin-5.6.3a.jar
 * Qualified Name:     org.zeroturnaround.jrebel.mybatis.cbp.ResourcesCBP
 * JD-Core Version:    0.6.0
 */