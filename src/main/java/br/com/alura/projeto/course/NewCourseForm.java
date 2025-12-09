package br.com.alura.projeto.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import br.com.alura.projeto.category.Category;
import br.com.alura.projeto.user.User;

public class NewCourseForm {

    private Long id;
    
    @NotBlank(message = "O nome é obrigatório.")
    @Length(max = 100, message = "O nome deve ter no máximo 100 caracteres.")
    private String name;

    @NotBlank(message = "O código é obrigatório.")
    @Length(min = 4, max = 10, message = "O código deve ter entre 4 e 10 caracteres.")
    @Pattern(regexp = "^[a-z]+(-[a-z]+)*$", message = "Código deve estar em letras minúsculas e pode usar hífen")
    private String code;

    private String description;

    @NotNull(message = "Selecione um instrutor.")
    private Long instructorId;

    @NotNull(message = "Selecione uma categoria.")

    private Long categoryId;

    public Course toModel(User instructor, Category category) {
        return new Course(name, code, description, CourseStatus.ACTIVE, instructor, category);
    }

    public Long getId() { 
        return id; 
    }

    public void setId(Long id) { 
        this.id = id; 
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Long instructorId) {
        this.instructorId = instructorId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
