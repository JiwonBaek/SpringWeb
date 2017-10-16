package com.newlecture.webapp.aop.spring;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Program {

   public static void main(String[] args) {
      
      ApplicationContext context = new ClassPathXmlApplicationContext("com/newlecture/webapp/aop/spring/aop-context.xml");
      Calculator cal = (Calculator) context.getBean("cal");
      
      
      int data = cal.add(3,4);
      data = cal.div(3,0);
      
      System.out.println(data);

   }
}