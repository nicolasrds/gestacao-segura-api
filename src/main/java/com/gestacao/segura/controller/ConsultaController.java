package com.gestacao.segura.controller;

import com.gestacao.segura.dto.ConsultaRequestDTO;
import com.gestacao.segura.dto.ConsultaResponseDTO;
import com.gestacao.segura.service.ConsultaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequestMapping("/v1/consulta")
@RestController
public class ConsultaController {

    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @GetMapping
    public ResponseEntity<Page<ConsultaResponseDTO>> findAll(Pageable pageable){
        Page<ConsultaResponseDTO> consultas = consultaService.findAll(pageable);
        return ResponseEntity.ok(consultas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaResponseDTO> findById(@PathVariable Long id){
        var consulta = consultaService.findById(id);
        return ResponseEntity.ok(consulta);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<ConsultaResponseDTO> save(@RequestBody ConsultaRequestDTO dto){
        var consulta = consultaService.save(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand((consulta.idConsulta())).toUri();

        return ResponseEntity.created(uri).body(consulta);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<ConsultaResponseDTO> update(@PathVariable Long id, @RequestBody ConsultaRequestDTO dto){
        var consulta = consultaService.update(id, dto);

        return ResponseEntity.ok(consulta);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<ConsultaResponseDTO> delte(@PathVariable Long id){
        consultaService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
