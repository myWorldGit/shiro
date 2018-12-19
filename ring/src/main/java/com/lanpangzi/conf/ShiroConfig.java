package com.lanpangzi.conf;

import com.lanpangzi.conf.service.UserRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(){
        System.out.println(this.securityManager());
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(this.securityManager());
        Map<String,String> filterMap = new LinkedHashMap<String,String>();
        //  配置拦截的页面  设置权限等。
        filterMap.put("/login","anon");
        filterMap.put("/loginauth","anon");
        filterMap.put("/test","anon");
        filterMap.put("/*","authc");
        //  拦截后跳转的页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager  securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(this.getUserReam());
        return securityManager;

    }

    @Bean
    public UserRealm getUserReam(){
        return  new UserRealm();
    }


}
