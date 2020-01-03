package org.spring.learning.componentscan;

import org.springframework.context.annotation.ComponentScan;

/**
 * 
 *<pre>
@ComponentScan：会自动扫描包路径下面的所有@Controller、@Service、@Repository、@Component 的类
	属性： 
		value/basePackages: 指定一组要扫描的包,将扫描这些包及其子包下的文件.(默认基包为配置类所在的包)
		classes: 直接指定扫描的一些类,这些类所在的包及其子包也会被扫描
		includeFilters: 指定扫描的时候，只包含某些类，
		excludeFilters: 不包含那些过滤，
		useDefaultFilters默认的过滤规则是开启的，如果我们要自定义的话是要关闭的。
		@Filters 指过滤规则，FilterType指定过滤的规则（ 
		    FilterType.ANNOTATION：按照注解
		    FilterType.ASSIGNABLE_TYPE：按照给定的类型；
		    FilterType.ASPECTJ：使用ASPECTJ表达式
		    FilterType.REGEX：使用正则指定
		    FilterType.CUSTOM：使用自定义规则）
 * </pre>
 */

@ComponentScan(value = "org.spring.learning.componentscan",
		//排除某些类
		excludeFilters = {
		//排除Controller注解标注的类 
		//	@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = { Controller.class }),
		//排除指定类型的类 
		//	@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {UserService.class }) 
		})
public class ScanConfig {

}
