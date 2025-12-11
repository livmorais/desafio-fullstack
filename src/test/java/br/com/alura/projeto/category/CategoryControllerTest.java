package br.com.alura.projeto.category;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryRepository categoryRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Should create a new category successfully")
    void shouldCreateCategory() throws Exception {
        NewCategoryForm form = new NewCategoryForm();
        form.setName("Backend");
        form.setCode("backend");
        form.setColor("#FF0000");
        form.setOrder(1);

        when(categoryRepository.existsByCode("backend")).thenReturn(false);
        when(categoryRepository.save(any(Category.class))).thenReturn(new Category("Backend", "backend", "#FF0000", 1));

        mockMvc.perform(post("/admin/category/new")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", form.getName())
                        .param("code", form.getCode())
                        .param("color", form.getColor())
                        .param("order", String.valueOf(form.getOrder())))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/categories"));
    }

    @Test
    @DisplayName("Should not create category with duplicate code")
    void shouldNotCreateCategoryWithDuplicateCode() throws Exception {
        NewCategoryForm form = new NewCategoryForm();
        form.setName("Backend");
        form.setCode("backend");
        form.setColor("#FF0000");
        form.setOrder(1);

        when(categoryRepository.existsByCode("backend")).thenReturn(true);

        mockMvc.perform(post("/admin/category/new")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", form.getName())
                        .param("code", form.getCode())
                        .param("color", form.getColor())
                        .param("order", String.valueOf(form.getOrder())))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasFieldErrors("newCategoryForm", "code"))
                .andExpect(view().name("admin/category/newForm"));
    }
}