/*    */ package org.zeroturnaround.jrebel.mybatis.cbp;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.zeroturnaround.bundled.javassist.ClassPool;
/*    */ import org.zeroturnaround.bundled.javassist.CtClass;
/*    */ import org.zeroturnaround.bundled.javassist.CtConstructor;
/*    */ import org.zeroturnaround.bundled.javassist.CtField;
/*    */ import org.zeroturnaround.bundled.javassist.CtMethod;
/*    */ import org.zeroturnaround.bundled.javassist.CtNewMethod;
/*    */ import org.zeroturnaround.javarebel.integration.support.JavassistClassBytecodeProcessor;
/*    */ import org.zeroturnaround.jrebel.mybatis.JrTypeAliasRegistry;
/*    */ 
/*    */ public class TypeAliasRegistryCBP extends JavassistClassBytecodeProcessor
/*    */ {
/*    */   public void process(ClassPool cp, ClassLoader cl, CtClass ctClass)
/*    */     throws Exception
/*    */   {
/* 13 */     ctClass.addInterface(cp.get(JrTypeAliasRegistry.class.getName()));
/*    */ 
/* 15 */     ctClass.addField(new CtField(cp.get(Map.class.getName()), "packageNamesAndSuperTypes", ctClass));
/*    */ 
/* 17 */     CtConstructor[] constructors = ctClass.getConstructors();
/* 18 */     for (int i = 0; i < constructors.length; i++) {
/* 19 */       CtConstructor constructor = constructors[i];
/* 20 */       if (constructor.callsSuper()) {
/* 21 */         constructor.insertAfter("packageNamesAndSuperTypes = new " + HashMap.class.getName() + "();");
/*    */       }
/*    */ 
/*    */     }
/*    */ 
/* 27 */     CtClass[] params = { cp
/* 26 */       .get("java.lang.String"), 
/* 26 */       cp
/* 27 */       .get("java.lang.Class") };
/*    */ 
/* 29 */     ctClass.getDeclaredMethod("registerAliases", params).insertBefore("packageNamesAndSuperTypes.put($1, $2);");
/*    */ 
/* 31 */     ctClass.addMethod(CtNewMethod.make("public void reRegisterAliases() {  java.util.Iterator it = packageNamesAndSuperTypes.entrySet().iterator();  while (it.hasNext()) {    java.util.Map.Entry me = (java.util.Map.Entry) it.next();    this.registerAliases((String) me.getKey(), (Class) me.getValue());  }}", ctClass));
/*    */   }
/*    */ }

/* Location:           F:\eclipse-jee-juno-SR2-win32\plugins\org.zeroturnaround.eclipse.embedder_5.3.1.RELEASE-201307081557\jrebel\mybatis-jr-plugin-5.6.3a.jar
 * Qualified Name:     org.zeroturnaround.jrebel.mybatis.cbp.TypeAliasRegistryCBP
 * JD-Core Version:    0.6.0
 */