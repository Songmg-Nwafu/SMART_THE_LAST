package com.nwafu.PISMDB;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Slf4j
@SpringBootApplication
@MapperScan("com.nwafu.PISMDB.dao")//让其扫描dao层接口。
@EnableSwagger2
public class PismdbApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(PismdbApplication.class);
    }

    public static void main(String[] args) {
        log.info("项目启动准备中——————");
        SpringApplication.run(PismdbApplication.class, args);
        log.info("项目启动成功————————");

    }
}

