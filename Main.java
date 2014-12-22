package com.coupang.c4.step14;

import com.coupang.c4.step14.beanfactory.Scope;
import com.coupang.c4.step14.beanfactory.SimpleBeanFactory;
import com.coupang.c4.step14.beans.Sample1;
import com.coupang.c4.step14.beans.Sample2;
import com.coupang.c4.step14.beans.bean;

import java.lang.reflect.InvocationTargetException;


public class Main {
	public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		SimpleBeanFactory simpleBeanFactory = new SimpleBeanFactory(Scope.SINGLETON, "/com/coupang/c4/step14/bean-definitions.properties");
		
		bean instance = simpleBeanFactory.getInstance(Sample1.class);
		System.out.println(instance != null);
		
		Object instance2 = simpleBeanFactory.getInstance("sample2");
		System.out.println(instance2 != null);
		System.out.println(Sample2.class.isAssignableFrom(instance2.getClass()));
		System.out.println(instance2 instanceof Sample2);
	}
}
