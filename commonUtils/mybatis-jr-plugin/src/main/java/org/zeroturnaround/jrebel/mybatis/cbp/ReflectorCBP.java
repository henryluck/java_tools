/*    */ package org.zeroturnaround.jrebel.mybatis.cbp;
/*    */ 
/*    */ import org.zeroturnaround.bundled.javassist.ClassPool;
/*    */ import org.zeroturnaround.bundled.javassist.CtClass;
/*    */ import org.zeroturnaround.bundled.javassist.CtConstructor;
/*    */ import org.zeroturnaround.bundled.javassist.CtMethod;
/*    */ import org.zeroturnaround.bundled.javassist.CtNewMethod;
/*    */ import org.zeroturnaround.javarebel.ClassEventListener;
/*    */ import org.zeroturnaround.javarebel.LoggerFactory;
/*    */ import org.zeroturnaround.javarebel.integration.support.JavassistClassBytecodeProcessor;
/*    */ 
/*    */ public class ReflectorCBP extends JavassistClassBytecodeProcessor
/*    */ {
/* 19 */   private static final String LOGGER = LoggerFactory.class.getName() + ".getInstance().productPrefix(\"MyBatis\")";
/*    */ 
/*    */   public void process(ClassPool cp, ClassLoader cl, CtClass ctClass) throws Exception {
/* 22 */     cp.importPackage("java.util");
/* 23 */     cp.importPackage("org.zeroturnaround.javarebel");
/* 24 */     cp.importPackage("org.zeroturnaround.javarebel.integration.util");
/*    */ 
/* 26 */     ctClass.addInterface(cp.get(ClassEventListener.class.getName()));
/*    */ 
/* 28 */     CtConstructor[] constructors = ctClass.getDeclaredConstructors();
/* 29 */     for (int i = 0; i < constructors.length; i++) {
/* 30 */       CtConstructor constructor = constructors[i];
/* 31 */       if (constructor.callsSuper()) {
/* 32 */         constructor.insertAfter("{  ReloaderFactory.getInstance().addHierarchyReloadListener(type, WeakUtil.weakCEL($0));}");
/*    */       }
/*    */ 
/*    */     }
/*    */ 
/* 40 */     ctClass.getDeclaredMethod("forClass").insertBefore("ReloaderFactory.getInstance().checkAndReload($1);");
/*    */ 
/* 44 */     ctClass.addMethod(CtNewMethod.make("public int priority() {  return ClassEventListener.PRIORITY_DEFAULT;}", ctClass));
/*    */ 
/* 50 */     CtMethod onClassEventMethod = CtNewMethod.make("public void onClassEvent(int eventType, Class clazz) {  synchronized(REFLECTOR_MAP) {" + LOGGER + ".log(\"Clearing ibatis class info cache for: \" + clazz);" + "    REFLECTOR_MAP.remove(clazz);" + "  }" + "}", ctClass);
/*    */ 
/* 58 */     ctClass.addMethod(onClassEventMethod);
/*    */   }
/*    */ }

/* Location:           F:\eclipse-jee-juno-SR2-win32\plugins\org.zeroturnaround.eclipse.embedder_5.3.1.RELEASE-201307081557\jrebel\mybatis-jr-plugin-5.6.3a.jar
 * Qualified Name:     org.zeroturnaround.jrebel.mybatis.cbp.ReflectorCBP
 * JD-Core Version:    0.6.0
 */