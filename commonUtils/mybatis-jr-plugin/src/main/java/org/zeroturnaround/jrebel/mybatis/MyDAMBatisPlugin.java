package org.zeroturnaround.jrebel.mybatis;

import org.zeroturnaround.javarebel.ClassResourceSource;
import org.zeroturnaround.javarebel.Integration;
import org.zeroturnaround.javarebel.IntegrationFactory;
import org.zeroturnaround.javarebel.Plugin;
import org.zeroturnaround.jrebel.mybatis.cbp.ConfigurationCBP;
import org.zeroturnaround.jrebel.mybatis.cbp.DefaultSqlSessionFactoryCBP;
import org.zeroturnaround.jrebel.mybatis.cbp.EbizDamImplCBP;
import org.zeroturnaround.jrebel.mybatis.cbp.InterceptorChainCBP;
import org.zeroturnaround.jrebel.mybatis.cbp.MapperAnnotationBuilderCBP;
import org.zeroturnaround.jrebel.mybatis.cbp.MapperFactoryBeanCBP;
import org.zeroturnaround.jrebel.mybatis.cbp.MapperRegistryCBP;
import org.zeroturnaround.jrebel.mybatis.cbp.ReflectorCBP;
import org.zeroturnaround.jrebel.mybatis.cbp.ResourcesCBP;
import org.zeroturnaround.jrebel.mybatis.cbp.RiilDamImplCBP;
import org.zeroturnaround.jrebel.mybatis.cbp.SqlSessionFactoryBeanCBP;
import org.zeroturnaround.jrebel.mybatis.cbp.SqlSessionUtilsCBP;
import org.zeroturnaround.jrebel.mybatis.cbp.StrictMapCBP;
import org.zeroturnaround.jrebel.mybatis.cbp.TypeAliasRegistryCBP;
import org.zeroturnaround.jrebel.mybatis.cbp.XMLConfigBuilderCBP;

public class MyDAMBatisPlugin implements Plugin {
    @Override
    public void preinit() {
        System.out.println("MyBatisPlugin preinit@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        ClassLoader cl = MyDAMBatisPlugin.class.getClassLoader();

        Integration integration = IntegrationFactory.getInstance();
        
        
        integration.addIntegrationProcessor(cl, "com.riil.core.dam.ibatis.DamImpl", new RiilDamImplCBP());
        integration.addIntegrationProcessor(cl, "com.ebiz.project.framework.core.dam.mybatis.DamImpl", new EbizDamImplCBP());
        integration.addIntegrationProcessor(cl, "org.mybatis.spring.SqlSessionUtils", new SqlSessionUtilsCBP());

        
        integration.addIntegrationProcessor(cl, "org.apache.ibatis.io.Resources", new ResourcesCBP());

        integration.addIntegrationProcessor(cl, new String[] { "org.apache.ibatis.builder.xml.XMLConfigBuilder",
            "pl.atena.ibatisbaf.core.config.ConfigBuilder" }, new XMLConfigBuilderCBP());

        integration.addIntegrationProcessor(cl, "org.apache.ibatis.session.defaults.DefaultSqlSessionFactory",
            new DefaultSqlSessionFactoryCBP());

        integration.addIntegrationProcessor(cl, "org.apache.ibatis.session.Configuration", new ConfigurationCBP());

        integration
            .addIntegrationProcessor(cl, "org.apache.ibatis.session.Configuration$StrictMap", new StrictMapCBP());

        integration.addIntegrationProcessor(cl, "org.apache.ibatis.binding.MapperRegistry", new MapperRegistryCBP());

        integration.addIntegrationProcessor(cl, "org.apache.ibatis.reflection.Reflector", new ReflectorCBP());

        integration.addIntegrationProcessor(cl, "org.mybatis.spring.SqlSessionFactoryBean",
            new SqlSessionFactoryBeanCBP());

        integration.addIntegrationProcessor(cl, "org.apache.ibatis.builder.annotation.MapperAnnotationBuilder",
            new MapperAnnotationBuilderCBP());

        integration.addIntegrationProcessor(cl, "org.apache.ibatis.type.TypeAliasRegistry", new TypeAliasRegistryCBP());

        integration.addIntegrationProcessor(cl, "org.apache.ibatis.plugin.InterceptorChain", new InterceptorChainCBP());

        integration.addIntegrationProcessor(cl, "org.mybatis.spring.mapper.MapperFactoryBean",
            new MapperFactoryBeanCBP());
    }

    @Override
    public boolean checkDependencies(final ClassLoader cl, final ClassResourceSource crs) {
        return crs.getClassResource("org.apache.ibatis.session.SqlSessionFactoryBuilder") != null;
    }

    @Override
    public String getAuthor() {
        return null;
    }

    @Override
    public String getDescription() {
        return "<li>Reloads modified SQL maps.</li>";
    }

    @Override
    public String getId() {
        return "mydam_batis_plugin";
    }

    @Override
    public String getName() {
        return "MyBatis plugin";
    }

    @Override
    public String getWebsite() {
        return null;
    }

    @Override
    public String getSupportedVersions() {
        return null;
    }

    @Override
    public String getTestedVersions() {
        return null;
    }
}

/*
 * Location:
 * F:\eclipse-jee-juno-SR2-win32\plugins\org.zeroturnaround.eclipse.embedder_5.3.1.RELEASE-201307081557\jrebel\
 * mybatis-jr-plugin-5.6.3a.jar Qualified Name: org.zeroturnaround.jrebel.mybatis.MyBatisPlugin JD-Core Version: 0.6.0
 */