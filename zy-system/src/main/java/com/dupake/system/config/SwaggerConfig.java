package com.dupake.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dupake.system.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(defaultHeader())
                ;
    }
    private static List<Parameter> defaultHeader(){
        ParameterBuilder appToken = new ParameterBuilder();
        appToken.name("Authorization").description("登录令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        ParameterBuilder appSubmitTolken = new ParameterBuilder();
        appSubmitTolken.name("SubmitToken").description("重复提交令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();

        List<Parameter> pars = new ArrayList<>();
        pars.add(appToken.build());
        pars.add(appSubmitTolken.build());
        return pars;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("XT ORDER APIS")
                .description("订单服务")
                .version("1.0")
                .build();
    }

}