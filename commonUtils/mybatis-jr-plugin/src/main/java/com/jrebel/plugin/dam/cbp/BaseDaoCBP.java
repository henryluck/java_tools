package com.jrebel.plugin.dam.cbp;

import org.zeroturnaround.bundled.javassist.ClassPool;
import org.zeroturnaround.bundled.javassist.CtClass;
import org.zeroturnaround.javarebel.integration.support.JavassistClassBytecodeProcessor;

import com.jrebel.plugin.dam.ClassUtils;

public class BaseDaoCBP extends JavassistClassBytecodeProcessor {

    //奇怪，cbp类会被调用3次，控制一下
    public BaseDaoCBP() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void process(final ClassPool cp, final ClassLoader arg1, final CtClass ctClass) throws Exception {
        //奇怪，cbp类总是被调用多次，导致错误,验证一下
        if(ClassUtils.isExecuted(ctClass)){
            return;
        }
        ctClass.getDeclaredMethod("getDam").insertBefore(
            "if(com.jrebel.plugin.dam.SqlMapXmlFilesManager.check(getClass().getClassLoader())){"+
                "com.jrebel.plugin.dam.SqlMapXmlFilesManager.reload();}");
    }

}
