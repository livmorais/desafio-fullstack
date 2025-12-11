package br.com.alura.projeto.login;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.alura.projeto.category.Category;
import br.com.alura.projeto.category.CategoryRepository;

@Controller
public class LoginController {

    private final CategoryRepository categoryRepository;

    public LoginController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/")
    public String home(Model model) {

        List<Category> categories = categoryRepository.findAllWithActiveCourses();

        List<Category> categoriesWithActiveCourses  = categories.stream()
            .filter(c -> c.getCourses() != null && !c.getCourses().isEmpty())
            .toList();

        model.addAttribute("categories", categoriesWithActiveCourses );

        return "login";
    }
}
