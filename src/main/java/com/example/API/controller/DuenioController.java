package com.example.API.controller;

import com.example.API.dto.DuenioDTO;
import com.example.API.model.Duenio;
import com.example.API.service.DuenioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin( origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
@RequestMapping("/duenio")
public class DuenioController {
    @Autowired
    private DuenioService duenioService;

    @PostMapping
    public ResponseEntity<Duenio> guardar(@RequestBody DuenioDTO duenio) {
        return ResponseEntity.ok(duenioService.guardar(duenio));
    }

    //@CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public ResponseEntity<List<Duenio>>  listar() {
        return ResponseEntity.ok(duenioService.listar());
    }

    /*@CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/edificio/{id}")
    public ResponseEntity<List<Duenio>>  listarPorEdificio(@PathVariable Long id) {
        return ResponseEntity.ok(duenioService.listarPorIdEdificio(id));
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<Duenio> buscar(@PathVariable Long id) {
        Duenio duenio = duenioService.buscar(id);
        return ResponseEntity.ok(duenio);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        duenioService.eliminar(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Duenio> modificar(@PathVariable( name = "id") Long id, @RequestBody DuenioDTO duenio) {
        return ResponseEntity.ok(duenioService.modificar(id, duenio));
    }
}