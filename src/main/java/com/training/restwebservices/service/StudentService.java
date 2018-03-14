package com.training.restwebservices.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.training.restwebservices.model.Course;
import com.training.restwebservices.model.Student;

@Repository
public class StudentService {

	private static List<Student> students = new ArrayList<Student>();

	static {
		students.add(new Student(1, "Popescu Ionut",
				Arrays.asList(new Course(1, "Data structures & algorithms", "Data structures & algorithms"),
						new Course(2, "OOP programming", "OOP programming"))));
		students.add(new Student(2, "Popescu Maria",
				Arrays.asList(new Course(1, "Data structures & algorithms", "Data structures & algorithms"))));
	}

	public List<Student> findAll() {
		return students;
	}

	public Student findById(int id) {
		return students.stream().filter(st -> st.getId() == id).findFirst().orElse(null);
	}

	public Student removeById(int id) {
		Student student = findById(id);
		if(student != null) {
			students.remove(student);
		}
		return student;
	}

	public Student add(Student student) {
		if(student.getId() == null) {
			student.setId(students.size() + 1);
		}
		students.add(student);
		return student;
	}
}