package com.example.API.dto;

import com.example.API.model.Persona;
import com.example.API.model.Reclamo;
import com.example.API.model.Unidad;

import java.io.Serializable;
import java.time.LocalDate;

public class ReclamoDTO implements Serializable {
    public static final int UBICACION_MAX_LENGTH = 300;
    public static final int DESCRIPCION_MAX_LENGTH = 300;
    public static final int ESTADO_MAX_LENGTH = 100;

    public static final int COMENTARIO_MAX_LENGTH = 300;

    private Long id;
    private String ubicacion;
    private String descripcion;
    private String estado;
    private Long personaId;
    private Long unidadId;

    //private String comentario;
    public ReclamoDTO(){

    }
    public ReclamoDTO(Long id, String ubicacion, String descripcion, String estado, Long persona, Long unidad){
        this.id=id;
        this.ubicacion=ubicacion;
        this.descripcion=descripcion;
        this.estado=estado;
        this.personaId=persona;
        this.unidadId=unidad;
        //this.comentario=comentario;
    }
    public Reclamo newReclamo(Persona persona, Unidad unidad) {
        return new Reclamo( this.id , this.ubicacion,this.descripcion,this.estado,persona,unidad);
    }

    public ReclamoDTO update(Reclamo reclamo){
        if (this.ubicacion!= null && ubicacion.length() <= UBICACION_MAX_LENGTH)
            reclamo.setUbicacion(this.ubicacion);
        if (this.descripcion != null && descripcion.length() <= DESCRIPCION_MAX_LENGTH)
            reclamo.setDescripcion(this.descripcion);
        if (this.estado != null && estado.length() <= ESTADO_MAX_LENGTH)
            reclamo.setEstado(this.estado);
        //if(this.comentario!=null && comentario.length()<= COMENTARIO_MAX_LENGTH)
          //  reclamo.setComentario(comentario);
        Long personaId = ( this.personaId == null )? reclamo.getPersona().getId() : this.personaId;
        Long unidadId = ( this.unidadId == null )? reclamo.getUnidad().getId() : this.unidadId;
        return new ReclamoDTO(reclamo.getId(), reclamo.getUbicacion(),reclamo.getDescripcion(),reclamo.getEstado(),personaId,unidadId);
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
    public Long getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Long p) {
        this.personaId=p;
    }
    public Long getUnidadId() {
        return unidadId;
    }

    public void setUnidadId(Long u) {
        this.unidadId=u;
    }
}