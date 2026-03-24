package com.example.demo.controller;

import com.example.demo.entity.Autor;
import com.example.demo.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @GetMapping
    public ResponseEntity<List<Autor>> findAll() {
        List<Autor> lista = autorService.findAll();
        return ResponseEntity.ok().body(lista);
    }

    @PostMapping
    public ResponseEntity<Autor> criar(@RequestBody Autor autor) {
        Autor resultado = autorService.criar(autor);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(autor.getId())
                .toUri();
        return ResponseEntity.created(uri).body(resultado);
    }

    @GetMapping("/{id}")
    public Optional<Autor> buscarPorId(@PathVariable Long id) {
        return autorService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        autorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
