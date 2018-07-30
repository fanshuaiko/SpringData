package com.entity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StudentTest {
	@SuppressWarnings("unused")
	private ApplicationContext ac = null;

	@Before
	public void setup() {
		ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.println("进入setup");
	}

	@After
	public void teardown() {
		ac = null;
		System.out.println("进入teardown");

	}

	@Test
	public void test() {
		System.out.println("进入test");

	}
}
