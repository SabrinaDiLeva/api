package com.example.API.service;

import com.example.API.dto.DuenioDTO;
import com.example.API.model.Duenio;
import com.example.API.model.Persona;
import com.example.API.repository.IDuenioRepository;
import com.example.API.repository.IPersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class DuenioService implements IService<Duenio,DuenioDTO> {
    private IDuenioRepository iDuenioRepository;
    private IPersonaRepository iPersonaRepository;

    @Autowired
    public DuenioService(IDuenioRepository iDuenioRepository, IPersonaRepository iPersonaRepository) {
        this.iDuenioRepository = iDuenioRepository;
        this.iPersonaRepository=iPersonaRepository;

    }

    @Override
    public List<Duenio> listar() {
        return iDuenioRepository.findAll();
    }

    @Override
    public Duenio guardar(DuenioDTO duenio) {
        Persona persona = iPersonaRepository.findById(duenio.getPersona()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Duenio d = duenio.newDuenio(persona);
        return iDuenioRepository.save(d);
    }

    @Override
    public Duenio buscar(Long id) {
        return iDuenioRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public void eliminar(Long id) {
        this.buscar(id);
        iDuenioRepository.deleteById(id);
    }

    @Override
    public Duenio modificar(Long id, DuenioDTO dto) {
        Duenio duenio = this.buscar(id);
        return this.guardar(dto.update(duenio));
    }

    public boolean autenticar(String documento, String contrasenia){
        List<Duenio> duenios = listar();
        boolean rta =false;
        for(Duenio duenio : duenios){
            if(duenio.getPersona().getDocumento().equals(documento) && duenio.getPersona().getContrasenia().equals(contrasenia)){
                rta=true;
                break;
            }
        }
        return rta;
    }
}
