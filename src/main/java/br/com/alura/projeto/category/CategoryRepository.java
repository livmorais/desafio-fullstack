package br.com.alura.projeto.category;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByCode(String code);
    Optional<Category> findByCode(String code);
}
