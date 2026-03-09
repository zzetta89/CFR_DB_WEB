package com.example.demo.entity;

import java.util.Set;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "calatori")
public class Calatori {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_calator")
    private Long idCalator;

    @Column(name = "cnp", unique = true)
    @Size(min = 13, max = 13, message = "CNP-ul trebuie să aibă exact 13 cifre!")
    @Pattern(regexp = "\\d+", message = "CNP-ul trebuie să conțină doar cifre!")
    private String cnp;

    @Column(name = "data_nasterii")
    private String dataNasterii;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "nume", nullable = false)
    private String nume;

    @Column(name = "prenume", nullable = false)
    private String prenume;

    @OneToMany(mappedBy = "calator", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Bilet> bilete;

    public Calatori() {}

    
    public Long getIdCalator() { return idCalator; }
    public void setIdCalator(Long idCalator) { this.idCalator = idCalator; }
    public String getCnp() { return cnp; }
    public void setCnp(String cnp) { this.cnp = cnp; }
    public String getDataNasterii() { return dataNasterii; }
    public void setDataNasterii(String dataNasterii) { this.dataNasterii = dataNasterii; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getNume() { return nume; }
    public void setNume(String nume) { this.nume = nume; }
    public String getPrenume() { return prenume; }
    public void setPrenume(String prenume) { this.prenume = prenume; }
    public Set<Bilet> getBilete() { return bilete; }
    public void setBilete(Set<Bilet> bilete) { this.bilete = bilete; }
}