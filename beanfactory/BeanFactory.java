package com.coupang.c4.step14.beanfactory;

import com.coupang.c4.step14.beans.bean;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by coupang on 2014. 12. 21..
 */
public interface BeanFactory {
    <T> bean getInstance(Class<T> NewBean) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException;
    Object getInstance(String beanName) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException;
}
