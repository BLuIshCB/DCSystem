package com.cb.common.config;

import com.cb.common.interceptor.JwtTokenAdminInterceptor;
import com.cb.common.interceptor.JwtTokenUserInterceptor;
import com.cb.common.utils.JacksonObjectMapper;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.*;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;
import java.util.Properties;


/**
 * 配置类，注册web层相关组件
 */
@Configuration
@Slf4j

public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    private JwtTokenAdminInterceptor jwtTokenAdminInterceptor;
    @Autowired
    private JwtTokenUserInterceptor jwtTokenUserInterceptor;
    /**
     * 注册自定义拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("开始注册admin自定义拦截器...");

        registry.addInterceptor(jwtTokenAdminInterceptor)
                .addPathPatterns("/admin/**")
//                .excludePathPatterns("/admin/**")
                .excludePathPatterns("/admin/getImage/**")
                .excludePathPatterns("/admin/employee/login");

        log.info("开始注册user自定义拦截器...");
        registry.addInterceptor(jwtTokenUserInterceptor)
                .addPathPatterns("/user/**")
                .excludePathPatterns("/user/user/**")
                .excludePathPatterns("/user/query/**")
                .excludePathPatterns("/user/shop/status");
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
    //允许垮域
    @Bean
    public CorsWebFilter corsWebFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 配置跨域
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 允许哪个请求头
        corsConfiguration.addAllowedHeader("*");
        // 允许哪个方法进行跨域
        corsConfiguration.addAllowedMethod("*");
        // 允许哪个请求来源进行跨域
        // corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedOriginPattern("*");
        // 是否允许携带cookie进行跨域
        corsConfiguration.setAllowCredentials(true);

        source.registerCorsConfiguration("/**",corsConfiguration);
        return new CorsWebFilter(source);
    }





    /**
    *   接口文档生成的配置
    * */
//    @Bean
//    public Docket docket1(){
//        log.info("准备生成管理端接口文档...");
//        ApiInfo apiInfo = new ApiInfoBuilder()
//                .title("CBPJ项目管理端接口文档")
//                .version("2.0")
//                .description("CBPJ项目管理端接口文档")
//                .build();
//
//        Docket docket = new Docket(DocumentationType.SWAGGER_2)
//                .groupName("管理端接口")
//                .apiInfo(apiInfo)
//                .select()
//                //指定生成接口需要扫描的包
//                .apis(RequestHandlerSelectors.basePackage("com.cb.system.controller"))
//                .paths(PathSelectors.any())
//                .build();
//
//        return docket;
//    }
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
     * json时间格式转换， 需要配合utils包下的JacksonObjectMapper使用
    * */
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        log.info("注册扩展消息转换器...");
        //创建一个消息转换器对象
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        //需要为消息转换器设置一个对象转换器，对象转换器可以将Java对象序列化为json数据
        converter.setObjectMapper(new JacksonObjectMapper());
        //将自己的消息转化器加入容器中
        converters.add(0,converter);
    }

}
