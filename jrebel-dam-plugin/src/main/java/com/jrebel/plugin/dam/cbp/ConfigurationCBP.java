package com.jrebel.plugin.dam.cbp;

import org.zeroturnaround.bundled.javassist.ClassPool;
import org.zeroturnaround.bundled.javassist.CtClass;
import org.zeroturnaround.bundled.javassist.CtConstructor;
import org.zeroturnaround.bundled.javassist.CtNewMethod;
import org.zeroturnaround.javarebel.integration.support.JavassistClassBytecodeProcessor;

import com.jrebel.plugin.dam.ClassUtils;
import com.jrebel.plugin.dam.JrConfiguration;

public class ConfigurationCBP extends JavassistClassBytecodeProcessor {
    
    @Override
    public void process(final ClassPool cp, final ClassLoader cl, final CtClass ctClass)
        throws Exception
      {
        //奇怪，cbp类总是被调用多次，导致错误,验证一下
        if(ClassUtils.isExecuted(ctClass)){
            return;
        }
        
        CtConstructor[] constructors = ctClass.getConstructors();
        for (int i = 0; i < constructors.length; i++) {
          CtConstructor constructor = constructors[i];
          if (constructor.callsSuper()) {
            constructor.insertAfter("com.jrebel.plugin.dam.Debuger.log(\"赋值引用config\");com.jrebel.plugin.dam.SqlMapXmlFilesManager.config=this;");
          }
        }
        
        ctClass.addInterface(cp.get(JrConfiguration.class.getName()));
        ctClass.addMethod(CtNewMethod.make("public void reInitConfig() {  loadedResources.clear();"+
        "mappedStatements.clear();"+"resultMaps.clear();"+"parameterMaps.clear();"+"sqlFragments.clear();"+
        "com.jrebel.plugin.dam.Debuger.log(\"DAM Configuration loadedResources clear!!\");}", ctClass));

        ctClass.getDeclaredMethod("isResourceLoaded").insertAfter("if (com.jrebel.plugin.dam.SqlMapXmlFilesManager.check(getClass().getClassLoader())) {  loadedResources.remove($1);  $_ = false;}");
      }
}
