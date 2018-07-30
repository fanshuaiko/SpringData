package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.entity.Student;
/**
 *有两种方式可以继承Repository
 *查询方法的定义规则和使用详细见官网，这里只是列出几个例子
 *当涉及到保存、更新和删除的时候就要涉及到事务的操作，和之前的项目中使用的一样，将事务的注解添加在services层中
 * @author fan
 * 创建时间：2018年6月4日
 */
//第一种方式：使用注解
//@RepositoryDefinition(domainClass = Student.class, idClass = Integer.class)
//public interface StudentRepository {


/**
 * 查询方法的定义规则和使用详细见官网，这里只是列出几个例子
 * 
 * 这种方法命名规则的弊端： 1.方法名会比较长 约定大于配置 2.对于一些复杂的查询很难实现 可以通过 @Query 注解来实现复杂的查询
 * 
 * @author fan 创建时间：2018年6月4日
 */

//第二种方式：extends Repository<Student, Integer>
public interface StudentRepository  extends Repository<Student, Integer>{
	
	// 根据id来找Student
	public Student findById(Integer id);

	// 查询条件：姓名以什么开始并且年龄小于多少 where name like %? and age < ?
	public List<Student> findByNameStartingWithAndAgeLessThan(String name, Integer age);

	// 查询条件：姓名以什么结尾或者年龄大于多少 where name like %? and age > ?
	public List<Student> findByNameEndingWithOrAgeGreaterThan(String name, Integer age);

	// 查询条件：年龄大于多少 where age > ?
	public List<Student> findByAgeGreaterThan(Integer age);

	// 查询条件：姓名为test1和test2 where name in(test1,test2)
	public List<Student> findByNameIn(String[] names);

	@Query(" from Student s where s.id = (select max(id) from Student)")
	public Student getStudentById();

	// 索引参数占位符
	@Query(" from Student s where s.name = ?1 and s.age = ?2")
	public Student getStudentByParams1(String name, Integer age);

	// 命名参数占位符
	@Query(" from Student s where s.name = :name and s.age = :age")
	public Student getStudentByParams2(@Param("name") String name, @Param("age") Integer age);
	
	//使用模糊查询
	@Query("from Student s where s.name like %?1%")
	public List<Student> queryLike1(String name);
	
	//原生查询（即sql）,这的Student不是类名，而是数据库中的表名
	@Query(nativeQuery=true,value="select count(1) from Student")
	public Long getCount();
	
	/**
	 * 当涉及到更新和删除的时候就要涉及到事务的操作，和之前的项目中使用的一样，将事务的注解添加在services层中
	 */
	//当使用保存、更新和删除的时候还要在方法上加上@Modifying注解
	@Modifying
	@Query("update Student s set s.name = ?1 where s.id = ?2")
	public void updateStudentById(String name,Integer id);
 }
