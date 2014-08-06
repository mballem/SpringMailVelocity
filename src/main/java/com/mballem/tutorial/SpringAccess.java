package com.mballem.tutorial;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by Marcio Ballem on 05/08/2014.
 */
public class SpringAccess {

    private static ApplicationContext springContext;

    private SpringAccess() {
    }

    public static synchronized ApplicationContext getSpringContext() {
        if (springContext == null) {
            springContext = new FileSystemXmlApplicationContext("classpath:spring-config.xml");
        }
        return springContext;
    }
}
