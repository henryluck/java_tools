package org.zeroturnaround.jrebel.mybatis.cbp;

import org.zeroturnaround.bundled.javassist.ClassPool;
import org.zeroturnaround.bundled.javassist.CtClass;
import org.zeroturnaround.javarebel.integration.support.JavassistClassBytecodeProcessor;
import org.zeroturnaround.jrebel.mybatis.JrConfiguration;
import org.zeroturnaround.jrebel.mybatis.SqlMapReloader;

public class DefaultSqlSessionFactoryCBP extends JavassistClassBytecodeProcessor {
    @Override
    public void process(final ClassPool cp, final ClassLoader cl, final CtClass ctClass) throws Exception {
        ctClass.getDeclaredMethod("openSessionFromDataSource").insertBefore(
            "if (configuration instanceof " + JrConfiguration.class.getName() + ") {" + "  "
                + SqlMapReloader.class.getName() + " reloader = ((" + JrConfiguration.class.getName()
                + ")configuration).getReloader();" + "  if (reloader != null) {" + "    reloader.reload();" + "  }"
                + "}");

        ctClass.getDeclaredMethod("openSessionFromConnection").insertBefore(
            "if (configuration instanceof " + JrConfiguration.class.getName() + ") {" + "  "
                + SqlMapReloader.class.getName() + " reloader = ((" + JrConfiguration.class.getName()
                + ")configuration).getReloader();" + "  if (reloader != null) {" + "    reloader.reload();" + "  }"
                + "}");
    }
}
