package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Calatori;

public interface CalatoriRepository extends JpaRepository<Calatori, Long> {
    
    
    boolean existsByCnp(String cnp);
    boolean existsByEmail(String email);

    boolean existsByCnpAndIdCalatorNot(String cnp, Long idCalator);
    boolean existsByEmailAndIdCalatorNot(String email, Long idCalator);
}