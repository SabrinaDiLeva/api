package com.example.API.service;

import java.util.Set;

import com.example.API.dto.EdificioDTO;
import com.example.API.dto.UnidadDTO;
import com.example.API.model.Duenio;
import com.example.API.model.Edificio;
import com.example.API.model.Inquilino;
import com.example.API.model.Unidad;
import com.example.API.repository.IDuenioRepository;
import com.example.API.repository.IEdificioRepository;
import com.example.API.repository.IInquilinoRepository;
import com.example.API.repository.IUnidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.yaml.snakeyaml.events.Event.ID;

import java.util.List;
import java.util.Optional;

@Service
public class UnidadService implements IService<Unidad,UnidadDTO> {
    private IUnidadRepository iUnidadRepository;
    private IEdificioRepository iEdificioRepository;
    private IDuenioRepository iDuenioRepository;
    private IInquilinoRepository iInquilinoRepository;

    @Autowired
    public UnidadService(IUnidadRepository iUnidadRepository, IEdificioRepository iEdificioRepository, IDuenioRepository iDuenioRepository, IInquilinoRepository iInquilinoRepository) {
        this.iUnidadRepository = iUnidadRepository;
        this.iEdificioRepository=iEdificioRepository;
        this.iDuenioRepository=iDuenioRepository;
        this.iInquilinoRepository=iInquilinoRepository;
    }

    @Override
    public List<Unidad> listar() {
        return iUnidadRepository.findAll();
    }

    @Override
    public Unidad guardar(UnidadDTO unidad) {
        Edificio edificio = iEdificioRepository.findById(unidad.getEdificio()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Unidad u = unidad.newUnidad(edificio);
        return iUnidadRepository.save(u);
    }

    @Override
    public Unidad buscar(Long id) {
        return iUnidadRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public void eliminar(Long id) {
        this.buscar(id);
        iUnidadRepository.deleteById(id);
    }

    @Override
    public Unidad modificar(Long id, UnidadDTO dto) {
        Unidad unidad = this.buscar(id);
        return this.guardar(dto.update(unidad));
    }

    public List<Unidad> listarPorIdEdificio(Long id) {
        return iUnidadRepository.findAllByEdificio_Id(id);
    }

    public Set<Duenio> listarDuenios(Long id){
        Unidad unidad = buscar(id);
        return unidad.getDuenio();
    }

    public Set<Inquilino> listarInquilinos(Long id){
        Unidad unidad = buscar(id);
        return unidad.getInquilino();
    }

    public Duenio buscarDuenio(Long id) {
        return iDuenioRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Inquilino buscarInquilino(Long id) {
        return iInquilinoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void transferir(Long unidadId, Long duenioId){
        Unidad unidad = buscar(unidadId);
        Set<Duenio> duenios = unidad.getDuenio();
        if(duenios.size()==1){
            duenios.removeAll(duenios);
            agregarDuenio(unidadId,duenioId);
        }
    }

    public Unidad agregarDuenio(Long u, Long d){
        Unidad unidad = buscar(u);
        Set<Duenio> duenios =unidad.getDuenio();
        duenios.add(buscarDuenio(d));
        unidad.setDuenio(duenios);
        return iUnidadRepository.save(unidad);
    }

    public Unidad agregarInquilino(Long u, Long i){
        Unidad unidad = buscar(u);
        Set<Inquilino> inquilinos =unidad.getInquilino();
        inquilinos.add(buscarInquilino(i));
        unidad.setInquilino(inquilinos);
        return iUnidadRepository.save(unidad);
    }

    public Unidad alquilar(Long u, Long i){
        Unidad unidad = buscar(u);
        Set<Inquilino> inquilinos =unidad.getInquilino();
        if(inquilinos.size()==0){
            inquilinos.add(buscarInquilino(i));
            unidad.setHabitado(true);
            unidad.setInquilino(inquilinos);
        }
        return iUnidadRepository.save(unidad);

    }


    public Unidad liberar(Long u){
        Unidad unidad = buscar(u);
        Set<Inquilino> inquilinos =unidad.getInquilino();
        inquilinos.removeAll(inquilinos);
        unidad.setInquilino(inquilinos);
        unidad.setHabitado(false);
        return iUnidadRepository.save(unidad);
    }

}