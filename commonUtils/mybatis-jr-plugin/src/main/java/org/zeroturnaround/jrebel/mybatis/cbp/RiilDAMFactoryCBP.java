package org.zeroturnaround.jrebel.mybatis.cbp;

import org.zeroturnaround.bundled.javassist.ClassPool;
import org.zeroturnaround.bundled.javassist.CtClass;
import org.zeroturnaround.bundled.javassist.CtMethod;
import org.zeroturnaround.javarebel.integration.support.JavassistClassBytecodeProcessor;

import com.jrebel.plugin.dam.ClassUtils;

public class RiilDAMFactoryCBP extends JavassistClassBytecodeProcessor {
    //奇怪，cbp类会被调用3次，控制一下

    public RiilDAMFactoryCBP() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void process(final ClassPool arg0, final ClassLoader arg1, final CtClass ctClass) throws Exception {
        //奇怪，cbp类总是被调用多次，导致错误,验证一下
        if(ClassUtils.isExecuted(ctClass)){
            return;
        }
        //加载所有的xml
        CtMethod initialize = ctClass.getDeclaredMethod("initialize");
        initialize.insertAfter("System.out.println(\"Start init sqlmap xml monitor...\");"+
                                            "com.jrebel.plugin.dam.SqlMapXmlFilesManager.registerXml(getClass().getClassLoader());"+
                                            "com.jrebel.plugin.dam.SqlMapXmlFilesManager.dam=this;"+
                                            "System.out.println(\"init sqlmap xml monitor finish!\");");
    }

}
