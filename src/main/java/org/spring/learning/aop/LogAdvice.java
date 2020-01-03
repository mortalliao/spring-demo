package org.spring.learning.aop;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

public class LogAdvice implements MethodBeforeAdvice, AfterReturningAdvice {

	/** 调用目标方法之前 */
	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		/**
		 * method: 目标方法 args: 实参数组 target: 目标对象
		 */
		System.out.println("开始调用的方法：" + method.getName());
		System.out.println("调用该方法的实参：" + Arrays.toString(args));
		System.out.println("目标对象：" + target);
		System.out.println("==========调用方法之前===============");

	}

	/** 调用目标方法有返回值之后 */
	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		/**
		 * returnValue: 返回值 method: 目标方法 args: 实参数组 target: 目标对象
		 */
		System.out.println("==========调用方法有返回值之后===============");
		System.out.println("返回值：" + returnValue);
		System.out.println("结束调用的方法：" + method.getName());
		System.out.println("调用该方法的实参：" + Arrays.toString(args));
		System.out.println("目标对象：" + target);
	}

	/**
	 * 调用目标方法之前 JoinPoint: 连接点，它封装了当前方法的所有信息
	 */
	public void before(JoinPoint jp, Object a, Object b) {
		System.out.println(a + "===" + b);
		/** Signature: 方法签名对象 (封装方法说明信息) */
		System.out.println("开始调用的方法：" + jp.getSignature().getName());
		System.out.println("调用该方法实参：" + Arrays.toString(jp.getArgs()));
		System.out.println("目标对象：" + jp.getTarget());
		System.out.println("==========调用方法之前===============");
	}

	/**
	 * 调用目标方法之后 JoinPoint: 连接点，它封装了当前方法的所有信息
	 */
	public void after(JoinPoint jp) {
		System.out.println("==========调用方法之后===============");
		/** Signature: 方法签名对象 (封装方法说明信息) */
		System.out.println("调用的方法：" + jp.getSignature().getName());
		System.out.println("调用该方法实参：" + Arrays.toString(jp.getArgs()));
		System.out.println("目标对象：" + jp.getTarget());
	}

	/**
	 * 调用目标方法有返回值之后 JoinPoint: 连接点，它封装了当前方法的所有信息
	 */
	public void afterReturning(JoinPoint jp, Object obj) {
		System.out.println("==========调用方法有返回值之后===============");
		/** Signature: 方法签名对象 (封装方法说明信息) */
		System.out.println("返回值: " + obj);
		System.out.println("调用的方法：" + jp.getSignature().getName());
		System.out.println("调用该方法实参：" + Arrays.toString(jp.getArgs()));
		System.out.println("目标对象：" + jp.getTarget());
	}

	/**
	 * 调用目标方法有异常之后 JoinPoint: 连接点，它封装了当前方法的所有信息
	 */
	public void afterThrowing(JoinPoint jp, Throwable ex) {
		System.out.println("==========调用目标方法有异常之后===============");
		/** Signature: 方法签名对象 (封装方法说明信息) */
		System.out.println("调用的方法：" + jp.getSignature().getName());
		System.out.println("调用该方法实参：" + Arrays.toString(jp.getArgs()));
		System.out.println("目标对象：" + jp.getTarget());
		System.out.println(ex.getMessage());
		ex.printStackTrace();

	}

	/**
	 * 调用目标方法前后都进入 ProceedingJoinPoint: 处理连接点对象
	 * 
	 * @throws Throwable
	 */
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("==========调用目标方法之前===============");
		/** 获取目标方法的实参数组 */
		Object[] args = pjp.getArgs();
		args[0] = "test888";
		args[1] = "888888";
		/** 调用目标方法 */
		Object res = pjp.proceed(args);
		System.out.println("==========调用目标方法之后===============");
		return res;
	}
}
