package com.learning.studentmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.studentmanagement.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
