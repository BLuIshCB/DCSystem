package com.cb.common.config;

import com.cb.common.interceptor.JwtTokenAdminInterceptor;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Properties;


/**
 * 配置类，注册web层相关组件
 */
@Configuration
@Slf4j

public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    private JwtTokenAdminInterceptor jwtTokenAdminInterceptor;
    /**
     * 注册自定义拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("开始注册自定义拦截器...");

//        InterceptorRegistration registration = registry.addInterceptor(new JwtTokenAdminInterceptor());
//        registration.addPathPatterns("/**");
//        registration.excludePathPatterns("/admin/employee/login");


        registry.addInterceptor(jwtTokenAdminInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/employee/login");

//        registry.addInterceptor(jwtTokenUserInterceptor)
//                .addPathPatterns("/user/**")
//                .excludePathPatterns("/user/user/login")
//                .excludePathPatterns("/user/shop/status");
    }
    /**
    * 使用mybatis plus 与pagehleper时需要加入下面的配置
    * */
    @Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum","true");
        properties.setProperty("rowBoundsWithCount","true");
        properties.setProperty("reasonable","true");
        properties.setProperty("dialect","mysql");
        pageHelper.setProperties(properties);
        return pageHelper;
    }
    /**
    *   接口文档生成的配置
    * */
    @Bean
    public Docket docket1(){
        log.info("准备生成管理端接口文档...");
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("CBPJ项目管理端接口文档")
                .version("2.0")
                .description("CBPJ项目管理端接口文档")
                .build();

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName("管理端接口")
                .apiInfo(apiInfo)
                .select()
                //指定生成接口需要扫描的包
                .apis(RequestHandlerSelectors.basePackage("com.cb.system.controller"))
                .paths(PathSelectors.any())
                .build();

        return docket;
    }
    /**
     *   接口文档生成的配置
     * */
//    @Bean
//    public Docket docket2(){
//        log.info("准备生成用户端接口文档...");
//        ApiInfo apiInfo = new ApiInfoBuilder()
//                .title("CBPJ项目接口文档")
//                .version("2.0")
//                .description("项目接口文档")
//                .build();
//
//        Docket docket = new Docket(DocumentationType.SWAGGER_2)
//                .groupName("用户端接口")
//                .apiInfo(apiInfo)
//                .select()
//                //指定生成接口需要扫描的包
//                .apis(RequestHandlerSelectors.basePackage("com.cb.controller.user"))
//                .paths(PathSelectors.any())
//                .build();
//
//        return docket;
//    }

    /**
     * 设置静态资源映射，主要是访问接口文档（html、js、css）
     * @param registry
     */
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        log.info("开始设置静态资源映射...");
//        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//
//    }

//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addRedirectViewController("/null/api-docs",
//                "/api-docs").setKeepQueryParams(true);
//        registry.addRedirectViewController("/null/swagger-resources/configuration/ui",
//                "/swagger-resources/configuration/ui");
//
//    }


}
