package com.learning.studentmanagement.dto;

import jakarta.validation.constraints.NotBlank;

public record CourseRequest(@NotBlank String name) {
}
