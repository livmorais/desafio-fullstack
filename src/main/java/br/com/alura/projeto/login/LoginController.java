package br.com.alura.projeto.login;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.alura.projeto.category.Category;
import br.com.alura.projeto.category.CategoryRepository;
import br.com.alura.projeto.course.Course;
import br.com.alura.projeto.course.CourseRepository;

@Controller
public class LoginController {

    private final CategoryRepository categoryRepository;
    private final CourseRepository courseRepository;

    public LoginController(CategoryRepository categoryRepository, CourseRepository courseRepository) {
        this.categoryRepository = categoryRepository;
        this.courseRepository = courseRepository;
    }

    @GetMapping("/")
    public String home(Model model) {

        List<Category> categories = categoryRepository.findAll();

        for (Category category : categories) {
            List<Course> coursesCategory = courseRepository.findByCategory(category);
            category.setCourses(coursesCategory);
        }

        List<Category> categoriesWithCourses = categories.stream()
                .filter(c -> c.getCourses() != null && !c.getCourses().isEmpty())
                .toList();

        model.addAttribute("categories", categoriesWithCourses);

        return "login";
    }
}
