package com.example.demo.APITests;

import com.example.demo.api.GetByIdTEST;
import com.example.demo.util.BeanUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RunWith(SpringRunner.class)
@SpringBootTest
public class testAPI {
	@Autowired
	BeanUtil beanUtil;

	/**
	 * @Description: 测试对字段的反射加载
	 */
	@Test
	public void testAPILoadField() throws InvocationTargetException, IllegalAccessException, IntrospectionException {
		GetByIdTEST api = (GetByIdTEST) beanUtil.getBean("getByIdTEST");

		Class clazz = api.getClass();

		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			String key = field.getName();
			PropertyDescriptor descriptor = new PropertyDescriptor(key, clazz);
			Method method = descriptor.getReadMethod();
			Object value = method.invoke(api);

			System.out.println(key + ":" + value);

		}
	}
}
