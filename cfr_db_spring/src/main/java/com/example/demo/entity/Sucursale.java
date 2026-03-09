package com.example.demo.entity;

import java.util.Set;
import jakarta.persistence.*;

@Entity
@Table(name = "sucursale")
public class Sucursale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sucursala")
    private Long idSucursala;

    private String adresa;
    
    @Column(nullable = false)
    private String denumire;
    
    @Column(nullable = false)
    private String oras;

    @OneToMany(mappedBy = "sucursala", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Bilet> bileteEmise;

    public Sucursale() {}

    
    public Long getIdSucursala() { return idSucursala; }
    public void setIdSucursala(Long idSucursala) { this.idSucursala = idSucursala; }
    public String getAdresa() { return adresa; }
    public void setAdresa(String adresa) { this.adresa = adresa; }
    public String getDenumire() { return denumire; }
    public void setDenumire(String denumire) { this.denumire = denumire; }
    public String getOras() { return oras; }
    public void setOras(String oras) { this.oras = oras; }
    public Set<Bilet> getBileteEmise() { return bileteEmise; }
    public void setBileteEmise(Set<Bilet> bileteEmise) { this.bileteEmise = bileteEmise; }
}