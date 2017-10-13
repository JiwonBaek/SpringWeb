package com.newlecture.aop;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;

public class program {

	public static void main(String[] args) {
		Calculator origin = new NewlecCalculator();

		// proxy�� �����ؼ� ���� �־����� �����Ѵ�.
		Calculator cal = (Calculator) Proxy.newProxyInstance(NewlecCalculator.class.getClassLoader(),
				new Class[] { Calculator.class }, new InvocationHandler() {

					@Override
					public Object invoke(Object proxy, Method method, Object[] obj) throws Throwable {
						System.out.println("����ó�� ��������");

						Object result = method.invoke(origin, args);

						return result;
					}

				});

		// int data = cal.add(3, 4);
		int data = cal.add(3, 4);

		System.out.println(data);

	}
}
