package br.com.alura.projeto.login;

import br.com.alura.projeto.category.Category;
import br.com.alura.projeto.category.CategoryRepository;
import br.com.alura.projeto.course.Course;
import br.com.alura.projeto.course.CourseRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LoginController.class)
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryRepository categoryRepository;

    @MockBean
    private CourseRepository courseRepository;

    @Test
    @DisplayName("Should show login page with categories with courses")
    void shouldShowLoginPageWithCategoriesWithCourses() throws Exception {
        Category category = new Category("Backend", "backend", "#FF0000", 1);
        Course course = new Course("Java Bootcamp", "javaboot", "Learn Java", null, null, null);

        when(categoryRepository.findAll()).thenReturn(List.of(category));
        when(courseRepository.findByCategory(category)).thenReturn(List.of(course));

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attributeExists("categories"));
    }
}