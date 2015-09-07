package org.zeroturnaround.jrebel.mybatis.cbp;

import org.zeroturnaround.bundled.javassist.ClassPool;
import org.zeroturnaround.bundled.javassist.CtClass;
import org.zeroturnaround.bundled.javassist.CtMethod;
import org.zeroturnaround.javarebel.integration.support.JavassistClassBytecodeProcessor;
import org.zeroturnaround.jrebel.mybatis.JrConfiguration;
import org.zeroturnaround.jrebel.mybatis.SqlMapReloader;

public class SqlSessionUtilsCBP extends JavassistClassBytecodeProcessor {
    @Override
    public void process(final ClassPool cp, final ClassLoader cl, final CtClass ctClass) throws Exception {
        CtMethod[] methods = ctClass.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            CtMethod m = methods[i];
            if(m.getName().equals("getSqlSession")){
                m.insertBefore("System.out.println(\"SqlSessionUtilsCBP process **************************************\");");
                m.insertBefore(SqlMapReloader.class.getName() + " reloader = ((" + JrConfiguration.class.getName()
                    + ")sessionFactory.getConfiguration()).getReloader();" + "  if (reloader != null) {" + "    reloader.reload();" + "  }");
            }
        }
    }
}
