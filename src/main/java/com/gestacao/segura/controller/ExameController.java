package com.gestacao.segura.controller;

import com.gestacao.segura.dto.ExameRequestDTO;
import com.gestacao.segura.dto.ExameResponseDTO;
import com.gestacao.segura.service.ExameService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequestMapping("/v1/exame")
@RestController
public class ExameController {

    private final ExameService exameService;

    public ExameController(ExameService exameService) {
        this.exameService = exameService;
    }

    @GetMapping
    public ResponseEntity<Page<ExameResponseDTO>> findAll(Pageable pageable) {
        Page<ExameResponseDTO> examePage = exameService.findAll(pageable);
        return ResponseEntity.ok(examePage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExameResponseDTO> findById(@PathVariable Long id) {
        var exame = exameService.findById(id);
        return ResponseEntity.ok(exame);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<ExameResponseDTO> save(@RequestBody @Valid ExameRequestDTO dto) {
        var exame = exameService.save(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand((exame.id())).toUri();
        return ResponseEntity.created(uri).body(exame);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<ExameResponseDTO> update(@PathVariable Long id, @RequestBody @Valid ExameRequestDTO dto){
        var exame = exameService.update(id, dto);

        return ResponseEntity.ok(exame);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<ExameResponseDTO> delete(@PathVariable Long id){
        exameService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
