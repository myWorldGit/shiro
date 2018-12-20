package com.lanpangzi.conf;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextHolder implements ApplicationContextAware {
    private static ApplicationContext context;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextHolder.context = applicationContext;
    }

    public static ApplicationContext getCtx(){
        return context;
    }

    public static <T> T getBean(Class<T> tClass){
        return context.getBean(tClass);
    }

    public static <T> T getBean(String beanName){
        return (T) context.getBean(beanName);
    }

}
