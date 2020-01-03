package org.spring.learning.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class AutoproxyAdvise {

	/**
	 * 调用目标方法之前 JoinPoint: 连接点，它封装了当前方法的所有信息
	 */
	//@Before(value="execution(* cn.itcast.service.*.*(..))")
	public void before(JoinPoint jp) {
		/** Signature: 方法签名对象 (封装方法说明信息) */
		System.out.println("开始调用的方法：" + jp.getSignature().getName());
		System.out.println("调用该方法实参：" + Arrays.toString(jp.getArgs()));
		System.out.println("目标对象：" + jp.getTarget());
		System.out.println("==========调用方法之前===============");
	}

	/**
	 * 调用目标方法之后 JoinPoint: 连接点，它封装了当前方法的所有信息
	 */
	//@After("execution(* cn.itcast.service.*.*(..))")
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
	//@AfterReturning(pointcut="execution(* cn.itcast.service.*.*(..))", returning="obj")
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
	//@AfterThrowing(pointcut="execution(* cn.itcast.service.*.*(..))", throwing="ex")
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
	@Around("execution(* cn.itcast.service.*.*(..))")
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
