package com.example.API.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="persona")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String documento;

    @Column
    private String nombre;

    @Column
    private String contrasenia;

    @OneToOne(mappedBy="persona")
    private Duenio duenio;

    @OneToOne(mappedBy="persona")
    private Inquilino inquilino;

    @OneToMany(mappedBy = "persona")
    @JsonIgnore
    private Set<Reclamo> reclamos = new HashSet<>();

    public Persona() {
    }

    public Persona(Long id, String documento, String nombre, String contrasenia) {
        this.id = id;
        this.documento = documento;
        this.nombre = nombre;
        this.contrasenia=contrasenia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumento() {
        return documento;
    }

    public void setTitulo(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}
