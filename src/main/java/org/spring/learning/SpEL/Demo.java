package org.spring.learning.SpEL;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class Demo {

	@Test
	public void test() {
		/** 直接量表达式(5*20/3) */
		String str1 = "3 * 4 / 3";
		/** 创建表达式解析器 */
		ExpressionParser ep = new SpelExpressionParser();
		/** 解析表达式 */
		Expression ex = ep.parseExpression(str1);
		/** 获取表达式的值 */
		System.out.println(ex.getValue());

		/** 表达式中创建数组(new int[2]或new int[]{10,20}) */
		String str2 = "new String[]{'Struts2', 'Hibernate4', 'Spring4'}";
		ex = ep.parseExpression(str2);
		String[] arr = (String[]) ex.getValue();
		System.out.println(Arrays.toString(arr));

		/** 表达式创建List集合({ele1,ele2,ele3,...}) */
		String str3 = "{10,20,30}";
		ex = ep.parseExpression(str3);
		List<Integer> lists = (List<Integer>) ex.getValue();
		System.out.println(lists);

		/** 表达式创建Map集合({key:value,key:value,...}) */
		String str4 = "{'name' : '李刚', 'age' : 20}";
		ex = ep.parseExpression(str4);
		Map<String, Object> maps = (Map<String, Object>) ex.getValue();
		System.out.println(maps);

		/** 调用方法(T(类名)) 类型运算符 T(java.lang.System).out.println('') */
		String str5 = "T(java.lang.System).out.println('中国')";
		String str6 = "T(java.lang.Math).random()";
		ex = ep.parseExpression(str6);
		System.out.println(ex.getValue());

		/** 调用构造器 new java.util.Date() */
		String str7 = "new java.util.Date()";
		ex = ep.parseExpression(str7);
		Date date = (Date) ex.getValue();
		System.out.println(date);

		/** 变量 #变量名.方法 */
		Date d = new Date();
		/** 创建赋值容器 */
		EvaluationContext ec = new StandardEvaluationContext();
		/** 把对象存入赋值容器 */
		ec.setVariable("a", d);

		String str8 = "#a.getYear()";
		/** 解析表达式 */
		ex = ep.parseExpression(str8);
		System.out.println((int) ex.getValue(ec) + 1900);
	}
}
