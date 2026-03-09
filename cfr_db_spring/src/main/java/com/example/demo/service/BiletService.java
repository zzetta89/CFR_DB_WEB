package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.Bilet;

public interface BiletService {
    List<Bilet> getAllBilete();
    Bilet saveBilet(Bilet bilet);
    Bilet getBiletById(Long id);
    Bilet updateBilet(Bilet bilet);
    void deleteBiletById(Long id);
}