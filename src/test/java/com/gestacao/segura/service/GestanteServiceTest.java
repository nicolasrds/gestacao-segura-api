package com.gestacao.segura.service;

import com.gestacao.segura.dto.GestanteRequestDTO;
import com.gestacao.segura.dto.GestanteResponseDTO;
import com.gestacao.segura.entity.Gestante;
import com.gestacao.segura.mapper.GestanteMapper;
import com.gestacao.segura.repository.GestanteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class GestanteServiceTest {
    @Mock
    private GestanteRepository gestanteRepository;
    @Mock private GestanteMapper gestanteMapper;
    @InjectMocks
    private GestanteService gestanteService;

    @Mock private Gestante gestante;
    @Mock private GestanteRequestDTO requestDTO;
    @Mock private GestanteResponseDTO responseDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        Page<Gestante> page = new PageImpl<>(List.of(gestante));
        when(gestanteRepository.findAll(any(Pageable.class))).thenReturn(page);
        when(gestanteMapper.toDto(gestante)).thenReturn(responseDTO);

        Page<GestanteResponseDTO> result = gestanteService.findAll(Pageable.unpaged());
        assertFalse(result.isEmpty());
    }

    @Test
    void testFindById() {
        when(gestanteRepository.findById(1L)).thenReturn(Optional.of(gestante));
        when(gestanteMapper.toDto(gestante)).thenReturn(responseDTO);

        GestanteResponseDTO result = gestanteService.findById(1L);
        assertNotNull(result);
    }

    @Test
    void testSave() {
        when(gestanteMapper.toEntity(requestDTO)).thenReturn(gestante);
        when(gestanteRepository.save(gestante)).thenReturn(gestante);
        when(gestanteMapper.toDto(gestante)).thenReturn(responseDTO);

        GestanteResponseDTO result = gestanteService.save(requestDTO);
        assertNotNull(result);
    }

    @Test
    void testUpdate() {
        when(gestanteRepository.getReferenceById(1L)).thenReturn(gestante);
        when(gestanteMapper.toDto(gestante)).thenReturn(responseDTO);

        GestanteResponseDTO result = gestanteService.update(1L, requestDTO);
        assertNotNull(result);
    }

    @Test
    void testDelete() {
        when(gestanteRepository.existsById(1L)).thenReturn(true);
        gestanteService.delete(1L);
        verify(gestanteRepository).deleteById(1L);
    }
}