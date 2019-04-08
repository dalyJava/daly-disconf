package com.baidu.disconf.web.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class WebConfig  implements WebMvcConfigurer {

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController( "/" ).setViewName( "forward:/index.html" );
    registry.setOrder( Ordered.HIGHEST_PRECEDENCE );
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/**")
        .addResourceLocations("classpath:/templates/","classpath:/static/");
  }

  @Bean
  public ReloadableResourceBundleMessageSource messageSource(){
    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
    String[] baseNames = {
        "classpath:messages/app-controller-info",
        "classpath:messages/app-service-info",
        "classpath:messages/config-controller-info",
        "classpath:messages/message-info",
        "classpath:messages/sign-controller-info",
        "classpath:messages/sign-service-info"
    };
    messageSource.addBasenames(baseNames);
    return messageSource;
  }

  /**
   * 根据浏览器语言环境，切换到相应的语言
   * @return
   */
  @Bean
  public LocaleResolver localeResolver() {
    SessionLocaleResolver localeResolver = new SessionLocaleResolver();
    //设置默认区域
    //localeResolver.setDefaultLocale(Locale.US);
    return localeResolver;
  }



}
