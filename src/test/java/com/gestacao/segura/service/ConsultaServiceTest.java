package com.gestacao.segura.service;

import com.gestacao.segura.dto.ConsultaRequestDTO;
import com.gestacao.segura.dto.ConsultaResponseDTO;
import com.gestacao.segura.entity.Consulta;
import com.gestacao.segura.entity.PreNatal;
import com.gestacao.segura.mapper.ConsultaMapper;
import com.gestacao.segura.repository.ConsultaRepository;
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
import static org.mockito.Mockito.*;

class ConsultaServiceTest {
    @Mock private ConsultaRepository consultaRepository;
    @Mock private ConsultaMapper consultaMapper;
    @InjectMocks private ConsultaService consultaService;

    @Mock private Consulta consulta;
    @Mock private ConsultaRequestDTO requestDTO;
    @Mock private ConsultaResponseDTO responseDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindAll() {
        Page<Consulta> page = new PageImpl<>(List.of(consulta));
        when(consultaRepository.findAll(any(Pageable.class))).thenReturn(page);
        when(consultaMapper.toDto(consulta)).thenReturn(responseDTO);

        Page<ConsultaResponseDTO> result = consultaService.findAll(Pageable.unpaged());
        assertFalse(result.isEmpty());
    }

    @Test
    void shouldFindById() {
        when(consultaRepository.findById(1L)).thenReturn(Optional.of(consulta));
        when(consultaMapper.toDto(consulta)).thenReturn(responseDTO);

        ConsultaResponseDTO result = consultaService.findById(1L);
        assertNotNull(result);
    }

    @Test
    void shouldSave() {
        PreNatal preNatalMock = mock(PreNatal.class);
        when(preNatalMock.getAlturaGestante()).thenReturn(1.70);

        when(consulta.getPreNatal()).thenReturn(preNatalMock);

        when(consultaMapper.toEntity(requestDTO)).thenReturn(consulta);
        when(consultaRepository.save(consulta)).thenReturn(consulta);
        when(consultaMapper.toDto(consulta)).thenReturn(responseDTO);

        ConsultaResponseDTO result = consultaService.save(requestDTO);

        assertNotNull(result);
        verify(consultaRepository).save(consulta);
    }


    @Test
    void shouldUpdate() {
        when(consultaRepository.getReferenceById(1L)).thenReturn(consulta);
        when(consultaMapper.toDto(consulta)).thenReturn(responseDTO);

        ConsultaResponseDTO result = consultaService.update(1L, requestDTO);
        assertNotNull(result);
    }

    @Test
    void shouldDelete() {
        when(consultaRepository.existsById(1L)).thenReturn(true);
        consultaService.delete(1L);
        verify(consultaRepository).deleteById(1L);
    }
}