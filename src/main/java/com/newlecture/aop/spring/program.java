package com.newlecture.aop.spring;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;

public class program {

	public static void main(String[] args) {
		Calculator origin = new NewlecCalculator();

		// proxy�� �����ؼ� ���� �־����� �����Ѵ�.
		
		
		// int data = cal.add(3, 4);
		   int data = cal.add(3, 4);

		System.out.println(data);

	}
}
