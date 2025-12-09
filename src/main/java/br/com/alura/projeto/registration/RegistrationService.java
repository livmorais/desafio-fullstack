package br.com.alura.projeto.registration;

import br.com.alura.projeto.course.Course;
import br.com.alura.projeto.course.CourseStatus;
import br.com.alura.projeto.course.CourseRepository;
import br.com.alura.projeto.user.User;
import br.com.alura.projeto.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public RegistrationService(RegistrationRepository registrationRepository, CourseRepository courseRepository, UserRepository userRepository) {
        this.registrationRepository = registrationRepository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void enrollStudent(NewRegistrationDTO dto) {
        Course course = courseRepository.findByCode(dto.getCourseCode())
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado."));

        if (course.getStatus() != CourseStatus.ACTIVE) {
            throw new IllegalStateException("Não é possível matricular em curso inativo.");
        }

        User student = userRepository.findByEmail(dto.getStudentEmail())
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado."));

        if (registrationRepository.existsByCourseAndStudent(course, student)) {
            throw new IllegalStateException("Aluno já matriculado neste curso.");
        }

        Registration registration = new Registration(student, course);
        registrationRepository.save(registration);
    }
}
