package com.services;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;

import com.entity.Student;

public class StudentServicesTest {

	private ApplicationContext ac = null;
	StudentServices studentServices = null;

	@Before
	public void setup() {
		ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		studentServices = ac.getBean(StudentServices.class);
	}

	@After
	public void teardown() {
		ac = null;

	}

	// 根据id来更新姓名
	@Test
	public void updateStudentByid() {
		studentServices.updateStudentByid("tttt", 2);
	}

	// 保存所有的对象
	@Test
	public void saveAll() {
		List<Student> students = new ArrayList<Student>();
		Student student = null;
		for (int i = 1; i <= 20; i++) {
			student = new Student();
			student.setName("test" + i);
			student.setAge(i);
			students.add(student);
		}
		studentServices.saveAll(students);
	}

	// 根据第几页和每页查询记录数来分页查询
	@Test
	public void findAll1() {
		// 该方法中页数是从0开始查的，即第0页就是第一页
		Page<Student> page = studentServices.faindAll1(6, 3);
		System.out.println("查询的总页数" + page.getTotalPages());
		System.out.println("查询的总记录数" + page.getTotalElements());
		// 当前页数是从0开始查的，所以加一后才是真正的页数
		System.out.println("查询的当前第几页" + (page.getNumber() + 1));
		System.out.println("查询的当前页面的记录数" + page.getNumberOfElements());
		System.out.println("查询的排序方式" + page.getSort());
		System.out.println("查询的当前页面的对象集合" + page.getContent());
		System.out.println("查询的每页显示多少条记录" + page.getSize());

	}

	// 根据第几页、每页查询记录数、排序方式和根据哪个字段排序来分页查询
	@Test
	public void findAll2() {
		Page<Student> page = studentServices.faindAll2(0, 6, Direction.DESC, "id");
		System.out.println("查询的总页数" + page.getTotalPages());
		System.out.println("查询的总记录数" + page.getTotalElements());
		// 当前页数是从0开始查的，所以加一后才是真正的页数
		System.out.println("查询的当前第几页" + (page.getNumber() + 1));
		System.out.println("查询的当前页面的记录数" + page.getNumberOfElements());
		System.out.println("查询的排序方式" + page.getSort());
		System.out.println("查询的当前页面的对象集合" + page.getContent());
		System.out.println("查询的每页显示多少条记录" + page.getSize());
	}

	// 该方法是在上一个分页并排序方法的基础上，多继承了一个JpaSpecificationExecutor<T>接口，该接口可以用作查询条件加强（即更加复杂的条件）
	// 下面就是在上一个分页并排序方法的基础上，添加了年龄大于10岁的条件
	@Test
	public void findAll3() {
		Page<Student> page = studentServices.faindAll3(0, 6, Direction.DESC, "id");

		System.out.println("查询的总页数" + page.getTotalPages());
		System.out.println("查询的总记录数" + page.getTotalElements());
		// 当前页数是从0开始查的，所以加一后才是真正的页数
		System.out.println("查询的当前第几页" + (page.getNumber() + 1));
		System.out.println("查询的当前页面的记录数" + page.getNumberOfElements());
		System.out.println("查询的排序方式" + page.getSort());
		System.out.println("查询的当前页面的对象集合" + page.getContent());
		System.out.println("查询的每页显示多少条记录" + page.getSize());
	}
}
