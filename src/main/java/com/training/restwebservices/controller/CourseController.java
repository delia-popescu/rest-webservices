package com.training.restwebservices.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.training.restwebservices.exception.CourseNotFoundException;
import com.training.restwebservices.model.Course;
import com.training.restwebservices.service.CourseService;

@RestController
public class CourseController {
	
	@Autowired
	private CourseService courseDao;

	@GetMapping(path="/courses")
	public List<Course> getAllCourses() {
		return courseDao.findAll();
	}
	
	@GetMapping(path="/courses/{id}")
	public Course getCourseById(@PathVariable Integer id) {
		Course course = courseDao.findById(id);
		if(course == null) {
			throw new CourseNotFoundException(String.format("Course with id %s doesn't exist", id));
		}
		return course;
	}
	
	@DeleteMapping(path="/delete/courses/{id}")
	public Course removeCourseById(@PathVariable Integer id) {
		ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(id).toUri();
		return courseDao.removeById(id);
	}
	
	@PostMapping(path="/courses")
	public ResponseEntity<Course> addCourse(@Valid @RequestBody Course course) {
		courseDao.add(course);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(course.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
}