package com.learning.studentmanagement.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learning.studentmanagement.dto.CourseRequest;
import com.learning.studentmanagement.model.Course;
import com.learning.studentmanagement.service.CourseService;
import com.learning.studentmanagement.utils.ApiConstants;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping(ApiConstants.COURSE)
    public ResponseEntity<List<Course>> getCourses() {
        return ResponseEntity.ok(courseService.getCourses());
    }

    @PostMapping(ApiConstants.COURSE)
    public ResponseEntity<?> saveCourse(@Valid @RequestBody CourseRequest courseRequest) {
        boolean isSaved = courseService.saveCourse(courseRequest);
        if (isSaved) {
            return ResponseEntity.ok(Map.of("message", "Course saved successfully"));
        }
        return ResponseEntity.badRequest().body(Map.of("message", "Error saving course"));
    }

    @DeleteMapping(ApiConstants.COURSE_BY_ID)
    public ResponseEntity<?> deleteCourse(Integer id) {
        boolean isSaved = courseService.deleteCourse(id);
        if (isSaved) {
            return ResponseEntity.ok(Map.of("message", "Course deleted successfully"));
        }
        return ResponseEntity.badRequest().body(Map.of("message", "Error deleting course"));
    }
}
