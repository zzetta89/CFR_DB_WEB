package com.example.demo.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.Sucursale;
import com.example.demo.service.SucursaleService;
import com.example.demo.repository.SucursaleRepository;

@Controller
public class SucursaleController {

    private final SucursaleService sucursaleService;
    private final SucursaleRepository sucursaleRepository;

    public SucursaleController(SucursaleService sucursaleService, SucursaleRepository sucursaleRepository) {
        this.sucursaleService = sucursaleService;
        this.sucursaleRepository = sucursaleRepository;
    }

    @GetMapping("/sucursale")
    public String listSucursale(Model model) {
        model.addAttribute("sucursale", sucursaleService.getAllSucursale());
        return "sucursale";
    }

    @GetMapping("/sucursale/new")
    public String createSucursalaForm(Model model) {
        model.addAttribute("sucursala", new Sucursale());
        return "create_sucursala";
    }

    // CREATE
    @PostMapping("/sucursale")
    public String saveSucursala(@Valid @ModelAttribute("sucursala") Sucursale sucursala, 
                                BindingResult result, Model model) {
        
        if (sucursaleRepository.existsByDenumireAndOras(sucursala.getDenumire(), sucursala.getOras())) {
            result.rejectValue("denumire", "error.sucursala", "Sucursala există deja în acest oraș!");
        }

        if (result.hasErrors()) {
            return "create_sucursala";
        }

        sucursaleService.saveSucursale(sucursala);
        return "redirect:/sucursale";
    }

    @GetMapping("/sucursale/edit/{id}")
    public String editSucursalaForm(@PathVariable Long id, Model model) {
        model.addAttribute("sucursala", sucursaleService.getSucursaleById(id));
        return "edit_sucursala";
    }

    // UPDATE
    @PostMapping("/sucursale/{id}")
    public String updateSucursala(@PathVariable Long id,
                                  @Valid @ModelAttribute("sucursala") Sucursale sucursala,
                                  BindingResult result, Model model) {
        
        boolean duplicate = sucursaleRepository.existsByDenumireAndOrasAndIdSucursalaNot(
                sucursala.getDenumire(), 
                sucursala.getOras(), 
                id
        );

        if (duplicate) {
            result.rejectValue("denumire", "error.sucursala", "Această combinație Nume-Oraș este deja folosită de altă sucursală!");
        }

        if (result.hasErrors()) {
            sucursala.setIdSucursala(id);
            return "edit_sucursala";
        }
        
        Sucursale existenta = sucursaleService.getSucursaleById(id);
        existenta.setDenumire(sucursala.getDenumire());
        existenta.setOras(sucursala.getOras());
        existenta.setAdresa(sucursala.getAdresa());

        sucursaleService.updateSucursale(existenta);
        return "redirect:/sucursale";
    }

    @GetMapping("/sucursale/delete/{id}")
    public String deleteSucursala(@PathVariable Long id) {
        sucursaleService.deleteSucursaleById(id);
        return "redirect:/sucursale";
    }
}