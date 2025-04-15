package com.estsoft.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
		// 스프링 컨테이너가 관리하는 빈 조회
		ApplicationContext context = new AnnotationConfigApplicationContext(DemoApplication.class);
		String[] beanList = context.getBeanDefinitionNames();

		Arrays.stream(beanList).forEach(System.out::println);
	}

}
