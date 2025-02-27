package com.gestacao.segura.controller;

import com.gestacao.segura.dto.GestanteRequestDTO;
import com.gestacao.segura.dto.GestanteResponseDTO;
import com.gestacao.segura.service.GestanteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequestMapping("/v1/gestante")
@RestController
public class GestanteController {

    private final GestanteService gestanteService;

    public GestanteController(GestanteService gestanteService) {
        this.gestanteService = gestanteService;
    }

    @GetMapping
    public ResponseEntity<Page<GestanteResponseDTO>> findAll(Pageable pageable){
        Page<GestanteResponseDTO> gestantes = gestanteService.findAll(pageable);
        return ResponseEntity.ok(gestantes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GestanteResponseDTO> findById(@PathVariable Long id){
        var gestante = gestanteService.findById(id);
        return ResponseEntity.ok(gestante);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<GestanteResponseDTO> save(@RequestBody GestanteRequestDTO dto){
        var gestante = gestanteService.save(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand((gestante.id())).toUri();

        return ResponseEntity.created(uri).body(gestante);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<GestanteResponseDTO> update(@PathVariable Long id, @RequestBody GestanteRequestDTO dto){
        var gestante = gestanteService.update(id, dto);

        return ResponseEntity.ok(gestante);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<GestanteResponseDTO> delte(@PathVariable Long id){
        gestanteService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
