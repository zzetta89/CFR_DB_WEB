package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Sucursale;

public interface SucursaleRepository extends JpaRepository<Sucursale, Long> {
    
    
    boolean existsByDenumireAndOras(String denumire, String oras);

    boolean existsByDenumireAndOrasAndIdSucursalaNot(String denumire, String oras, Long idSucursala);
}