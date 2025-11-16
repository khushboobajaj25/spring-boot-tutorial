package com.learning.studentmanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.learning.studentmanagement.dto.CourseRequest;
import com.learning.studentmanagement.model.Course;
import com.learning.studentmanagement.repository.CourseRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;

    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseByName(String name) {
        return courseRepository.findByName(name).orElseThrow();
    }

    public boolean saveCourse(CourseRequest courseRequest) {
        Course course = new Course();
        course.setName(courseRequest.name());
        courseRepository.save(course);
        return true;
    }

    public boolean deleteCourse(Integer id) {
        courseRepository.deleteById(id);
        return true;
    }
}
