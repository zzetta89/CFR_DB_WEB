package com.example.demo.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.Bilet;
import com.example.demo.repository.BiletRepository;
import com.example.demo.service.*;

@Controller
public class BiletController {

    private final BiletService biletService;
    private final CalatoriService calatoriService;
    private final SucursaleService sucursaleService;
    private final BiletRepository biletRepository;

    public BiletController(BiletService bs, CalatoriService cs, SucursaleService ss, BiletRepository br) {
        this.biletService = bs;
        this.calatoriService = cs;
        this.sucursaleService = ss;
        this.biletRepository = br;
    }

    @GetMapping("/bilete")
    public String listBilete(Model model) {
        model.addAttribute("bilete", biletService.getAllBilete());
        return "bilete";
    }

    @GetMapping("/bilete/new")
    public String createBiletForm(Model model) {
        model.addAttribute("bilet", new Bilet());
        model.addAttribute("listaCalatori", calatoriService.getAllCalatori());
        model.addAttribute("listaSucursale", sucursaleService.getAllSucursale());
        return "create_bilet";
    }

    // CREATE
    @PostMapping("/bilete")
    public String saveBilet(@Valid @ModelAttribute("bilet") Bilet bilet, 
                            BindingResult result, Model model) {
        
        boolean locOcupat = biletRepository.existsByNrTrenAndNrVagonAndNrLocAndDataCalatoriei(
                bilet.getNrTren(), bilet.getNrVagon(), bilet.getNrLoc(), bilet.getDataCalatoriei());

        if (locOcupat) {
            result.rejectValue("nrLoc", "error.bilet", "Locul este deja rezervat!");
        }

        if (result.hasErrors()) {
            model.addAttribute("listaCalatori", calatoriService.getAllCalatori());
            model.addAttribute("listaSucursale", sucursaleService.getAllSucursale());
            return "create_bilet";
        }

        biletService.saveBilet(bilet);
        return "redirect:/bilete";
    }

    @GetMapping("/bilete/edit/{id}")
    public String editBiletForm(@PathVariable Long id, Model model) {
        model.addAttribute("bilet", biletService.getBiletById(id));
        model.addAttribute("listaCalatori", calatoriService.getAllCalatori());
        model.addAttribute("listaSucursale", sucursaleService.getAllSucursale());
        return "edit_bilet";
    }

    // UPDATE
    @PostMapping("/bilete/{id}")
    public String updateBilet(@PathVariable Long id,
                              @Valid @ModelAttribute("bilet") Bilet bilet,
                              BindingResult result, Model model) {
        
        // Verificam daca locul e ocupat de ALT bilet
        boolean locOcupatDeAltcineva = biletRepository.existsByNrTrenAndNrVagonAndNrLocAndDataCalatorieiAndIdBiletNot(
                bilet.getNrTren(), 
                bilet.getNrVagon(), 
                bilet.getNrLoc(), 
                bilet.getDataCalatoriei(),
                id 
        );

        if (locOcupatDeAltcineva) {
            result.rejectValue("nrLoc", "error.bilet", "Locul este ocupat de alt bilet!");
        }

        if (result.hasErrors()) {
            bilet.setIdBilet(id);
            model.addAttribute("listaCalatori", calatoriService.getAllCalatori());
            model.addAttribute("listaSucursale", sucursaleService.getAllSucursale());
            return "edit_bilet";
        }
        
        Bilet existent = biletService.getBiletById(id);
        existent.setDataCalatoriei(bilet.getDataCalatoriei());
        existent.setDestinatie(bilet.getDestinatie());
        existent.setPlecare(bilet.getPlecare());
        existent.setNrTren(bilet.getNrTren());
        existent.setNrVagon(bilet.getNrVagon());
        existent.setNrLoc(bilet.getNrLoc());
        existent.setCalator(bilet.getCalator());
        existent.setSucursala(bilet.getSucursala());

        biletService.updateBilet(existent);
        return "redirect:/bilete";
    }

    @GetMapping("/bilete/delete/{id}")
    public String deleteBilet(@PathVariable Long id) {
        biletService.deleteBiletById(id);
        return "redirect:/bilete";
    }
}