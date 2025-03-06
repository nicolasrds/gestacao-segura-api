package com.gestacao.segura.service;

import com.gestacao.segura.dto.MedicoRequestDTO;
import com.gestacao.segura.dto.MedicoResponseDTO;
import com.gestacao.segura.entity.Medico;
import com.gestacao.segura.mapper.MedicoMapper;
import com.gestacao.segura.repository.MedicoRepository;
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

class MedicoServiceTest {
    @Mock private MedicoRepository medicoRepository;
    @Mock private MedicoMapper medicoMapper;
    @InjectMocks private MedicoService medicoService;

    @Mock private Medico medico;
    @Mock private MedicoRequestDTO requestDTO;
    @Mock private MedicoResponseDTO responseDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindAll() {
        Page<Medico> page = new PageImpl<>(List.of(medico));
        when(medicoRepository.findAll(any(Pageable.class))).thenReturn(page);
        when(medicoMapper.toDto(medico)).thenReturn(responseDTO);

        Page<MedicoResponseDTO> result = medicoService.findAll(Pageable.unpaged());
        assertFalse(result.isEmpty());
    }

    @Test
    void shouldFindById() {
        when(medicoRepository.findById(1L)).thenReturn(Optional.of(medico));
        when(medicoMapper.toDto(medico)).thenReturn(responseDTO);

        MedicoResponseDTO result = medicoService.findById(1L);
        assertNotNull(result);
    }

    @Test
    void shouldSave() {
        when(medicoMapper.toEntity(requestDTO)).thenReturn(medico);
        when(medicoRepository.save(medico)).thenReturn(medico);
        when(medicoMapper.toDto(medico)).thenReturn(responseDTO);

        MedicoResponseDTO result = medicoService.save(requestDTO);
        assertNotNull(result);
    }

    @Test
    void shouldUpdate() {
        when(medicoRepository.getReferenceById(1L)).thenReturn(medico);
        when(medicoMapper.toDto(medico)).thenReturn(responseDTO);

        MedicoResponseDTO result = medicoService.update(1L, requestDTO);
        assertNotNull(result);
    }

    @Test
    void shouldDelete() {
        when(medicoRepository.existsById(1L)).thenReturn(true);
        medicoService.delete(1L);
        verify(medicoRepository).deleteById(1L);
    }
}
