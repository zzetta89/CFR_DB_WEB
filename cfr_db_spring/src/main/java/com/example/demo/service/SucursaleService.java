package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.Sucursale;

public interface SucursaleService {
    List<Sucursale> getAllSucursale();
    Sucursale saveSucursale(Sucursale sucursala);
    Sucursale getSucursaleById(Long id);
    Sucursale updateSucursale(Sucursale sucursala);
    void deleteSucursaleById(Long id);
}