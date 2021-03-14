package com.spring.ex01;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class LoggingAdvice implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation arg0) throws Throwable {
		System.out.println("�޼��� ȣ�� �� : LogginAdvice");
		System.out.println(arg0.getMethod()+"�޼��� ȣ�� ��");
		Object obj = arg0.proceed();
		
		System.out.println("�޼��� ȣ�� �� : loggingAdvice");
		System.out.println(arg0.getMethod()+"�޼��� ȣ�� ��");
		return obj;
	}

}
