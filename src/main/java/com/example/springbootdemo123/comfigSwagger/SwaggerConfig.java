package com.example.springbootdemo123.comfigSwagger;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket createRestApi(){
        //Docket: 摘要对象，通过对象配置描述文件信息。
        Docket docket = new Docket(DocumentationType.OAS_30);
        docket.apiInfo(apiInfo())
                //select():返回ApiSelectorBuilder 对象，通过对象调用 builder()可以创建Docket对象
                .select()//ApiSelectorBuilder
                .apis(RequestHandlerSelectors.basePackage("com.example.springbootdemo123"))
                .build();
        return docket;
    }

    //设置表述文件中的info,参数类型 ApiInfo
    private ApiInfo apiInfo() {
        //用ApiInfoBuilder进行定制
        return new ApiInfoBuilder()
                //设置标题
                .title("接口文档")
                //描述
                .description("我是描述信息")
                //版本
                .version("版本号：V.1")
                //协议
                .license("The Apache License")
                //协议url
                .licenseUrl("http://www.baidu.com")
                .build();

    }
}
