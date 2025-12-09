package br.com.alura.projeto.course;

import br.com.alura.projeto.category.Category;
import br.com.alura.projeto.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Length(max = 100)
    private String name;

    @NotBlank
    @Length(min = 4, max = 10)
    private String code;

    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CourseStatus status = CourseStatus.ACTIVE;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime inactiveAt;

    @ManyToOne
    @JoinColumn(name = "instructor_id", nullable = false)
    private User instructor;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Deprecated
    public Course() {}

    public Course(String name, String code, String description, CourseStatus status, User instructor, Category category) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.status = status;
        this.instructor = instructor;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public CourseStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getInactiveAt() {
        return inactiveAt;
    }

    public User getInstructor() {
        return instructor;
    }

    public Category getCategory() {
        return category;
    }

    public void setName(String name) { 
        this.name = name; 
    }

    public void setCode(String code) { 
        this.code = code; 
    }

    public void setDescription(String description) { 
        this.description = description; 
    }

    public void setInstructor(User instructor) { 
        this.instructor = instructor; 
    }
    
    public void setCategory(Category category) { 
        this.category = category; 
    }

    public void setStatus(CourseStatus status) {
        this.status = status;
    }

    public void setInactiveAt(LocalDateTime inactiveAt) {
        this.inactiveAt = inactiveAt;
    }
}
