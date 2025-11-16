package com.learning.studentmanagement.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record StudentRequest(
        @NotBlank String name,
        @NotBlank @Size(min = 4, max = 4) String year,
        @NotBlank String email,
        @Min(value = 18, message = "Age should be greater than 18") Integer age,
        @NotNull CourseRequest course) {
}
