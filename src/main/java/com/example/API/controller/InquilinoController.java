package com.example.API.controller;

import com.example.API.dto.InquilinoDTO;
import com.example.API.model.Inquilino;
import com.example.API.service.InquilinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inquilino")
@CrossOrigin( origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
public class InquilinoController {
    @Autowired
    private InquilinoService inquilinoService;

    @PostMapping
    public ResponseEntity<Inquilino> guardar(@RequestBody InquilinoDTO inquilino) {
        return ResponseEntity.ok(inquilinoService.guardar(inquilino));
    }

    //@CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public ResponseEntity<List<Inquilino>>  listar() {
        return ResponseEntity.ok(inquilinoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inquilino> buscar(@PathVariable Long id) {
        Inquilino inquilino = inquilinoService.buscar(id);
        return ResponseEntity.ok(inquilino);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        inquilinoService.eliminar(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inquilino> modificar(@PathVariable( name = "id") Long id, @RequestBody InquilinoDTO inquilino) {
        return ResponseEntity.ok(inquilinoService.modificar(id, inquilino));
    }
}
