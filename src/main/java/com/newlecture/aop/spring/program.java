package com.newlecture.aop.spring;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;

public class program {

	public static void main(String[] args) {
		Calculator origin = new NewlecCalculator();

		// proxy를 생성해서 실제 주업무를 위임한다.
		
		
		// int data = cal.add(3, 4);
		   int data = cal.add(3, 4);

		System.out.println(data);

	}
}
