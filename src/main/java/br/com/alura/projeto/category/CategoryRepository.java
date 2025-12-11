package br.com.alura.projeto.category;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByCode(String code);
    Optional<Category> findByCode(String code);
    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.courses")
    List<Category> findAllWithCourses();
}
