
Spring有关问题

BeanFactory和ApplicationContext有什么区别
	BeanFactory和ApplicationContext是Spring的两大核心接口，都可以当做Spring的容器。
	其中ApplicationContext是BeanFactory的子接口。
	
	1. BeanFactory：是Spring里面最底层的接口
	包含了各种Bean的定义，读取bean配置文档，管理bean的加载、实例化，控制bean的生命周期，维护bean之间的依赖关系
	ApplicationContext接口作为BeanFactory的派生，除了提供BeanFactory所具有的功能外，还提供了更完整的框架功能
		①继承MessageSource，因此支持国际化。
		②统一的资源文件访问方式。
		③提供在监听器中注册bean的事件。
		④同时加载多个配置文件。
		⑤载入多个(有继承关系)上下文 ，使得每一个上下文都专注于一个特定的层次，比如应用的web层。
		
	2. BeanFactroy采用的是延迟加载形式来注入Bean的，即只有在使用到某个Bean时(调用getBean())，才对该Bean进行加载实例化。
	这样，我们就不能发现一些存在的Spring的配置问题。如果Bean的某一个属性没有注入，BeanFacotry加载后，直至第一次使用调用getBean方法才会抛出异常。
		①ApplicationContext，它是在容器启动时，一次性创建了所有的Bean。
		这样，在容器启动时，我们就可以发现Spring中存在的配置错误，这样有利于检查所依赖属性是否注入。
		ApplicationContext启动后预载入所有的单实例Bean，通过预载入单实例bean ,确保当你需要的时候，你就不用等待，因为它们已经创建好了。
		②相对于基本的BeanFactory，ApplicationContext 唯一的不足是占用内存空间。当应用程序配置Bean较多时，程序启动较慢。

	3. BeanFactory通常以编程的方式被创建，ApplicationContext还能以声明的方式创建，如使用ContextLoader。
	
	4. BeanFactory和ApplicationContext都支持BeanPostProcessor、BeanFactoryPostProcessor的使用，
	但两者之间的区别是：BeanFactory需要手动注册，而ApplicationContext则是自动注册。



Spring Bean的生命周期
	1. 根据配置情况调用 Bean 构造方法或工厂方法实例化 Bean
	2. 利用依赖注入完成 Bean 中所有属性值的配置注入
	3. 如果 Bean 实现了 BeanNameAware 接口，则 Spring 调用 Bean 的 setBeanName() 方法传入当前 Bean 的 id值
	4. 如果 Bean 实现了 BeanFactoryAware 接口，则 Spring 调用 setBeanFactory() 方法传入当前工厂实例的引用
	5. 如果 Bean 实现了 ApplicationContextAware 接口，则 Spring 调用 setApplicationContext() 方法传入当前 ApplicationContext 实例的引用
	6. 如果 BeanPostProcessor 和 Bean 关联，则 Spring 将调用该接口的预初始化方法 postProcessBeforeInitialzation() 对 Bean 进行加工操作，此处非常重要，Spring 的 AOP 就是利用它实现的
	7. 如果 Bean 实现了 InitializingBean 接口，则 Spring 将调用 afterPropertiesSet() 方法
	8. 如果在配置文件中通过 init-method 属性指定了初始化方法，则调用该初始化方法
	9. 如果 BeanPostProcessor 和 Bean 关联，则 Spring 将调用该接口的初始化方法 postProcessAfterInitialization()。此时，Bean 已经可以被应用系统使用了
	10. 如果在 <bean> 中指定了该 Bean 的作用范围为 scope="singleton"，则将该 Bean 放入 Spring IoC 的缓存池中，将触发 Spring 对该 Bean 的生命周期管理；
		如果在 <bean> 中指定了该 Bean 的作用范围为 scope="prototype"，则将该 Bean 交给调用者，调用者管理该 Bean 的生命周期，Spring 不再管理该 Bean
	11. 如果 Bean 实现了 DisposableBean 接口，则 Spring 会调用 destory() 方法将 Spring 中的 Bean 销毁；如果在配置文件中通过 destory-method 属性指定了 Bean 的销毁方法，则 Spring 将调用该方法对 Bean 进行销毁



