package com.services;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.entity.Student;
import com.repository.StudentCrudRepository;
import com.repository.StudentPagingAndSortingRepository;
import com.repository.StudentRepository;

@Service
@Transactional
public class StudentServices {
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	StudentCrudRepository studentCrudRepository;
	@Autowired
	StudentPagingAndSortingRepository studentPagingAndSortingRepository;
	// 不能单独注入只继承JpaSpecificationExecutor<T>的接口，该接口只能和其他接口一起被继承才能使用，用作查询条件加强（即更加复杂的条件）
	// @Autowired
	// StudentJpaSpecificationExecutor studentJpaSpecificationExecutor;

	public void updateStudentByid(String name, Integer id) {
		studentRepository.updateStudentById(name, id);
	}

	// 直接调用的是StudentCrudRepository中的saveAll（）方法，而不是自己写的方法
	public void saveAll(List<Student> students) {
		studentCrudRepository.saveAll(students);
	}

	// 根据第几页和每页查询记录数来分页查询
	public Page<Student> faindAll1(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);// 分页后根据id降序排列
		Page<Student> studentPage = studentPagingAndSortingRepository.findAll(pageable);
		return studentPage;
	}

	// 根据第几页、每页查询记录数、排序方式和根据哪个字段排序来分页查询
	public Page<Student> faindAll2(int page, int size, Direction direction, String properties) {
		Pageable pageable = PageRequest.of(page, size, direction, properties);// 分页后根据id降序排列
		Page<Student> studentPage = studentPagingAndSortingRepository.findAll(pageable);
		return studentPage;
	}

	//该方法是在上一个分页并排序方法的基础上，多继承了一个JpaSpecificationExecutor<T>接口，该接口可以用作查询条件加强（即更加复杂的条件）
	//下面就是在上一个分页并排序方法的基础上，添加了年龄大于10岁的条件
	public Page<Student> faindAll3(int page, int size, Direction direction, String properties) {

		Pageable pageable = PageRequest.of(page, size, direction, properties);// 分页后根据id降序排列
		Specification<Student> spec = new Specification<Student>() {
			private static final long serialVersionUID = 1L;

			public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				Expression<? extends Number> path = root.get("age");
				return criteriaBuilder.gt(path, 10);
			}
		};

		Page<Student> studentPage = studentPagingAndSortingRepository.findAll(spec, pageable);
		return studentPage;
	}

	/**
	 * 
	 * Setter方法
	 */

	public void setStudentRepository(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public void setStudentCrudRepository(StudentCrudRepository studentCrudRepository) {
		this.studentCrudRepository = studentCrudRepository;
	}

	public void setStudentPagingAndSortingRepository(
			StudentPagingAndSortingRepository studentPagingAndSortingRepository) {
		this.studentPagingAndSortingRepository = studentPagingAndSortingRepository;
	}

}
