package com.repository;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.entity.Student;

public class StudentRepositoryTest {

	private ApplicationContext ac = null;
	StudentRepository studentRepository = null;

	@Before
	public void setup() {
		ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		studentRepository = ac.getBean(StudentRepository.class);
	}

	@After
	public void teardown() {
		ac = null;

	}

	// 根据id查询Student
	@Test
	public void findById() {
		Student student = studentRepository.findById(1);
		System.out.println(student);
	}

	// 以姓名以test开头并且年龄小于23
	@Test
	public void findByNameStartingWithAndAgeLessThan() {
		List<Student> students = studentRepository.findByNameStartingWithAndAgeLessThan("test", 23);
		for (Student student : students) {
			System.out.println(student);
		}
	}

	// 以姓名以 n 结尾或者年龄大于23
	@Test
	public void findByNameEndingWithOrAgeGreaterThan() {
		List<Student> students = studentRepository.findByNameEndingWithOrAgeGreaterThan("n", 23);
		for (Student student : students) {
			System.out.println(student);
		}
	}

	// 年龄大于多少 where age > ?
	@Test
	public void findByAgeGreaterThan() {
		List<Student> students = studentRepository.findByAgeGreaterThan(23);
		for (Student student : students) {
			System.out.println(student);
		}
	}

	// 姓名为test1和test2
	@Test
	public void findByNameIn() {
		String[] names = { "test1", "test2" };
		List<Student> students = studentRepository.findByNameIn(names);
		for (Student student : students) {
			System.out.println(student);
		}
	}

	// Query注解查询，查询id号为最大的学生
	@Test
	public void getStudentById() {
		Student student = studentRepository.getStudentById();
		System.out.println(student);
	}

	// Query注解查询(索引参数占位符),查询姓名为 test2 年龄为21的Student
	@Test
	public void getStudentByParams1() {
		Student student = studentRepository.getStudentByParams1("test2", 21);
		System.out.println(student);
	}

	// Query注解查询（命名参数占位符）,查询姓名为 test2 年龄为21的Student
	@Test
	public void getStudentByParams2() {
		Student student = studentRepository.getStudentByParams2("test2", 21);
		System.out.println(student);
	}

	// Query注解查询,查询姓名包含 test 的Student
	@Test
	public void queryLike1() {
		List<Student> students = studentRepository.queryLike1("test");
		for (Student student : students) {
			System.out.println(student);
		}
	}

	// Query注解查询,获得总记录数
	@Test
	public void getCount() {
		Long count = studentRepository.getCount();
		System.out.println("count:" +count);
	}
}
