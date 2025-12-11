package br.com.alura.projeto.course;

import br.com.alura.projeto.category.Category;
import br.com.alura.projeto.category.CategoryRepository;
import br.com.alura.projeto.user.Role;
import br.com.alura.projeto.user.User;
import br.com.alura.projeto.user.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CourseController.class)
class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseRepository courseRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private CategoryRepository categoryRepository;

    @Test
    @DisplayName("Should create a new course successfully")
    void shouldCreateCourse() throws Exception {
        User instructor = new User("John Doe", "john@gmail.com", Role.INSTRUCTOR, "password123");
        ReflectionTestUtils.setField(instructor, "id", 1L);

        Category category = new Category("Backend", "backend", "#FF0000", 1);
        ReflectionTestUtils.setField(category, "id", 1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(instructor));
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(courseRepository.existsByCode("javaboot")).thenReturn(false);
        when(userRepository.findByRole(Role.INSTRUCTOR)).thenReturn(List.of(instructor));
        when(categoryRepository.findAll()).thenReturn(List.of(category));
        when(courseRepository.save(any(Course.class))).thenReturn(
                new Course("Java Bootcamp", "javaboot", "Learn Java", CourseStatus.ACTIVE, instructor, category)
        );

        mockMvc.perform(post("/admin/course/new")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", "Java Bootcamp")
                        .param("code", "javaboot")
                        .param("description", "Learn Java")
                        .param("instructorId", "1")
                        .param("categoryId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/courses"));
    }

    @Test
    @DisplayName("Should not create course with duplicate code")
    void shouldNotCreateCourseWithDuplicateCode() throws Exception {
        User instructor = new User("John Doe", "john@gmail.com", Role.INSTRUCTOR, "password123");
        ReflectionTestUtils.setField(instructor, "id", 1L);

        Category category = new Category("Backend", "backend", "#FF0000", 1);
        ReflectionTestUtils.setField(category, "id", 1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(instructor));
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(courseRepository.existsByCode("javaboot")).thenReturn(true);
        when(userRepository.findByRole(Role.INSTRUCTOR)).thenReturn(List.of(instructor));
        when(categoryRepository.findAll()).thenReturn(List.of(category));

        mockMvc.perform(post("/admin/course/new")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", "Java Bootcamp")
                        .param("code", "javaboot")
                        .param("description", "Learn Java")
                        .param("instructorId", "1")
                        .param("categoryId", "1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasFieldErrors("newCourseForm", "code"))
                .andExpect(view().name("admin/course/newForm"));
    }

    @Test
    @DisplayName("Should not create course with invalid code length")
    void shouldNotCreateCourseWithInvalidCodeLength() throws Exception {
        User instructor = new User("John Doe", "john@gmail.com", Role.INSTRUCTOR, "password123");
        ReflectionTestUtils.setField(instructor, "id", 1L);

        Category category = new Category("Backend", "backend", "#FF0000", 1);
        ReflectionTestUtils.setField(category, "id", 1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(instructor));
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(userRepository.findByRole(Role.INSTRUCTOR)).thenReturn(List.of(instructor));
        when(categoryRepository.findAll()).thenReturn(List.of(category));

        mockMvc.perform(post("/admin/course/new")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", "Java Bootcamp")
                        .param("code", "jb")
                        .param("description", "Learn Java")
                        .param("instructorId", "1")
                        .param("categoryId", "1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasFieldErrors("newCourseForm", "code"))
                .andExpect(view().name("admin/course/newForm"));
    }
}