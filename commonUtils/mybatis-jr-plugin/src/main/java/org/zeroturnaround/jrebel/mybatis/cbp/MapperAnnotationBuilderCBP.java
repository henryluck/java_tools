/*    */ package org.zeroturnaround.jrebel.mybatis.cbp;
/*    */ 
/*    */ import org.apache.ibatis.builder.xml.XMLMapperBuilder;
/*    */ import org.apache.ibatis.executor.ErrorContext;
/*    */ import org.apache.ibatis.io.Resources;
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
/*    */ public class MapperAnnotationBuilderCBP extends JavassistClassBytecodeProcessor
/*    */ {
/*    */   public void process(ClassPool cp, ClassLoader cl, CtClass ctClass)
/*    */     throws Exception
/*    */   {
/* 21 */     CtMethod m = ctClass.getDeclaredMethod("loadXmlResource");
/* 22 */     m.instrument(new ExprEditor()
/*    */     {
/*    */       public void edit(NewExpr e) throws CannotCompileException {
/* 25 */         if (XMLMapperBuilder.class.getName().equals(e.getClassName()))
/* 26 */           e.replace("{  $_ = $proceed($$);  if ($2 instanceof " + JrConfiguration.class
/* 29 */             .getName() + ") {" + "    " + SqlMapReloader.class
/* 30 */             .getName() + " reloader = ((" + JrConfiguration.class.getName() + ") $2).getReloader();" + "    if (reloader != null) {" + "      reloader.addMapping(" + Resources.class
/* 32 */             .getName() + ".getResourceURL($3), $3);" + "    }" + "  }" + "}");
/*    */       }
/*    */ 
/*    */       public void edit(MethodCall m)
/*    */         throws CannotCompileException
/*    */       {
/* 41 */         String methodName = m.getMethodName();
/* 42 */         if (("getResourceAsStream".equals(methodName)) && (Resources.class.getName().equals(m.getClassName())))
/* 43 */           m.replace("{  try {    $_ = $proceed($$);  } catch (java.io.IOException ioe) {    if (this.configuration instanceof " + JrConfiguration.class
/* 48 */             .getName() + ") {" + "  " + SqlMapReloader.class
/* 49 */             .getName() + " reloader = ((" + JrConfiguration.class.getName() + ") this.configuration).getReloader();" + "      if (reloader != null && reloader.mappingsLoadedFromSameLocation()) {" + "        String _urlString = reloader.buildUrlBasedOnFirstMapping(type);" + "        String _path = reloader.buildPathBasedOnFirstMapping(type);" + "        java.io.InputStream _in = null;" + "        try {" + "          _in = " + Resources.class
/* 55 */             .getName() + ".getUrlAsStream(_urlString);" + "        } catch (java.io.IOException ignore) {}" + "        if (_in != null) {" + "          try {" + "        " + XMLMapperBuilder.class
/* 59 */             .getName() + " _builder = new " + XMLMapperBuilder.class.getName() + "(_in, this.configuration, _path, this.configuration.getSqlFragments());" + "            _builder.parse();" + "          } catch (Exception e) {" + "            throw new RuntimeException(\"Failed to parse mapping resource: '\" + _path + \"'\", e);" + "          } finally {" + "        " + ErrorContext.class
/* 64 */             .getName() + ".instance().reset();" + "          }" + "          reloader.addMapping(new java.net.URL(_urlString), _path);" + "        }" + "      }" + "    }" + "  }" + "}");
/*    */       }
/*    */     });
/*    */   }
/*    */ }

/* Location:           F:\eclipse-jee-juno-SR2-win32\plugins\org.zeroturnaround.eclipse.embedder_5.3.1.RELEASE-201307081557\jrebel\mybatis-jr-plugin-5.6.3a.jar
 * Qualified Name:     org.zeroturnaround.jrebel.mybatis.cbp.MapperAnnotationBuilderCBP
 * JD-Core Version:    0.6.0
 */