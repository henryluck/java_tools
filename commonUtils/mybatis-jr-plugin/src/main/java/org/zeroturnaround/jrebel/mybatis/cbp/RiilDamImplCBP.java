package org.zeroturnaround.jrebel.mybatis.cbp;

import java.util.ArrayList;
import java.util.List;

import org.zeroturnaround.bundled.javassist.ClassPool;
import org.zeroturnaround.bundled.javassist.CtClass;
import org.zeroturnaround.bundled.javassist.CtMethod;
import org.zeroturnaround.javarebel.integration.support.JavassistClassBytecodeProcessor;
import org.zeroturnaround.jrebel.mybatis.JrConfiguration;
import org.zeroturnaround.jrebel.mybatis.SqlMapReloader;

public class RiilDamImplCBP extends JavassistClassBytecodeProcessor {
    @Override
    public void process(final ClassPool cp, final ClassLoader cl, final CtClass ctClass) throws Exception {
        List<String> methodNames = new ArrayList<String>();
        methodNames.add("delete");
        methodNames.add("insert");
        methodNames.add("selectList");
        methodNames.add("selectOne");
        methodNames.add("update");
        
        
        CtMethod[] methods = ctClass.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            CtMethod m = methods[i];
            if(methodNames.contains(m.getName())){
                m.insertBefore("System.out.println(\"RiilDamImplCBP process **************************************\");");
                m.insertBefore(SqlMapReloader.class.getName() + " reloader = ((" + JrConfiguration.class.getName()
                    + ")m_sqlSession.getConfiguration()).getReloader();" + "  if (reloader != null) {" + "    reloader.reload();" + "  }");
            }
        }
    }
}
