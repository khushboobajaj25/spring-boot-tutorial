package com.learning.studentmanagement.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learning.studentmanagement.dto.StudentRequest;
import com.learning.studentmanagement.model.Student;
import com.learning.studentmanagement.service.StudentService;
import com.learning.studentmanagement.utils.ApiConstants;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping(ApiConstants.STUDENT)
    public ResponseEntity<List<Student>> getStudents() {
        return ResponseEntity.ok(studentService.getStudents());
    }

    @GetMapping(ApiConstants.STUDENT_BY_ID)
    public ResponseEntity<Student> getStudent(@PathVariable Integer id) {
        return ResponseEntity.ok(studentService.getStudent(id));
    }

    @PostMapping(ApiConstants.STUDENT)
    public ResponseEntity<?> saveStudent(@Valid @RequestBody StudentRequest studentRequest) {
        boolean isSaved = studentService.saveStudent(studentRequest);
        if (isSaved) {
            return ResponseEntity.ok(Map.of("message", "Student saved successfully"));
        }
        return ResponseEntity.badRequest().body(Map.of("message", "Error saving student"));
    }

    @DeleteMapping(ApiConstants.STUDENT_BY_ID)
    public ResponseEntity<?> deleteStudent(Integer id) {
        boolean isSaved = studentService.deleteStudent(id);
        if (isSaved) {
            return ResponseEntity.ok(Map.of("message", "Student deleted successfully"));
        }
        return ResponseEntity.badRequest().body(Map.of("message", "Error deleting student"));
    }
}
