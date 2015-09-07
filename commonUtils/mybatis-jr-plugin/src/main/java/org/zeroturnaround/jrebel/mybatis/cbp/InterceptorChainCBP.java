package org.zeroturnaround.jrebel.mybatis.cbp;

import org.zeroturnaround.bundled.javassist.ClassPool;
import org.zeroturnaround.bundled.javassist.CtClass;
import org.zeroturnaround.bundled.javassist.CtNewMethod;
import org.zeroturnaround.javarebel.integration.support.JavassistClassBytecodeProcessor;
import org.zeroturnaround.jrebel.mybatis.JrInterceptorChain;

public class InterceptorChainCBP extends JavassistClassBytecodeProcessor {
    @Override
    public void process(final ClassPool cp, final ClassLoader cl, final CtClass ctClass) throws Exception {
        ctClass.addInterface(cp.get(JrInterceptorChain.class.getName()));

        ctClass.addMethod(CtNewMethod.make("public void jrClear() {  interceptors.clear();}", ctClass));
    }
}
