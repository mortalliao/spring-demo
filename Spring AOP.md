
Spring AOP有关问题


什么是 AOP? 
	AOP即面向切面编程
	使用AOP技术, 可以将一些系统性相关的编程工作, 独立提取出来, 独立实现, 然后通过切面切入进系统
	权限管理, 事务管理, 日志记录



point cut, advice, Join point是什么?
	AOP相关的概念
		1) Aspect:切面, 切入系统的一个切面。比如事务管理是一个切面, 权限管理也是一个切面；
		2) Join point:连接点, 也就是可以进行横向切入的位置；
		3) Advice:通知, 切面在某个连接点执行的操作
			(分为: Before advice , After returning advice , After throwing advice , After (finally) advice , Around advice )
		4) Pointcut: 切点, 符合切点表达式的连接点, 也就是真正被切入的地方；


join point 和 point cut 的区别?





AOP 的实现原理

AOP分为静态AOP和动态AOP。
静态AOP是指AspectJ实现的AOP, 他是将切面代码直接编译到Java类文件中。
动态AOP是指将切面代码进行动态织入实现的AOP。

Spring的AOP为动态AOP, 实现的技术为： JDK提供的动态代理技术 和 CGLIB(动态字节码增强技术) 。尽管实现技术不一样, 但 都是基于代理模式 ,  都是生成一个代理对象




谈谈对SpringAOP Weaving(织入)的理解?



谈谈SpringAOP Introduction(引入)的理解?




讲解JDK 动态代理和 CGLIB 代理原理以及区别?

	JDK动态代理
		主要使用到 InvocationHandler 接口和 Proxy.newProxyInstance() 方法。
		JDK动态代理要求被代理实现一个接口, 只有接口中的方法才能够被代理 。
		
		其方法是将被代理对象注入到一个中间对象, 而中间对象实现InvocationHandler接口, 
		在实现该接口时, 可以在 被代理对象调用它的方法时, 在调用的前后插入一些代码。
		而 Proxy.newProxyInstance() 能够利用中间对象来生产代理对象。
		插入的代码就是切面代码。所以使用JDK动态代理可以实现AOP。

	CGLIB(code generate libary)
		字节码生成技术实现AOP, 其实就是继承被代理对象, 然后Override需要被代理的方法, 在覆盖该方法时, 自然是可以插入我们自己的代码的
		因为需要Override被代理对象的方法, 所以自然CGLIB技术实现AOP时, 就 必须要求需要被代理的方法不能是final方法, 因为final方法不能被子类覆盖 




讲解Spring 框架中基于 Schema 的 AOP 实现原理?

`<aop:aspect>`声明一个aspect
```
<aop:config>
    <aop:aspect id="myAspect" ref="aBean">
        ...
    </aop:aspect>
</aop:config>
 
<bean id="aBean" class="...">
    ...
</bean>
```

`<aop:pointcut>`声明一个pointcut
pointcut就是符合插入aspect的条件的包名或类名。运行时, 执行到此处, 会插入关联的aspect
```
<aop:config>
    <aop:pointcut id="businessService"
        expression="com.jdsu.nc.portal.SystemArchitecture.businessService()"/>
</aop:config>
```

<aop:pointcut>可以作为<aop:config>的直接子元素, 声明通用的顶级pointcut
也可以作为<aop:aspect>的子元素, 为一个确切的aspect声明专用的pointcut
```
<aop:config>
    <aop:aspect id="myAspect" ref="aBean">
        <aop:pointcut id="businessService"
            expression="execution(* com.jdsu.nc.portal.service.*.*(..))"/>
        ...
    </aop:aspect>
</aop:config>
```


为一个aspect声明`advice`
声明advice的时候, 必须给出pointcut和被插入执行的method
Spring AOP支持5种advice, 包括<aop:before>, <aop:after>, <aop:after-returning>, <aop:after-throwing>, <aop:around>
当其他advice都不合适的情况下, 才使用<aop:around>


```
<bean id="aspectBean" class="net.aazj.aop.DataSourceInterceptor"/>
<aop:config>
    <aop:aspect id="dataSourceAspect" ref="aspectBean">
        <aop:pointcut id="dataSourcePoint" expression="execution(public * net.aazj.service..*.getUser(..))" />
        <aop:pointcut expression="" id=""/>
        <aop:before method="before" pointcut-ref="dataSourcePoint"/>
        <aop:after method=""/>
        <aop:around method=""/>
    </aop:aspect>
    <aop:aspect></aop:aspect>
</aop:config>
```

```
<aop:aspect> 配置一个切面；

<aop:pointcut>配置一个切点，基于切点表达式；

<aop:before>,<aop:after>,<aop:around>是定义不同类型的advise. 

aspectBean 是切面的处理bean
```

```
public class DataSourceInterceptor {
    public void before(JoinPoint jp) {
        DataSourceTypeManager.set(DataSources.SLAVE);
    }
}
```




讲解Spring 框架中如何基于 AOP 实现的事务管理?


<!-- 定义事务管理器 -->    
<bean id="transactionManager"    
    class="org.springframework.jdbc.datasource.DataSourceTransactionManager">    
    <property name="dataSource" ref="dataSource" />    
</bean>  

<!-- 配置事务的属性 -->    
<tx:advice id="TestAdvice" transaction-manager="transactionManager">    
    <!--配置事务传播性，隔离级别以及超时回滚等问题 -->    
    <tx:attributes>    
        <tx:method name="search*" propagation="REQUIRED" read-only="true" isolation="DEFAUT" TIMEOUT="-1" />    
        <tx:method name="del*" propagation="REQUIRED" />    
        <tx:method name="update*" propagation="REQUIRED" />    
        <tx:method name="add*" propagation="REQUIRED" />    
    </tx:attributes>    
</tx:advice>


read-only:设置该事务中是否允许修改数据。（对于只执行查询功能的事务，设置为TRUE可以提高事务的执行速度）  

propagation：事务的传播机制。一般设置为required。可以保证在事务中的代码只在当前事务中运行，防止创建多个事务。

isolation:事务隔离级别。不是必须的。默认值是default。

timeout:允许事务运行的最长时间，以秒为单位。

rollback-for:触发回滚的异常。

no-rollback-for:不会触发回滚的异常。



<aop:config>    
    <!--配置事务切点 -->    
    <aop:pointcut id="services"    
        expression="execution(public* com.pb.service.*.*(..))" />    
    <aop:advisor pointcut-ref="services" advice-ref="TestAdvice" />    
</aop:config> 


1. 配置事务管理类
2. 配置事务属性
3. 配置事务的AOP切入点















