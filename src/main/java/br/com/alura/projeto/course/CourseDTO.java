package br.com.alura.projeto.course;

import java.time.LocalDateTime;

public record CourseDTO(Long id, String name, String code, InstructorDTO instructor, CategoryDTO category, String description, CourseStatus status, LocalDateTime createdAt, LocalDateTime inactiveAt) {
    public CourseDTO(Course course) {
        this(course.getId(), course.getName(), course.getCode(), new InstructorDTO(course.getInstructor().getId(), course.getInstructor().getName()), new CategoryDTO(course.getCategory().getId(), course.getCategory().getName()), course.getDescription(), course.getStatus(), course.getCreatedAt(), course.getInactiveAt());
    }

    public record InstructorDTO(Long id, String name) {}
    public record CategoryDTO(Long id, String name) {}

}

