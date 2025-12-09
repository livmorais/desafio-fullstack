package br.com.alura.projeto.category;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/admin/categories")
    public String list(Model model) {
        List<CategoryDTO> list = categoryRepository.findAll()
                .stream()
                .map(CategoryDTO::new)
                .toList();

        model.addAttribute("categories", list);

        return "admin/category/list";
    }

    @GetMapping("/admin/category/new")
    public String create(NewCategoryForm newCategory, Model model) {
        return "admin/category/newForm";
    }

    @Transactional
    @PostMapping("/admin/category/new")
    public String save(@Valid NewCategoryForm form, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return create(form, model);
        }

        if (categoryRepository.existsByCode(form.getCode())) {
            result.rejectValue("code", null, "O código já existe!");
            model.addAttribute("categories", categoryRepository.findAll());
            return "admin/category/newForm";
        }

        categoryRepository.save(form.toModel());
        return "redirect:/admin/categories";
    }


    @GetMapping("/admin/category/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));

        NewCategoryForm form = new NewCategoryForm();
        form.setName(category.getName());
        form.setCode(category.getCode());
        form.setColor(category.getColor());
        form.setOrder(category.getOrder());

        model.addAttribute("newCategoryForm", form);
        model.addAttribute("id", id);

        return "admin/category/editForm";
    }

    @Transactional
    @PostMapping("/admin/category/{id}/edit")
    public String update(@PathVariable Long id, @Valid NewCategoryForm form, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("id", id);
            return "admin/category/editForm";
        }

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));

        categoryRepository.findByCode(form.getCode()).ifPresent(existing -> {
            if (!existing.getId().equals(id)) {
                result.rejectValue("code", null, "O código já existe!");
            }
        });

        if (result.hasErrors()) {
            model.addAttribute("id", id);
            return "admin/category/editForm";
        }

        category.setName(form.getName());
        category.setCode(form.getCode());
        category.setColor(form.getColor());
        category.setOrder(form.getOrder());

        return "redirect:/admin/categories";
    }

}
