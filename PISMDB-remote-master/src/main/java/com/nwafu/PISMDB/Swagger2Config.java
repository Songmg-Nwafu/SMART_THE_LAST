package com.nwafu.PISMDB;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * 接口文档配置
 */

// swagger2的配置内容仅仅就是需要创建一个Docket实例
//@Configuration
public class Swagger2Config {
//    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.nwafu.PISMDB.controller"))
                .paths(PathSelectors.any())
                .build();
//                .ignoredParameterTypes(PersonInfo.class)
//                .ignoredParameterTypes(Face.class)
//                .ignoredParameterTypes(GroupInfo.class);
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("PISMDB（植物干扰小分子系统）API文档")
                .description("实现植物干扰小分子的数据库")
                .version("0.0.1")
                .build(); // 这部分信息其实可以自定义到配置文件中读取
    }
}

