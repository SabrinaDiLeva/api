package com.example.API.service;

import com.example.API.dto.EdificioDTO;
import com.example.API.model.*;
import com.example.API.repository.IEdificioRepository;
import com.example.API.repository.IUnidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EdificioService implements IService<Edificio, EdificioDTO> {
    private IEdificioRepository iEdificioRepository;
    private IUnidadRepository iUnidadRepository;

    @Autowired
    public EdificioService(IEdificioRepository iEdificioRepository, IUnidadRepository iUnidadRepository) {
        this.iEdificioRepository = iEdificioRepository;
        this.iUnidadRepository=iUnidadRepository;

    }

    @Override
    public List<Edificio> listar() {
        return iEdificioRepository.findAll();
    }

    @Override
    public Edificio guardar(EdificioDTO edificio) {
        Optional<Edificio> result =  Optional.of(iEdificioRepository.save(edificio.newEdificio()));
        return result.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    @Override
    public Edificio buscar(Long id) {
        return iEdificioRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public void eliminar(Long id) {
        this.buscar(id);
        iEdificioRepository.deleteById(id);
    }

    @Override
    public Edificio modificar(Long id, EdificioDTO dto) {
        Edificio edificio = this.buscar(id);
        return this.guardar(dto.update(edificio));
    }
    /*

    public List<Unidad> listarLibres(Long id){
        List<Unidad> unidadesDelEdificio= iUnidadRepository.findAllByEdificio_Id(id);
        List<Unidad> unidadesLibres = new ArrayList<>();
        for(Unidad unidad : unidadesDelEdificio){
            if(!unidad.getHabitado()){
                unidadesLibres.add(unidad);
            }
        }
        return unidadesLibres;
    }
    public List<Duenio> listarDuenios(Long id){
        List<Duenio> dueniosDelEdificio=new ArrayList<>();
        Edificio edificio = buscar(id);
        Set<Unidad> unidades =edificio.getUnidades();
        for(Unidad unidad : unidades){
            Set<Duenio> duenios = unidad.getDuenio();
            for (Duenio duenio : duenios){
                dueniosDelEdificio.add(duenio);
            }
        }
        return dueniosDelEdificio;
    }

    public List<Inquilino> listarInquilinos(Long id){
        List<Inquilino> inquilinosDelEdificio=new ArrayList<>();
        Edificio edificio = buscar(id);
        Set<Unidad> unidades =edificio.getUnidades();
        for(Unidad unidad : unidades){
            Set<Inquilino> inquilinos = unidad.getInquilino();
            for (Inquilino inquilino : inquilinos){
                inquilinosDelEdificio.add(inquilino);
            }
        }
        return inquilinosDelEdificio;
    }

    public List<Reclamo> listarReclamos(Long id){
        List<Reclamo> reclamosDelEdificio = new ArrayList<>();
        Edificio edificio = buscar(id);
        Set<Unidad> unidades =edificio.getUnidades();
        for(Unidad unidad : unidades){
            Set<Reclamo> reclamos = unidad.getReclamo();
            for (Reclamo reclamo : reclamos){
                reclamosDelEdificio.add(reclamo);
            }
        }
        return reclamosDelEdificio;
    }*/
}