Spring Bean的作用域
	singleton 单例
	prototype 从容器中调用Bean, 返回一个实例
	request   每次HTTP请求创建一个Bean, 适用于WebApplicationContext环境
	session   同一个HTTP Session共享一个Bean, 适用于WebApplicationContext环境
	globalSession  一般应用与Porlet应用环境, 适用于WebApplicationContext环境



Spring框架中的单例Beans是线程安全的吗
	Spring框架并没有对单例bean进行任何多线程的封装处理。关于单例bean的线程安全和并发问题需要开发者自行去搞定
	singleton表示该bean全局只有一个实例，Spring中bean的scope默认也是singleton.
	prototype表示该bean在每次被注入的时候，都要重新创建一个实例，这种情况适用于有状态的Bean.



构造方法注入和设值注入有什么区别

1. 
在设值注入方法支持大部分的依赖注入，如果我们仅需要注入int, string和long型的变量，我们不要用设值的方法注入。
	对于基本类型，如果我们没有注入的话，可以为基本类型设置默认值。
在构造方法注入不支持大部分的依赖注入，因为在调用构造方法中必须传入正确的构造参数，否则的话为报错。
2. 
设值注入不会重写构造方法的值。如果我们对同一个变量同时使用了构造方法注入又使用了设置方法注入的话，那么构造方法将不能覆盖由设值方法注入的值。
很明显，因为构造方法尽在对象被创建时调用。
3.
在使用设值注入时有可能还不能保证某种依赖是否已经被注入，也就是说这时对象的依赖关系有可能是不完整的。
而在另一种情况下，构造器注入则不允许生成依赖关系不完整的对象
4. 
在设值注入时如果对象A和对象B互相依赖，在创建对象A时Spring会抛出ObjectCurrentlyInCreationException异常，因为在B对象被创建之前A对象是不能被创建的，反之亦然。
所以Spring用设值注入的方法解决了循环依赖的问题，因对象的设值方法是在对象被创建之前被调用的



Spring框架中有哪些不同类型的事件

	Spring 提供了以下5种标准的事件
		1. 上下文更新事件(ContextRefreshedEvent): 
			在调用ConfigurableApplicationContext 接口中的refresh()方法时被触发
		2. 上下文开始事件(ContextStartedEvent):
			当容器调用ConfigurableApplicationContext的Start()方法开始/重新开始容器时触发该事件
		3. 上下文停止事件(ContextStoppedEvent):
			当容器调用ConfigurableApplicationContext的Stop()方法停止容器时触发该事件
		4. 上下文关闭事件(ContextClosedEvent):
			当ApplicationContext被关闭时触发该事件。容器被关闭时，其管理的所有单例Bean都被销毁
		5. 请求处理事件(RequestHandledEvent):
			在Web应用中，当一个http请求(request)结束触发该事件

	如果一个bean实现了ApplicationListener接口，当一个ApplicationEvent 被发布以后，bean会自动被通知



FileSystemResource和ClassPathResource有何区别
	在FileSystemResource中需要给出spring-config.xml文件在你项目中的相对路径或者绝对路径
	在ClassPathResource中spring会在ClassPath中自动搜寻配置文件，所以要把ClassPathResource 文件放在ClassPath下
	
	ClassPathResource在环境变量中读取配置文件，
	FileSystemResource在配置文件中读取配置文件



Spring 框架中都用到了哪些设计模式

(1)工厂模式：BeanFactory就是简单工厂模式的体现，用来创建对象的实例；

(2)单例模式：Bean默认为单例模式。

(3)代理模式：Spring的AOP功能用到了JDK的动态代理和CGLIB字节码生成技术；

(4)模板方法：用来解决代码重复的问题。比如. RestTemplate, JmsTemplate, JpaTemplate。

(5)观察者模式：定义对象键一种一对多的依赖关系，当一个对象的状态发生改变时，所有依赖于它的对象都会得到通知被制动更



Spring的优点

	spring这个有两大概念：IOC和AOP
	
		IOC反转控制(也可以叫DI依赖注入，其实都是一个意思，角度不同而已)，
		就是之前对象依赖关系不用你来维护，由IOC容器来维护
		
		AOP也很好理解，面向切面编程，就是把一些公共的功能提取出来，到你用的时候你从容器中拿出对象直接用就可以了












