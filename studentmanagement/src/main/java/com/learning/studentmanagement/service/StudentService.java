package com.learning.studentmanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.learning.studentmanagement.dto.StudentRequest;
import com.learning.studentmanagement.model.Course;
import com.learning.studentmanagement.model.Student;
import com.learning.studentmanagement.repository.StudentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final CourseService courseService;

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student getStudent(Integer id) {
        return studentRepository.findById(id).orElseThrow();
    }

    public boolean saveStudent(StudentRequest studentRequest) {
        Student student = new Student();
        student.setEmail(studentRequest.email());
        student.setName(studentRequest.name());
        student.setYear(studentRequest.year());
        student.setAge(studentRequest.age());

        Course course = courseService.getCourseByName(studentRequest.course().name());
        student.setCourse(course);
        studentRepository.save(student);
        return true;
    }

    public boolean deleteStudent(Integer id) {
        studentRepository.deleteById(id);
        return true;
    }
}
