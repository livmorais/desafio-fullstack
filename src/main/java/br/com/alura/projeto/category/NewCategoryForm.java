package br.com.alura.projeto.category;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

public class NewCategoryForm {

    @NotBlank(message = "O nome é obrigatório.")
    private String name;

    @NotBlank(message = "O código é obrigatório.")
    @Length(min = 4, max = 10, message = "O código deve ter entre 4 e 10 caracteres.")
    private String code;

    @Min(value = 1, message = "A ordem deve ser no mínimo 1.")
    private int order;

    @NotBlank(message = "A cor é obrigatória.")
    @Pattern(regexp = "^#?[0-9A-Fa-f]{6}$", message = "Use um código hexadecimal de 6 dígitos (ex: #FF00AA).")
    private String color;

    public Category toModel() {
        return new Category(name, code, color, order);
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

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        if (color != null && !color.isBlank()) {
            if (!color.startsWith("#")) {
                color = "#" + color;
            }
        }
        this.color = color;
    }
    
}
