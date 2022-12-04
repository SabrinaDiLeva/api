package com.example.API.dto;


import com.example.API.model.Duenio;
import com.example.API.model.Edificio;
import com.example.API.model.Inquilino;
import com.example.API.model.Unidad;

import java.io.Serializable;
import java.util.Set;

public class UnidadDTO implements Serializable {
    private Long id;
    private int piso;
    private int nro;
    private boolean habitado;
    private Long edificioId;

    private Set<Duenio> duenios;

    private Set<Inquilino> inquilinos;
    //private String inquilinos;
    //private String duenios;

    public UnidadDTO(){

    }
    public UnidadDTO(Long id, int piso, int nro, boolean habitado, Long edificioId){
        this.id=id;
        this.piso=piso;
        this.nro=nro;
        this.habitado=habitado;
        this.edificioId=edificioId;
    }

    public UnidadDTO(Long id, int piso, int nro, boolean habitado, Long edificioId, Set<Duenio> duenios, Set<Inquilino> inquilinos){
        this.id=id;
        this.piso=piso;
        this.nro=nro;
        this.habitado=habitado;
        this.edificioId=edificioId;
        this.duenios=duenios;
        this.inquilinos=inquilinos;
    }


    public Unidad newUnidad(Edificio edificio) {
        return new Unidad( this.id , this.piso,this.nro,this.habitado,edificio);
    }



    /*public UnidadDTO update(Unidad unidad){
        Long edificioId = ( this.edificioId == null )? unidad.getEdificio().getId() : this.edificioId;
        return new UnidadDTO(unidad.getId(),unidad.getPiso(),unidad.getNro(),unidad.getHabitado(),edificioId );
    }*/
    public UnidadDTO update(Unidad unidad){
        unidad.setPiso(this.piso);
        unidad.setNro(this.nro);
        unidad.setHabitado(this.habitado);
        Long edificioId = ( this.edificioId == null )? unidad.getEdificio().getId() : this.edificioId;
        unidad.setDuenio(this.duenios);
        unidad.setInquilino(this.inquilinos);
        return new UnidadDTO(unidad.getId(), unidad.getPiso(), unidad.getNro(),unidad.getHabitado(),unidad.getEdificio().getId(),unidad.getDuenio(),unidad.getInquilino());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }
    public int getNro() {
        return nro;
    }

    public void setNro(int nro) {
        this.nro = nro;
    }

    public boolean getHabitado() {
        return habitado;
    }

    public void setHabitado(boolean h) {
        this.habitado = h;
    }

    public Long getEdificio(){
        return this.edificioId;
    }
    public void setEdificio(Long edificio){
        this.edificioId=edificio;
    }

    public boolean isHabitado() {
        return habitado;
    }

    public Long getEdificioId() {
        return edificioId;
    }

    public void setEdificioId(Long edificioId) {
        this.edificioId = edificioId;
    }

    public Set<Duenio> getDuenios() {
        return duenios;
    }

    public void setDuenios(Set<Duenio> duenios) {
        this.duenios = duenios;
    }

    public Set<Inquilino> getInquilinos() {
        return inquilinos;
    }

    public void setInquilinos(Set<Inquilino> inquilinos) {
        this.inquilinos = inquilinos;
    }
}
