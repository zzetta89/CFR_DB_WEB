package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Bilet;

public interface BiletRepository extends JpaRepository<Bilet, Long> {
    
    
    boolean existsByNrTrenAndNrVagonAndNrLocAndDataCalatoriei(String nrTren, int nrVagon, int nrLoc, String dataCalatoriei);

    
    boolean existsByNrTrenAndNrVagonAndNrLocAndDataCalatorieiAndIdBiletNot(
            String nrTren, int nrVagon, int nrLoc, String dataCalatoriei, Long idBilet);
}