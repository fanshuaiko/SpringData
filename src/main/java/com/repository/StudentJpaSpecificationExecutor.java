package com.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.entity.Student;
/**
 * JpaSpecificationExecutor<T>这个接口一般和其他接口一起使用，用作查询条件加强，直接注入到Services会报错（无法找到这个Bean）
 * @author fan
 * 创建时间：2018年6月5日
 */
public interface StudentJpaSpecificationExecutor extends JpaSpecificationExecutor<Student>{

}
