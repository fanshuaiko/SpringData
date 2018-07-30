package com.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.entity.Student;
/**
 * 接口是可以多继承的
 * JpaSpecificationExecutor<T>这个接口一般和其他接口一起使用，用作查询条件加强，直接注入到Services会报错（无法找到这个Bean）
 * 这里再继承这个接口就是在分页查询的时候再添加一些条件
 * @author fan
 * 创建时间：2018年6月5日
 */
public interface StudentPagingAndSortingRepository extends PagingAndSortingRepository<Student, Integer>
															,JpaSpecificationExecutor<Student>{
	
}
