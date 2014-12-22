/**
 * 1. singleton instance 관리 = 생성된 bean 캐싱
 * 2. 고려 내용 추후 다른 scope(bean의 생성 주기, Spring 수업을 들어야 됨) 생성이 용이한 구조가 되도록
 * Pattern을 도입하자(17번 예제)
 */

package com.coupang.c4.step14.beanfactory;

import com.coupang.c4.step14.beans.bean;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class SimpleBeanFactory implements BeanFactory{
	private String propertyPath;
	private HashMap<bean, Scope> BeanFactory = new HashMap<bean, Scope>();

	Scope scope;
	BeanDefinition BeanDefinition = new BeanDefinition(propertyPath);

	public SimpleBeanFactory(Scope scope, String propertyPath) {
		this.scope = scope;
		this.propertyPath = propertyPath;
	}


	public <T> bean getInstance(Class<T> NewBean) throws NoSuchMethodException, IllegalAccessException,
			InvocationTargetException, InstantiationException {

		Set<bean> keySet = BeanFactory.keySet();
		Iterator itr = keySet.iterator();

		while(itr.hasNext()) {
			bean next = (bean) itr.next();
			if(next.getClass() == NewBean) {
				if(BeanFactory.get(NewBean) == scope.SINGLETON) {
					return next;
				}
			}
		}

		Constructor constructor = NewBean.getDeclaredConstructor();
		constructor.setAccessible(true);

		BeanFactory.put((bean)constructor.newInstance(), scope.SINGLETON);

		return (bean)constructor.newInstance();
	}



	public Object getInstance(String beanName) throws ClassNotFoundException, NoSuchMethodException,
			IllegalAccessException, InvocationTargetException, InstantiationException {

		Set<bean> keySet = BeanFactory.keySet();
		Iterator itr = keySet.iterator();
		String beansType = BeanDefinition.getBeansType(beanName);
		Class<?> NewBean = Class.forName(beansType);

		while(itr.hasNext()) {
			bean next = (bean) itr.next();
			if(next.getClass() == NewBean) {
				if(BeanFactory.get(next) == scope.SINGLETON) {
					return next;
				}
			}
		}

		Constructor constructor = NewBean.getDeclaredConstructor();
		constructor.setAccessible(true);

		BeanFactory.put((bean)constructor.newInstance(), scope.SINGLETON);

		return (bean)constructor.newInstance();

	} //캐싱 필요 맴버변수 MAP 필요, Key 클래스 Value
}


/*
F(Factory) 해야 하는 일 : 설정 파일을 읽어서 우리가 인스턴스를 요청할 때 인스턴스 파일을 리턴
그러나 모든 일을 factory가 할 필요는 없음(다른 클래스에 위임)
유연성과 확장성을 잘 고려해서 심플 빈 팩토리를 잘 나누고 추상화를 합시다.
200점 - 감동
90점 - 스팩완성
70점 - 일부완성
30점 - 하나도못하면

전제조건 : 기본생성자가 있는 bean만 취급
*/
