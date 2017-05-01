package com.woodpecker.config;

import com.woodpecker.ApplicationMain;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

/**
 * WebMVC的javaConfig配置，替代XML配置bean：自定义可控的Spring MVC
 * 如果想保留Spring Boot MVC的特性，并只是添加其他的MVC配置(拦截器，formatter，视图控制器等)，
 * 继承WebMvcConfigurerAdapter 并重写其中的一些方法添加自己想要的@Bean即可
 *
 * @author Glenn
 * @since 2017-03-29
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = ApplicationMain.class, includeFilters = @ComponentScan.Filter(Controller.class), useDefaultFilters = false)
class WebMvcConfiguration extends WebMvcConfigurationSupport {

    @Override
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        RequestMappingHandlerMapping requestMappingHandlerMapping = super.requestMappingHandlerMapping();
        requestMappingHandlerMapping.setUseSuffixPatternMatch(false);
        requestMappingHandlerMapping.setUseTrailingSlashMatch(false);
        return requestMappingHandlerMapping;
    }

    /**
     * 加载静态资源文件
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(StaticResourcePath.RESOURCES_HANDLER).addResourceLocations(StaticResourcePath.RESOURCES_LOCATION);
    }
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean(name = "messageSource")
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(StaticResourcePath.MESSAGE_SOURCE);
        messageSource.setCacheSeconds(5);
        return messageSource;
    }

    @Bean(name = "templateResolver")
    public TemplateResolver templateResolver() {
        TemplateResolver templateResolver = new ServletContextTemplateResolver();
        templateResolver.setPrefix(StaticResourcePath.VIEWS);
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    @Bean(name = "springTemplateEngine")
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }

    @Bean(name = "thymeleafViewResolver")
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
        thymeleafViewResolver.setTemplateEngine(templateEngine());
        thymeleafViewResolver.setCharacterEncoding("UTF-8");
        return thymeleafViewResolver;
    }

    @Controller
    static class FaviconController {
        @RequestMapping(value = "favicon.ico")
        @SuppressWarnings("unused")
        String favicon() {
            return "forward:/resources/images/favicon.ico";
        }
    }
    /**
     * 路径常量类
     */
    private static final class StaticResourcePath{
        private static final String MESSAGE_SOURCE = "/WEB-INF/i18n/messages";
        private static final String VIEWS = "/WEB-INF/views/";
        private static final String RESOURCES_LOCATION = "/resources/";
        private static final String RESOURCES_HANDLER = RESOURCES_LOCATION + "**";
    }
}
