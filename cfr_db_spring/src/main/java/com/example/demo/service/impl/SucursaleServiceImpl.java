package com.example.demo.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.entity.Sucursale;
import com.example.demo.repository.SucursaleRepository;
import com.example.demo.service.SucursaleService;

@Service
public class SucursaleServiceImpl implements SucursaleService {

    private final SucursaleRepository sucursaleRepository;

    public SucursaleServiceImpl(SucursaleRepository sucursaleRepository) {
        this.sucursaleRepository = sucursaleRepository;
    }

    @Override
    public List<Sucursale> getAllSucursale() {
        return sucursaleRepository.findAll();
    }

    @Override
    @Transactional
    public Sucursale saveSucursale(Sucursale sucursala) {
        return sucursaleRepository.save(sucursala);
    }

    @Override
    public Sucursale getSucursaleById(Long id) {
        return sucursaleRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Sucursale updateSucursale(Sucursale sucursala) {
        return sucursaleRepository.save(sucursala);
    }

    @Override
    @Transactional
    public void deleteSucursaleById(Long id) {
        sucursaleRepository.deleteById(id);
    }
}