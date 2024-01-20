package com.mazloom.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class BeanUtils implements ApplicationContextAware {

    private static ApplicationContext context;

    public static <T> T getProperty(String propertyKey, Class<T> propertyType) {
        return context.getEnvironment().getProperty(propertyKey, propertyType);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BeanUtils.context = applicationContext;
    }

}
