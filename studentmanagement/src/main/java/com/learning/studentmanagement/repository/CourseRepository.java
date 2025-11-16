package com.learning.studentmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.studentmanagement.model.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    Optional<Course> findByName(String name);
}
