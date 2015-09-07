/*    */ package org.zeroturnaround.jrebel.mybatis.cbp;
/*    */ 
/*    */ import org.zeroturnaround.bundled.javassist.ClassPool;
import org.zeroturnaround.bundled.javassist.CtClass;
import org.zeroturnaround.bundled.javassist.CtMethod;
import org.zeroturnaround.bundled.javassist.NotFoundException;
import org.zeroturnaround.javarebel.integration.support.JavassistClassBytecodeProcessor;
import org.zeroturnaround.jrebel.mybatis.JrTypeAliasRegistry;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MapperFactoryBeanCBP extends JavassistClassBytecodeProcessor
/*    */ {
/*    */   @Override
public void process(final ClassPool cp, final ClassLoader cl, final CtClass ctClass)
/*    */     throws Exception
/*    */   {
                   CtMethod m;
/*    */     try
/*    */     {
/* 16 */       m = ctClass.getDeclaredMethod("checkDaoConfig");
/*    */     }
/*    */     catch (NotFoundException customerUsesVersionOlderThanMybatisSpring101)
/*    */     {
/* 18 */       return;
/*    */     }
/*    */     
/* 21 */     m.insertBefore(JrTypeAliasRegistry.class
/* 22 */       .getName() + " registry; " + "registry = (" + JrTypeAliasRegistry.class
/* 23 */       .getName() + ") getSqlSession().getConfiguration().getTypeAliasRegistry(); " + "registry.reRegisterAliases();");
/*    */   }
/*    */ }

/* Location:           F:\eclipse-jee-juno-SR2-win32\plugins\org.zeroturnaround.eclipse.embedder_5.3.1.RELEASE-201307081557\jrebel\mybatis-jr-plugin-5.6.3a.jar
 * Qualified Name:     org.zeroturnaround.jrebel.mybatis.cbp.MapperFactoryBeanCBP
 * JD-Core Version:    0.6.0
 */