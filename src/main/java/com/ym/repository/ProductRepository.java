package com.ym.repository;

import com.ym.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsById(Long id);
    Page<Product> findByNameContains(String keyword, Pageable pageable);
}
