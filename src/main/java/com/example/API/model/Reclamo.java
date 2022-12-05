package com.example.API.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="reclamo")
public class Reclamo{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String ubicacion;

    @Column
    private String descripcion;

    @Column
    private String estado;

    @Column
    private LocalDate fecha;

    @Column
    private String comentario;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "unidad_id",referencedColumnName = "id")
    private Unidad unidad;

    //persona
    @ManyToOne
    @JoinColumn(name = "persona_id",referencedColumnName = "id")
    private Persona persona;

    @OneToMany(mappedBy = "reclamo")
    @JsonIgnore
    private Set<Imagen> imagenes = new HashSet<>();


    public Reclamo(){

    }
    public Reclamo(Long id, String ubicacion, String descripcion, String estado, Persona persona, Unidad unidad, LocalDate fecha, String comentario){
        this.id=id;
        this.ubicacion=ubicacion;
        this.descripcion=descripcion;
        this.estado=estado;
        this.persona=persona;
        this.unidad=unidad;
        this.fecha=fecha;
        this.comentario=comentario;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String u) {
        this.ubicacion = u;
    }
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String d) {
        this.descripcion = d;
    }
    public String getEstado() {
        return estado;
    }

    public void setEstado(String u) {
        this.estado = u;
    }
    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona p) {
        this.persona=p;
    }
    public Unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidad u) {
        this.unidad=u;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Set<Imagen> getImagenes() {
        return imagenes;
    }

    public void setImagenes(Set<Imagen> imagenes) {
        this.imagenes = imagenes;
    }
}
