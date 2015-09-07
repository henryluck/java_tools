/*    */ package org.zeroturnaround.jrebel.mybatis.cbp;
/*    */ 
/*    */ import org.zeroturnaround.bundled.javassist.CannotCompileException;
/*    */ import org.zeroturnaround.bundled.javassist.ClassPool;
/*    */ import org.zeroturnaround.bundled.javassist.CtClass;
/*    */ import org.zeroturnaround.bundled.javassist.CtMethod;
/*    */ import org.zeroturnaround.bundled.javassist.expr.ExprEditor;
/*    */ import org.zeroturnaround.bundled.javassist.expr.MethodCall;
/*    */ import org.zeroturnaround.javarebel.integration.support.JavassistClassBytecodeProcessor;
/*    */ import org.zeroturnaround.jrebel.mybatis.SqlMapReloader;
/*    */ 
/*    */ public class StrictMapCBP extends JavassistClassBytecodeProcessor
/*    */ {
/*    */   public void process(ClassPool cp, ClassLoader cl, CtClass ctClass)
/*    */     throws Exception
/*    */   {
/* 19 */     ctClass.getDeclaredMethod("put").instrument(new ExprEditor() {
/*    */       public void edit(MethodCall m) throws CannotCompileException {
/* 21 */         if ("containsKey".equals(m.getMethodName())) {
/* 22 */           m.replace("{  if (" + SqlMapReloader.class
/* 24 */             .getName() + ".isReloading())" + "    $_ = false;" + "  else" + "    $_ = $proceed($$);" + "}");
/*    */         }
/* 31 */         else if ("get".equals(m.getMethodName()))
/* 32 */           m.replace("{  $_ = $proceed($$);  if (" + SqlMapReloader.class
/* 35 */             .getName() + ".isReloading() " + "      && !($_ instanceof org.apache.ibatis.session.Configuration$StrictMap$Ambiguity))" + "    $_ = null;" + "}");
/*    */       }
/*    */     });
/*    */   }
/*    */ }

/* Location:           F:\eclipse-jee-juno-SR2-win32\plugins\org.zeroturnaround.eclipse.embedder_5.3.1.RELEASE-201307081557\jrebel\mybatis-jr-plugin-5.6.3a.jar
 * Qualified Name:     org.zeroturnaround.jrebel.mybatis.cbp.StrictMapCBP
 * JD-Core Version:    0.6.0
 */