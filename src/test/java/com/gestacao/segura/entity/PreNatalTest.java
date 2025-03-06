package com.gestacao.segura.entity;

import com.gestacao.segura.dto.PreNatalRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PreNatalTest {
    private PreNatal preNatal;
    private Gestante gestante;

    @BeforeEach
    void setUp() {
        gestante = new Gestante(1L);
        preNatal = PreNatal.builder()
                .id(1L)
                .gestante(gestante)
                .numeroGestacao(2)
                .dataUltimaMenstruacao(LocalDate.of(2023, 1, 10))
                .dataProvavelParto(LocalDate.of(2023, 10, 10))
                .pesoInicioGestacao(60.0)
                .alturaGestante(1.65)
                .consultas(List.of())
                .build();
    }

    @Test
    void testPreNatalUpdate() {
        PreNatalRequestDTO dto = new PreNatalRequestDTO(1L, 3, LocalDate.of(2023, 2, 15), LocalDate.of(2023, 11, 15), 62.0, 1.66);
        preNatal.update(dto);
        assertEquals(3, preNatal.getNumeroGestacao());
        assertEquals(LocalDate.of(2023, 2, 15), preNatal.getDataUltimaMenstruacao());
        assertEquals(LocalDate.of(2023, 11, 15), preNatal.getDataProvavelParto());
        assertEquals(62.0, preNatal.getPesoInicioGestacao());
        assertEquals(1.66, preNatal.getAlturaGestante());
    }
}