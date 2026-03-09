package com.example.demo.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.entity.Calatori;
import com.example.demo.repository.CalatoriRepository;
import com.example.demo.service.CalatoriService;

@Service
public class CalatoriServiceImpl implements CalatoriService {
    private final CalatoriRepository repo;

    public CalatoriServiceImpl(CalatoriRepository repo) { this.repo = repo; }

    @Override
    public List<Calatori> getAllCalatori() { return repo.findAll(); }

    @Override
    @Transactional
    public Calatori saveCalatori(Calatori calator) { return repo.save(calator); }

    @Override
    public Calatori getCalatoriById(Long id) { return repo.findById(id).orElse(null); }

    @Override
    @Transactional
    public Calatori updateCalatori(Calatori calator) { return repo.save(calator); }

    @Override
    @Transactional
    public void deleteCalatoriById(Long id) { repo.deleteById(id); }
}