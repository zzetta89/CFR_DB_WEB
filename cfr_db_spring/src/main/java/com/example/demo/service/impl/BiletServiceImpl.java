package com.example.demo.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.entity.Bilet;
import com.example.demo.repository.BiletRepository;
import com.example.demo.service.BiletService;

@Service
public class BiletServiceImpl implements BiletService {
    private final BiletRepository repo;

    public BiletServiceImpl(BiletRepository repo) { this.repo = repo; }

    @Override
    public List<Bilet> getAllBilete() { return repo.findAll(); }

    @Override
    @Transactional
    public Bilet saveBilet(Bilet bilet) { return repo.save(bilet); }

    @Override
    public Bilet getBiletById(Long id) { return repo.findById(id).orElse(null); }

    @Override
    @Transactional
    public Bilet updateBilet(Bilet bilet) { return repo.save(bilet); }

    @Override
    @Transactional
    public void deleteBiletById(Long id) { repo.deleteById(id); }
}