package com.gestacao.segura.controller;

import com.gestacao.segura.dto.MedicoRequestDTO;
import com.gestacao.segura.dto.MedicoResponseDTO;
import com.gestacao.segura.service.MedicoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequestMapping("/v1/medico")
@RestController
public class MedicoController {

    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @GetMapping
    public ResponseEntity<Page<MedicoResponseDTO>> findAll(Pageable pageable){
        Page<MedicoResponseDTO> medicos = medicoService.findAll(pageable);
        return ResponseEntity.ok(medicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoResponseDTO> findById(@PathVariable Long id){
        var medico = medicoService.findById(id);
        return ResponseEntity.ok(medico);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<MedicoResponseDTO> save(@RequestBody MedicoRequestDTO dto){
        var medico = medicoService.save(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand((medico.idMedico())).toUri();

        return ResponseEntity.created(uri).body(medico);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<MedicoResponseDTO> update(@PathVariable Long id, @RequestBody MedicoRequestDTO dto){
        var medico = medicoService.update(id, dto);

        return ResponseEntity.ok(medico);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<MedicoResponseDTO> delte(@PathVariable Long id){
        medicoService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
