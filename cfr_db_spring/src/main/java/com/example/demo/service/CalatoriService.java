package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.Calatori;

public interface CalatoriService {
    List<Calatori> getAllCalatori();
    Calatori saveCalatori(Calatori calator);
    Calatori getCalatoriById(Long id);
    Calatori updateCalatori(Calatori calator);
    void deleteCalatoriById(Long id);
}