package com.training.restwebservices.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.training.restwebservices.model.Course;

@Repository
public class CourseService {

	public static List<Course> courses = new ArrayList<Course>();

	static {
		courses.add(new Course(1, "Data structures & algorithms", "Data structures & algorithms"));
		courses.add(new Course(2, "OOP programming", "OOP programming"));
	}

	public List<Course> findAll() {
		return courses;
	}

	public Course findById(Integer id) {
		return courses.stream().filter(course -> course.getId() == id).findFirst().orElse(null);
	}

	public Course removeById(Integer id) {
		Course course = findById(id);
		if(course != null) {
			courses.remove(course);
		}
		return course;
	}

	public Course add(Course course) {
		if (course.getId() != null) {
			course.setId(courses.size() + 1);
		}
		courses.add(course);
		return course;
	}
}
