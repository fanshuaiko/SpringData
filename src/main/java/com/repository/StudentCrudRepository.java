package com.repository;

import org.springframework.data.repository.CrudRepository;

import com.entity.Student;

public interface StudentCrudRepository extends CrudRepository<Student, Integer>{

	
	
}
