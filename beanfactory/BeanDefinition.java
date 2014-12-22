package com.coupang.c4.step14.beanfactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by coupang on 2014. 12. 21..
 */
public class BeanDefinition implements BeanDefinitionLoader {

    String propertypath;

    public BeanDefinition(String propertypath) {
        this.propertypath = propertypath;
    }


    public String getBeansType(String bean) {
        InputStream TeampStream = this.getClass().getResourceAsStream("/com/coupang/c4/step14/bean-definitions.properties");
        Properties properties = new Properties();

        try {
            properties.load(TeampStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties.getProperty(bean);
    }
}
