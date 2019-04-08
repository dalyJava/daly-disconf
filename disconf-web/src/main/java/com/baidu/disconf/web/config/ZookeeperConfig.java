package com.baidu.disconf.web.config;

import com.baidu.disconf.web.innerapi.zookeeper.impl.ZookeeperDriverImpl;
import com.baidu.disconf.web.service.zookeeper.config.ZooConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ZookeeperConfig {


  @Bean
  public ZookeeperDriverImpl zookeeperDriver(){
    ZookeeperDriverImpl zookeeperDriver = new ZookeeperDriverImpl();
    return zookeeperDriver;
  }

  @Bean
  @Autowired
  public ZooConfig zooConfig(Environment environment){
    ZooConfig zooConfig = new ZooConfig();
    zooConfig.setZooHosts(environment.getProperty("zookeeper.hosts"));
    zooConfig.setZookeeperUrlPrefix(environment.getProperty("zookeeper.url.prefix"));
    return zooConfig;
  }


}
