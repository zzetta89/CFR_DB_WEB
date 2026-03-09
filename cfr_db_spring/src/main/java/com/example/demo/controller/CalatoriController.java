package com.example.demo.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.Calatori;
import com.example.demo.service.CalatoriService;
import com.example.demo.repository.CalatoriRepository;

@Controller
public class CalatoriController {

    private final CalatoriService calatoriService;
    private final CalatoriRepository calatoriRepository;

    public CalatoriController(CalatoriService calatoriService, CalatoriRepository calatoriRepository) {
        this.calatoriService = calatoriService;
        this.calatoriRepository = calatoriRepository;
    }

    @GetMapping("/calatori")
    public String listCalatori(Model model) {
        model.addAttribute("calatori", calatoriService.getAllCalatori());
        return "calatori";
    }

    @GetMapping("/calatori/new")
    public String createCalatorForm(Model model) {
        model.addAttribute("calator", new Calatori());
        return "create_calator";
    }

    // CREATE
    @PostMapping("/calatori")
    public String saveCalator(@Valid @ModelAttribute("calator") Calatori calator, 
                              BindingResult result, Model model) {
        
        if (calatoriRepository.existsByCnp(calator.getCnp())) {
            result.rejectValue("cnp", "error.calator", "Acest CNP există deja!");
        }
        if (calatoriRepository.existsByEmail(calator.getEmail())) {
            result.rejectValue("email", "error.calator", "Acest Email este deja folosit!");
        }

        if (result.hasErrors()) {
            return "create_calator";
        }

        calatoriService.saveCalatori(calator);
        return "redirect:/calatori";
    }

    @GetMapping("/calatori/edit/{id}")
    public String editCalatorForm(@PathVariable Long id, Model model) {
        model.addAttribute("calator", calatoriService.getCalatoriById(id));
        return "edit_calator";
    }

    // UPDATE
    @PostMapping("/calatori/{id}")
    public String updateCalator(@PathVariable Long id,
                                @Valid @ModelAttribute("calator") Calatori calator,
                                BindingResult result, Model model) {
        
        
        if (calatoriRepository.existsByCnpAndIdCalatorNot(calator.getCnp(), id)) {
            result.rejectValue("cnp", "error.calator", "Acest CNP aparține altui călător!");
        }

        
        if (calatoriRepository.existsByEmailAndIdCalatorNot(calator.getEmail(), id)) {
            result.rejectValue("email", "error.calator", "Acest Email aparține altui călător!");
        }

        if (result.hasErrors()) {
            calator.setIdCalator(id); 
            return "edit_calator";
        }

        Calatori existent = calatoriService.getCalatoriById(id);
        existent.setNume(calator.getNume());
        existent.setPrenume(calator.getPrenume());
        existent.setEmail(calator.getEmail());
        existent.setCnp(calator.getCnp());
        existent.setDataNasterii(calator.getDataNasterii());

        calatoriService.updateCalatori(existent);
        return "redirect:/calatori";
    }

    @GetMapping("/calatori/delete/{id}")
    public String deleteCalator(@PathVariable Long id) {
        calatoriService.deleteCalatoriById(id);
        return "redirect:/calatori";
    }
}