package com.baidu.disconf.web.config;

import com.baidu.disconf.web.shiro.MyRealmByAccount;
import com.baidu.disconf.web.shiro.MyRealmByEmail;
import com.baidu.disconf.web.shiro.MyRealmByPhoneNumber;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.Filter;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisSentinelManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {


  @Bean
  public DefaultWebSecurityManager securityManager(ModularRealmAuthenticator modularRealmAuthenticator,
      Collection<Realm> realms) {
    DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
    //设置realm.
    securityManager.setAuthenticator(modularRealmAuthenticator);
    //添加多realms
    securityManager.setRealms(realms);
    // 自定义缓存实现 使用redis
    //securityManager.setCacheManager(cacheManager);
    // 自定义session管理 使用redis
    //securityManager.setSessionManager(sessionManager());
    //注入记住我管理器;
    //securityManager.setRememberMeManager(rememberMeManager());
    return securityManager;
  }



  /**
   * 系统自带的Realm管理，主要针对多realm
   */
  @Bean
  @Autowired
  public ModularRealmAuthenticator modularRealmAuthenticator(Collection<Realm> realms) {
    ModularRealmAuthenticator modularRealmAuthenticator = new ModularRealmAuthenticator();
    //添加多realm
    modularRealmAuthenticator.setRealms(realms);
    //设置认证策略
    modularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
    return modularRealmAuthenticator;
  }

  @Bean
  public Collection<Realm> realms() {
    Collection<Realm> realms = new HashSet<>();
    realms.add(new MyRealmByAccount());
    realms.add(new MyRealmByEmail());
    realms.add(new MyRealmByPhoneNumber());
    return realms;
  }


  @Bean
  public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
    ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
    shiroFilterFactoryBean.setSecurityManager(securityManager);
    shiroFilterFactoryBean.setLoginUrl("/login.html");

    LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
    filterChainDefinitionMap.put("/api/account/**", "anon");
    filterChainDefinitionMap.put("/api/env/**", "anon");
    filterChainDefinitionMap.put("/css/**","anon");
    filterChainDefinitionMap.put("/js/**","anon");
    filterChainDefinitionMap.put("/img/**","anon");
    filterChainDefinitionMap.put("/plug-in/**","anon");
    filterChainDefinitionMap.put("/**", "authc");
    filterChainDefinitionMap.put("/api/**", "authc");
    //filterChainDefinitionMap.put("/**", "anon");

    Map<String, Filter> filters = new HashMap<>();
    shiroFilterFactoryBean.setFilters(filters);

    shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
    return shiroFilterFactoryBean;
  }



  @Bean
  public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
    return new LifecycleBeanPostProcessor();
  }
}
