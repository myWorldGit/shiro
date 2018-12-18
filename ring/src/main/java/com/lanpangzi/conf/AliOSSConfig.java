package com.lanpangzi.conf;

import com.lanpangzi.conf.shiro.OssService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AliOSSConfig {
    @Bean(initMethod = "init",destroyMethod = "destory")
    public OssService ossService(){
        return new OssService();
    }
}
