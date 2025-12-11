package br.com.alura.projeto.registration;

import br.com.alura.projeto.course.Course;
import br.com.alura.projeto.course.CourseStatus;
import br.com.alura.projeto.course.CourseRepository;
import br.com.alura.projeto.user.Role;
import br.com.alura.projeto.user.User;
import br.com.alura.projeto.user.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RegistrationController.class)
class RegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegistrationService registrationService;

    @MockBean
    private RegistrationRepository registrationRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Should enroll student successfully")
    void shouldEnrollStudent() throws Exception {
        NewRegistrationDTO dto = new NewRegistrationDTO();
        dto.setCourseCode("javaboot");
        dto.setStudentEmail("student@gmail.com");

        mockMvc.perform(post("/registration/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Should return BAD_REQUEST if student already enrolled")
    void shouldReturnBadRequestIfAlreadyEnrolled() throws Exception {
        NewRegistrationDTO dto = new NewRegistrationDTO();
        dto.setCourseCode("javaboot");
        dto.setStudentEmail("student@gmail.com");

        doThrow(new IllegalStateException("Student already enrolled in this course."))
                .when(registrationService).enrollStudent(any(NewRegistrationDTO.class));

        mockMvc.perform(post("/registration/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Student already enrolled in this course."));
    }
}