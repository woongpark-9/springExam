package com.spring.ex01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CalcTest {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("AOPTest.xml");
		Calculator cal = (Calculator) ctx.getBean("proxyCal");
		cal.add(40, 40);
	}
	
	
}
