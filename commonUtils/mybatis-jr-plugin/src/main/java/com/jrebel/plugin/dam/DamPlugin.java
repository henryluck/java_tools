package com.jrebel.plugin.dam;

import org.zeroturnaround.javarebel.ClassResourceSource;
import org.zeroturnaround.javarebel.IntegrationFactory;
import org.zeroturnaround.javarebel.Plugin;

import com.jrebel.plugin.dam.cbp.BaseDaoCBP;
import com.jrebel.plugin.dam.cbp.ConfigurationCBP;
import com.jrebel.plugin.dam.cbp.DAMFactoryCBP;
import com.jrebel.plugin.dam.cbp.SqlSessionUtilsCBP;

public class DamPlugin implements Plugin {

    public DamPlugin() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean checkDependencies(final ClassLoader arg0, final ClassResourceSource arg1) {
        return true;
    }

    @Override
    public String getAuthor() {
        return "mike";
    }

    @Override
    public String getDescription() {
        return "jrebel pulgin for dam";
    }

    @Override
    public String getId() {
        return "dam-plugin-6.0";
    }

    @Override
    public String getName() {
        return "dam-plugin-6.0";
    }

    @Override
    public String getSupportedVersions() {
        return "1.0";
    }

    @Override
    public String getTestedVersions() {
        return "1.0";
    }

    @Override
    public String getWebsite() {
        return "none";
    }

    @Override
    public void preinit() {
        Debuger.log("DAM plugin preinit()");
        IntegrationFactory.getInstance().addIntegrationProcessor("com.riil.core.dam.ibatis.DAMFactory", new DAMFactoryCBP());
        IntegrationFactory.getInstance().addIntegrationProcessor("com.riil.core.dao.BaseDAO", new BaseDaoCBP());
        IntegrationFactory.getInstance().addIntegrationProcessor("org.apache.ibatis.session.Configuration", new ConfigurationCBP());
        
        IntegrationFactory.getInstance().addIntegrationProcessor("org.mybatis.spring.SqlSessionUtils", new SqlSessionUtilsCBP());
    }

}
