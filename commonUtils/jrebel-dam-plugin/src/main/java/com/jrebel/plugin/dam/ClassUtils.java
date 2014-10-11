package com.jrebel.plugin.dam;

import org.zeroturnaround.bundled.javassist.CtClass;
import org.zeroturnaround.bundled.javassist.CtNewMethod;

public class ClassUtils {

    public ClassUtils() {
        // TODO Auto-generated constructor stub
    }

    public static boolean isExecuted(final CtClass ctClass) throws Exception{
        try {
            ctClass.getDeclaredMethod("damPluginFlag");
        } catch (org.zeroturnaround.bundled.javassist.NotFoundException e) {
            ctClass.addMethod(CtNewMethod.make("public void damPluginFlag() { System.out.println(\"x\");}",ctClass));
            return false;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

}
