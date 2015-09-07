package com.jrebel.plugin.dam.cbp;

import org.zeroturnaround.bundled.javassist.ClassPool;
import org.zeroturnaround.bundled.javassist.CtClass;
import org.zeroturnaround.bundled.javassist.CtMethod;
import org.zeroturnaround.javarebel.integration.support.JavassistClassBytecodeProcessor;

public class SqlSessionUtilsCBP extends JavassistClassBytecodeProcessor {
    @Override
    public void process(final ClassPool cp, final ClassLoader cl, final CtClass ctClass) throws Exception {
        CtMethod[] methods = ctClass.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            CtMethod m = methods[i];
            if(m.getName().equals("getSqlSession")){
                m.insertBefore("if(com.jrebel.plugin.dam.SqlMapXmlFilesManager.check(sessionFactory.getClass().getClassLoader())){"+
                    " com.jrebel.plugin.dam.SqlMapXmlFilesManager.reload();}");
            }
        }
    }
}
