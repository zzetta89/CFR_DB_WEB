package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "bilet")
public class Bilet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bilet")
    private Long idBilet;

    @Column(name = "data_calatoriei")
    private String dataCalatoriei;

    private String destinatie;
    private int nrLoc;
    private String nrTren;
    private int nrVagon;
    private String plecare;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_calator")
    private Calatori calator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sucursala")
    private Sucursale sucursala;

    public Bilet() {}

    
    public Long getIdBilet() { return idBilet; }
    public void setIdBilet(Long idBilet) { this.idBilet = idBilet; }
    public String getDataCalatoriei() { return dataCalatoriei; }
    public void setDataCalatoriei(String dataCalatoriei) { this.dataCalatoriei = dataCalatoriei; }
    public String getDestinatie() { return destinatie; }
    public void setDestinatie(String destinatie) { this.destinatie = destinatie; }
    public int getNrLoc() { return nrLoc; }
    public void setNrLoc(int nrLoc) { this.nrLoc = nrLoc; }
    public String getNrTren() { return nrTren; }
    public void setNrTren(String nrTren) { this.nrTren = nrTren; }
    public int getNrVagon() { return nrVagon; }
    public void setNrVagon(int nrVagon) { this.nrVagon = nrVagon; }
    public String getPlecare() { return plecare; }
    public void setPlecare(String plecare) { this.plecare = plecare; }
    public Calatori getCalator() { return calator; }
    public void setCalator(Calatori calator) { this.calator = calator; }
    public Sucursale getSucursala() { return sucursala; }
    public void setSucursala(Sucursale sucursala) { this.sucursala = sucursala; }
}