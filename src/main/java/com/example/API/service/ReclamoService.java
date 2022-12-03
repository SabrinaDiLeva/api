package com.example.API.service;

import com.example.API.dto.ReclamoDTO;
import com.example.API.model.Persona;
import com.example.API.model.Reclamo;
import com.example.API.model.Unidad;
import com.example.API.repository.IPersonaRepository;
import com.example.API.repository.IReclamoRepository;
import com.example.API.repository.IUnidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ReclamoService implements IService<Reclamo,ReclamoDTO> {
    private IReclamoRepository iReclamoRepository;
    private IPersonaRepository iPersonaRepository;
    private IUnidadRepository iUnidadRepository;

    @Autowired
    public ReclamoService(IReclamoRepository iReclamoRepository, IPersonaRepository iPersonaRepository, IUnidadRepository iUnidadRepository) {
        this.iReclamoRepository = iReclamoRepository;
        this.iPersonaRepository=iPersonaRepository;
        this.iUnidadRepository=iUnidadRepository;

    }

    @Override
    public List<Reclamo> listar() {
        return iReclamoRepository.findAll();
    }

    @Override
    public Reclamo guardar(ReclamoDTO reclamo) {
        Persona persona = iPersonaRepository.findById(reclamo.getPersonaId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Unidad unidad = iUnidadRepository.findById(reclamo.getUnidadId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Reclamo i = reclamo.newReclamo(persona, unidad);
        return iReclamoRepository.save(i);
    }

    @Override
    public Reclamo buscar(Long id) {
        return iReclamoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public void eliminar(Long id) {
        this.buscar(id);
        iReclamoRepository.deleteById(id);
    }

    @Override
    public Reclamo modificar(Long id, ReclamoDTO dto) {
        Reclamo reclamo = this.buscar(id);
        return this.guardar(dto.update(reclamo));
    }

    public List<Reclamo> listarPorIdUnidad(Long id) {
        return iReclamoRepository.findAllByUnidad_Id(id);
    }

    public List<Reclamo> listarPorIdPersona(Long id) {
        return iReclamoRepository.findAllByPersona_Id(id);
    }
}
