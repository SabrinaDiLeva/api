package com.example.API.service;
import com.example.API.dto.EdificioDTO;
import com.example.API.dto.ImagenDTO;
import com.example.API.dto.UnidadDTO;
import com.example.API.model.*;
import com.example.API.repository.IEdificioRepository;
import com.example.API.repository.IImagenRepository;
import com.example.API.repository.IReclamoRepository;
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
public class ImagenService implements IService<Imagen, ImagenDTO> {
    private IImagenRepository iImagenRepository;
    private IReclamoRepository iReclamoRepository;

    @Autowired
    public ImagenService(IImagenRepository iImagenRepository, IReclamoRepository iReclamoRepository) {
        this.iImagenRepository = iImagenRepository;
        this.iReclamoRepository=iReclamoRepository;

    }

    @Override
    public List<Imagen> listar() {
        return iImagenRepository.findAll();
    }

    @Override
    public Imagen guardar(ImagenDTO imagen) {
        Reclamo reclamo = iReclamoRepository.findById(imagen.getReclamo()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Imagen i = imagen.newImagen(reclamo);
        return iImagenRepository.save(i);
    }

    @Override
    public Imagen buscar(Long id) {
        return iImagenRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public void eliminar(Long id) {
        this.buscar(id);
        iImagenRepository.deleteById(id);
    }

    @Override
    public Imagen modificar(Long id, ImagenDTO dto) {
        Imagen imagen = this.buscar(id);
        return this.guardar(dto.update(imagen));
    }
}