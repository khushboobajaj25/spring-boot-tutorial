package com.learning.studentmanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Table
@Entity(name = "student")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank
    private String name;

    @NotBlank
    @Size(min = 4, max = 4)
    private String year;

    @NotBlank
    private String email;

    @Min(value = 18, message = "Age should be greater than 18")
    private Integer age;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
