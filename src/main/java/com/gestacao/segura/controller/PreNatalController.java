package com.gestacao.segura.controller;

import com.gestacao.segura.dto.PreNatalRequestDTO;
import com.gestacao.segura.dto.PreNatalResponseDTO;
import com.gestacao.segura.service.PreNatalService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequestMapping("/pre-natal")
@RestController
public class PreNatalController {

    private final PreNatalService preNatalService;

    public PreNatalController(PreNatalService preNatalService) {
        this.preNatalService = preNatalService;
    }

    @GetMapping
    public ResponseEntity<Page<PreNatalResponseDTO>> findAll(Pageable pageable) {
        Page<PreNatalResponseDTO> preNatalPage = preNatalService.findAll(pageable);
        return ResponseEntity.ok(preNatalPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PreNatalResponseDTO> findById(@PathVariable Long id) {
        var preNatal = preNatalService.findById(id);
        return ResponseEntity.ok(preNatal);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<PreNatalResponseDTO> save(@RequestBody @Valid PreNatalRequestDTO dto) {
        var preNatal = preNatalService.save(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand((preNatal.id())).toUri();
        return ResponseEntity.created(uri).body(preNatal);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<PreNatalResponseDTO> update(@PathVariable Long id, @RequestBody @Valid PreNatalRequestDTO dto){
        var preNatal = preNatalService.update(id, dto);

        return ResponseEntity.ok(preNatal);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<PreNatalResponseDTO> delete(@PathVariable Long id){
        preNatalService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
