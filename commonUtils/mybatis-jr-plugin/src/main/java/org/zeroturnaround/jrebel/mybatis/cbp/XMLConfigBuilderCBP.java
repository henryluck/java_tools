package org.zeroturnaround.jrebel.mybatis.cbp;

import org.zeroturnaround.bundled.javassist.ClassPool;
import org.zeroturnaround.bundled.javassist.CtClass;
import org.zeroturnaround.bundled.javassist.CtConstructor;
import org.zeroturnaround.bundled.javassist.CtField;
import org.zeroturnaround.bundled.javassist.CtMethod;
import org.zeroturnaround.bundled.javassist.CtNewMethod;
import org.zeroturnaround.javarebel.integration.support.JavassistClassBytecodeProcessor;
import org.zeroturnaround.jrebel.mybatis.JrConfiguration;
import org.zeroturnaround.jrebel.mybatis.JrXMLConfigBuilder;
import org.zeroturnaround.jrebel.mybatis.SqlMapReloader;

public class XMLConfigBuilderCBP extends JavassistClassBytecodeProcessor {
    @Override
    public void process(final ClassPool cp, final ClassLoader cl, final CtClass ctClass) throws Exception {
        ctClass.addInterface(cp.get(JrXMLConfigBuilder.class.getName()));

        ctClass.addField(new CtField(cp.get(SqlMapReloader.class.getName()), "reloader", ctClass));

        CtConstructor[] constructors = ctClass.getDeclaredConstructors();
        for (int i = 0; i < constructors.length; i++) {
            CtConstructor constructor = constructors[i];
            if (constructor.callsSuper()) {
                constructor.insertAfter("System.out.println(\"XMLConfigBuilderCBP process**********************************\");if (configuration instanceof " + JrConfiguration.class.getName() + ") {"
                    + "  $0.reloader = ((" + JrConfiguration.class.getName() + ")configuration).getReloader();"
                    + "  $0.reloader.setConfigBuilder($0);" 
                    + " $0.reloader.registerXml(getClass().getClassLoader());"
                    + "}");
            }

        }

        CtMethod origMethod = ctClass.getDeclaredMethod("parse");
        CtMethod copyMethod = CtNewMethod.copy(origMethod, "__parse", ctClass, null);
        ctClass.addMethod(copyMethod);
        origMethod
            .setBody("{  if ($0.reloader == null) {    return __parse();  }  $0.reloader.enterConf();  try {    return __parse();  } finally {    $0.reloader.exitConf();  }}");

        ctClass.addMethod(CtNewMethod.make("public void reinit() {  parsed = false;  __parse();}", ctClass));
    }
}

/*
 * Location:
 * F:\eclipse-jee-juno-SR2-win32\plugins\org.zeroturnaround.eclipse.embedder_5.3.1.RELEASE-201307081557\jrebel\
 * mybatis-jr-plugin-5.6.3a.jar Qualified Name: org.zeroturnaround.jrebel.mybatis.cbp.XMLConfigBuilderCBP JD-Core
 * Version: 0.6.0
 */