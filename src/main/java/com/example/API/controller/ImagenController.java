package com.example.API.controller;

import com.example.API.dto.ImagenDTO;
import com.example.API.model.Duenio;
import com.example.API.model.Imagen;
import com.example.API.service.DuenioService;
import com.example.API.service.ImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin( origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
@RequestMapping("/imagen")
public class ImagenController {
    @Autowired
    private ImagenService imagenService;

    @PostMapping
    public ResponseEntity<Imagen> guardar(@RequestBody ImagenDTO imagen) {
        return ResponseEntity.ok(imagenService.guardar(imagen));
    }

    //@CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public ResponseEntity<List<Imagen>>  listar() {
        return ResponseEntity.ok(imagenService.listar());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Imagen> buscar(@PathVariable Long id) {
        Imagen imagen = imagenService.buscar(id);
        return ResponseEntity.ok(imagen);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        imagenService.eliminar(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Imagen> modificar(@PathVariable( name = "id") Long id, @RequestBody ImagenDTO imagen) {
        return ResponseEntity.ok(imagenService.modificar(id, imagen));
    }
}