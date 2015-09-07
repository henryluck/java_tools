package org.zeroturnaround.jrebel.mybatis.cbp;

import org.zeroturnaround.bundled.javassist.ClassPool;
import org.zeroturnaround.bundled.javassist.CtClass;
import org.zeroturnaround.bundled.javassist.CtConstructor;
import org.zeroturnaround.bundled.javassist.CtField;
import org.zeroturnaround.bundled.javassist.CtNewMethod;
import org.zeroturnaround.javarebel.integration.support.JavassistClassBytecodeProcessor;
import org.zeroturnaround.jrebel.mybatis.JrConfiguration;
import org.zeroturnaround.jrebel.mybatis.JrInterceptorChain;
import org.zeroturnaround.jrebel.mybatis.SqlMapReloader;

public class ConfigurationCBP extends JavassistClassBytecodeProcessor {
    @Override
    public void process(final ClassPool cp, final ClassLoader cl, final CtClass ctClass) throws Exception {
        ctClass.addInterface(cp.get(JrConfiguration.class.getName()));

        ctClass.addField(new CtField(cp.get(SqlMapReloader.class.getName()), "reloader", ctClass));

        CtConstructor[] constructors = ctClass.getConstructors();
        for (int i = 0; i < constructors.length; i++) {
            CtConstructor constructor = constructors[i];
            if (constructor.callsSuper()) {
                constructor.insertAfter("reloader = new " + SqlMapReloader.class.getName() + "($0);");
            }
        }

        ctClass.addMethod(CtNewMethod.make("public " + SqlMapReloader.class.getName() + " getReloader() {"
            + "  return reloader;" + "}", ctClass));

        ctClass.addMethod(CtNewMethod.make("public void reinit() {  loadedResources.clear();  (("
            + JrInterceptorChain.class.getName() + ") interceptorChain).jrClear();" + "}", ctClass));

        ctClass.getDeclaredMethod("isResourceLoaded").insertAfter(
            "if (reloader.doReload($1)) {  loadedResources.remove($1);  $_ = false;}");
    }
}
