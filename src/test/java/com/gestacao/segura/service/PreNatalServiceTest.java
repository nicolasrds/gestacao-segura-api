package com.gestacao.segura.service;

import com.gestacao.segura.dto.PreNatalRequestDTO;
import com.gestacao.segura.dto.PreNatalResponseDTO;
import com.gestacao.segura.entity.PreNatal;
import com.gestacao.segura.mapper.PreNatalMapper;
import com.gestacao.segura.repository.PreNatalRepository;
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

class PreNatalServiceTest {
    @Mock private PreNatalRepository preNatalRepository;
    @Mock private PreNatalMapper preNatalMapper;
    @InjectMocks private PreNatalService preNatalService;

    @Mock private PreNatal preNatal;
    @Mock private PreNatalRequestDTO requestDTO;
    @Mock private PreNatalResponseDTO responseDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindAll() {
        Page<PreNatal> page = new PageImpl<>(List.of(preNatal));
        when(preNatalRepository.findAll(any(Pageable.class))).thenReturn(page);
        when(preNatalMapper.toDto(preNatal)).thenReturn(responseDTO);

        Page<PreNatalResponseDTO> result = preNatalService.findAll(Pageable.unpaged());
        assertFalse(result.isEmpty());
    }

    @Test
    void shouldFindById() {
        when(preNatalRepository.findById(1L)).thenReturn(Optional.of(preNatal));
        when(preNatalMapper.toDto(preNatal)).thenReturn(responseDTO);

        PreNatalResponseDTO result = preNatalService.findById(1L);
        assertNotNull(result);
    }

    @Test
    void shouldSave() {
        when(preNatalMapper.toEntity(requestDTO)).thenReturn(preNatal);
        when(preNatalRepository.save(preNatal)).thenReturn(preNatal);
        when(preNatalMapper.toDto(preNatal)).thenReturn(responseDTO);

        PreNatalResponseDTO result = preNatalService.save(requestDTO);
        assertNotNull(result);
    }

    @Test
    void shouldUpdate() {
        when(preNatalRepository.findById(1L)).thenReturn(Optional.of(preNatal));
        when(preNatalMapper.toDto(preNatal)).thenReturn(responseDTO);

        PreNatalResponseDTO result = preNatalService.update(1L, requestDTO);
        assertNotNull(result);
    }

    @Test
    void shouldDelete() {
        when(preNatalRepository.existsById(1L)).thenReturn(true);
        preNatalService.delete(1L);
        verify(preNatalRepository).deleteById(1L);
    }
}
