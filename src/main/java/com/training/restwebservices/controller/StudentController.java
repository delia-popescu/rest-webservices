package com.training.restwebservices.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.training.restwebservices.exception.StudentNotFoundException;
import com.training.restwebservices.model.Course;
import com.training.restwebservices.model.Student;
import com.training.restwebservices.service.StudentService;

@PreAuthorize("hasRole(ADMIN)")
@RestController
public class StudentController {

	@Autowired
	private StudentService studentDao;

	@GetMapping(path = "/students")
	public List<Student> getAllStudents() {
		return studentDao.findAll();
	}

	//HATEAOAS endpoint
	@GetMapping(path = "/students/{id}")
	public Resource<Student> getStudentById(@PathVariable int id) {
		Student student = studentDao.findById(id);
		if (student == null) {
			throw new StudentNotFoundException(String.format("Student with id %d doesn't exist", id));
		}
		Resource<Student> resource = buildUserResource(student);
		return resource;
	}

	private Resource<Student> buildUserResource(Student student) {
		Resource<Student> resource = new Resource<Student>(student);
		Link allStudentsLink = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(StudentController.class).getAllStudents())
				.withRel("all-students");
		Link allStudentCourseLink = ControllerLinkBuilder.linkTo(ControllerLinkBuilder
				.methodOn(StudentController.class, student.getId()).getStudentCourses(student.getId()))
				.withRel("all-user-posts");
		resource.add(allStudentsLink, allStudentCourseLink);
		return resource;
	}

	@PostMapping(path = "/students")
	public ResponseEntity<Student> addStudent(@RequestBody Student student) {
		studentDao.add(student);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(student.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping(path = "/students/{id}/courses")
	public List<Course> getStudentCourses(@PathVariable int id) {
		Student student = studentDao.findById(id);
		if (student == null) {
			throw new StudentNotFoundException(String.format("Student with id %d doesn't exist", id));
		}
		return student.getCourses();
	}

	@GetMapping(path = "/students/{id}/courses/{courseId}")
	public Course getStudentCourse(@PathVariable int id, @PathVariable int courseId) {
		Student student = studentDao.findById(id);
		if (student != null) {
			return student.getCourses().stream().filter(course -> course.getId() == courseId).findFirst().orElse(null);
		}
		return null;
	}
}