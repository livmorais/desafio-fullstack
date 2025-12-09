package br.com.alura.projeto.registration;

import br.com.alura.projeto.course.Course;
import br.com.alura.projeto.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    boolean existsByCourseAndStudent(Course course, User student);
    Optional<Registration> findByCourseAndStudent(Course course, User student);
    @Query("""
        SELECT r.course.name, r.course.code, r.course.instructor.name,
               r.course.instructor.email, COUNT(r), r.course.status
        FROM Registration r
        WHERE r.course.status = 'ACTIVE'
        GROUP BY r.course.id, r.course.name, r.course.code,
                 r.course.instructor.name, r.course.instructor.email, r.course.status
        ORDER BY COUNT(r) DESC
    """)
    List<Object[]> getReportData();
    @Query("""
        SELECT r.course.name, r.course.code, r.course.instructor.name,
               r.course.instructor.email, COUNT(r), r.course.status
        FROM Registration r
        WHERE r.course.status = 'INACTIVE'
        GROUP BY r.course.id, r.course.name, r.course.code,
                 r.course.instructor.name, r.course.instructor.email, r.course.status
        ORDER BY COUNT(r) DESC
    """)
    List<Object[]> getInactiveReportData();
}
