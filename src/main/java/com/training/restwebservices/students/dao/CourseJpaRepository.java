package com.training.restwebservices.students.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.restwebservices.students.model.Course;

public interface CourseJpaRepository extends JpaRepository<Course, Integer>{

}
