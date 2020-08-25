
Spring有关问题

BeanFactory和ApplicationContext有什么区别
	BeanFactory和ApplicationContext是Spring的两大核心接口，都可以当做Spring的容器。
	其中ApplicationContext是BeanFactory的子接口。
	
	BeanFactory：是Spring里面最底层的接口
	包含了各种Bean的定义，读取bean配置文档，管理bean的加载、实例化，控制bean的生命周期，维护bean之间的依赖关系
	ApplicationContext接口作为BeanFactory的派生，除了提供BeanFactory所具有的功能外，还提供了更完整的框架功能
		①继承MessageSource，因此支持国际化。
		②统一的资源文件访问方式。
		③提供在监听器中注册bean的事件。
		④同时加载多个配置文件。
		⑤载入多个（有继承关系）上下文 ，使得每一个上下文都专注于一个特定的层次，比如应用的web层。
		
	BeanFactroy采用的是延迟加载形式来注入Bean的，即只有在使用到某个Bean时(调用getBean())，才对该Bean进行加载实例化。
	这样，我们就不能发现一些存在的Spring的配置问题。如果Bean的某一个属性没有注入，BeanFacotry加载后，直至第一次使用调用getBean方法才会抛出异常。





Spring Bean的生命周期


Spring Bean的作用域
	singleton 单例
	prototype 从容器中调用Bean, 返回一个实例
	request   每次HTTP请求创建一个Bean, 适用于WebApplicationContext环境
	session   同一个HTTP Session共享一个Bean, 适用于WebApplicationContext环境
	globalSession  一般应用与Porlet应用环境, 适用于WebApplicationContext环境
	
	

Spring框架中的单例Beans是线程安全的吗
构造方法注入和设值注入有什么区别
Spring框架中有哪些不同类型的事件
FileSystemResource和ClassPathResource有何区别
Spring 框架中都用到了哪些设计模式
Spring的优点





















