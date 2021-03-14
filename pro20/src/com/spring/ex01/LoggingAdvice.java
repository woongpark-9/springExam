package com.spring.ex01;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class LoggingAdvice implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation arg0) throws Throwable {
		System.out.println("메서드 호출 전 : LogginAdvice");
		System.out.println(arg0.getMethod()+"메서드 호출 전");
		Object obj = arg0.proceed();
		
		System.out.println("메서드 호출 후 : loggingAdvice");
		System.out.println(arg0.getMethod()+"메서드 호출 후");
		return obj;
	}

}
