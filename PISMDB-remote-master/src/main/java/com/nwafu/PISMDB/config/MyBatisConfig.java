package com.nwafu.PISMDB.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.pagehelper.PageHelper;

/**
 * @Author songmg
 * pageHelper注入  实现分页功能
 */
@Configuration
public class MyBatisConfig {
    @Bean
    public PageHelper pageHelper () {
        System.out.println("...pageHelper...");
        PageHelper pageHelper=new PageHelper();
        Properties p=new Properties();
        p.setProperty("dialect","mysql");
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithoutCount", "true");
        p.setProperty("reasonable", "true");
        p.setProperty("params","count=countSql");
        pageHelper.setProperties(p);
        return pageHelper;
    }
}
