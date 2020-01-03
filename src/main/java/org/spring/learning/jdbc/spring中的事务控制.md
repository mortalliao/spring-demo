
# spring中的事务控制

## Spring事务控制我们要明确的
第一: JavaEE体系进行分层开发, 事务处理位于业务层, Spring提供了分层设计业务层的事务处理解决方案
第二: spring框架为我们提供了一组事务控制的接口, 这组接口是在spring-tx-5.2.0.RLEASE.jar中
第三: spring的事务控制都是基于AOP的, 它既可以使用编程的方式实现, 也可以使用配置的方式实现

spring中事务控制    PlatformTransactionManager  (是一个接口)

此接口是spring的事务管理器,它里面提供了操作事务的方法

### PlatformTransactionManager接口提供事务操作的方法
包含3个具体的操作
-  获取事务状态信息
	- TransactionStatus getTransaction(TransactionDefinition definition)
- 提交事务
	- void commit(TransactionStatus status)
- 回滚事务
	- rollback(TransactionStatus status)

在开发中都是使用它的实现类 如:
真正管理事务的对象
org.springframework.jdbc.datasource.DataSourceTransactionManager 使用Spring JDBC或iBatis 进行持久化数据时使用
org.springframework.orm.hibernate5.HibernateTransactionManager   使用Hibernate版本进行持久化数据时使用

### TransactionDefinition 事务的定义信息
事务的定义信息, 里面有如下方法
获取事务对象名称
	- String getName()
获取事务隔离级别
	- int getIsolationLevel()
获取事务传播行为
	- int getPropagationBehavior()
获取事务超时时间
	- int getTimeout()
获取事务是否只读
	- boolean isReadOnly()

#### 事务的隔离级别
事务隔离级别反映事务提交并发访问时的处理态度
- ISOLATION_DEFAULT
	- 默认级别, 归属下列某一种
- ISOLATION_READ_UNCOMMITTED
	- 可以读取未提交数据
- ISOLATION_READ_COMMITTED
	- 只能读取已提交数据, 解决脏读问题(Oracle默认级别)
- ISOLATION_REPEATABLE_READ
	- 是否读取其他事务提交修改后的数据, 解决不可重复读问题(MySQL默认级别)
- ISOLATION_SERIALIZABLE
	- 是否读取其他事务提交添加后的数据, 解决幻读问题

#### 事务的传播行为
REQUIRED: 如果当前没有事务, 就新建一个事务, 如果已经存在一个事务中, 加入到这个事务, 一般的选择(默认值)
SUPPORTS: 支持当前事务, 如果当前没有事务, 就以非事务方式执行(没有事务)
MANDATORY: 使用当前的事务, 如果当前没有事务, 就抛出异常
REQUERS_NEW: 新建事务, 如果当前在事务中, 把当前事务挂起
NOT_SUPPORTED: 以非事务方式执行操作, 如果当前存在事务, 就把当前事务挂起
NEVER: 以非事务方式运行, 如果当前存在事务, 就抛出异常
NESTED: 如果当前存在事务, 则在嵌套事务内执行, 如果当前没有事务, 则执行REQUIRED类似的操作

#### 超过时间
默认值是-1,没有超过限制.如果有,以秒为单位进行设置.

#### 是否是只读事务
建议查询时设置为只读.


### TransactionStatus
此接口提供的是事务具体的运行状态, 方法介绍如下
TransactionStatus接口描述了某个时间点上事务对象的状态信息, 包含6个具体的操作
- 刷新事务
	- void flush()
- 获取是否是存在存储点
	- boolean hasSavepoint()
- 获取事务是否完成
	- boolean isCompleted()
-获取事务是否为新的事务
	- boolean isNewTransaction()
-获取事务是否回滚
	- boolean isRollbackOnly()
-设置事务回滚
	- void setRollbackOnly()






























