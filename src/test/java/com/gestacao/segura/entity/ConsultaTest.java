package com.gestacao.segura.entity;

import com.gestacao.segura.dto.ConsultaRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsultaTest {
    private Consulta consulta;
    private Medico medico;
    private PreNatal preNatal;

    @BeforeEach
    void setUp() {
        medico = new Medico(1L);
        preNatal = new PreNatal(1L);
        consulta = Consulta.builder()
                .idConsulta(1L)
                .preNatal(preNatal)
                .dataConsulta(LocalDateTime.now())
                .semanasDeAmenorreiaInt(10)
                .semanasDeAmenorreiaFrc(5)
                .pesoGestante(65.0)
                .pressaoArterial("120/80")
                .alturaUterina(30)
                .fcf("140bpm")
                .movimentosFetais("OK")
                .observacoes("Nada relevante")
                .medico(medico)
                .imc(24.0)
                .build();
    }

    @Test
    void testConsultaUpdate() {
        ConsultaRequestDTO dto = new ConsultaRequestDTO(1L, LocalDateTime.now(), 12, 6, 68.0, "130/85", 32, "145bpm", "S", "Sem observações", 1L);
        consulta.update(dto);
        assertEquals(12, consulta.getSemanasDeAmenorreiaInt());
        assertEquals(6, consulta.getSemanasDeAmenorreiaFrc());
        assertEquals(68.0, consulta.getPesoGestante());
        assertEquals("130/85", consulta.getPressaoArterial());
        assertEquals(32, consulta.getAlturaUterina());
        assertEquals("145bpm", consulta.getFcf());
        assertEquals("S", consulta.getMovimentosFetais());
        assertEquals("Sem observações", consulta.getObservacoes());
    }
}