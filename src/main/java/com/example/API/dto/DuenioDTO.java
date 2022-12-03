package com.example.API.dto;

import com.example.API.model.Duenio;
import com.example.API.model.Persona;

import java.io.Serializable;

public class DuenioDTO implements Serializable {
    private Long id;
    private Long personaId;

    public DuenioDTO(){

    }
    public DuenioDTO(Long id, Long persona){
        this.id = id;
        this.personaId=persona;
    }

    public Duenio newDuenio(Persona persona) {
        return new Duenio( this.id , persona);
    }

    public DuenioDTO update(Duenio duenio){
        Long personaId = ( this.personaId == null )? duenio.getPersona().getId() : this.personaId;
        return new DuenioDTO(duenio.getId(), personaId);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getPersona() {
        return personaId;
    }

    public void setPersona(Long id) {
        this.personaId = id;
    }
}