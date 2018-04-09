package com.training.restwebservices.students.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.restwebservices.students.model.Student;

public interface StudentJpaRepository extends JpaRepository<Student, Integer>{

}
