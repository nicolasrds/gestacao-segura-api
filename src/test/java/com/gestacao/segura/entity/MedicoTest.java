package com.gestacao.segura.entity;

import com.gestacao.segura.dto.MedicoRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MedicoTest {
    private Medico medico;

    @BeforeEach
    void setUp() {
        medico = Medico.builder()
                .id(1L)
                .cpf("98765432109876")
                .nomeMedico("Dr. João")
                .email("joao@email.com")
                .registro("12345-SP")
                .senha("senha456")
                .consultas(List.of())
                .build();
    }

    @Test
    void testMedicoAttributes() {
        assertEquals("Dr. João", medico.getNomeMedico());
        assertEquals("12345-SP", medico.getRegistro());
    }

    @Test
    void testMedicoUpdate() {
        MedicoRequestDTO dto = new MedicoRequestDTO("65432109876543", "Dr. Pedro", "pedro@email.com", "54321-RJ", "novaSenha");
        medico.update(dto);
        assertEquals("Dr. Pedro", medico.getNomeMedico());
        assertEquals("65432109876543", medico.getCpf());
        assertEquals("pedro@email.com", medico.getEmail());
        assertEquals("54321-RJ", medico.getRegistro());
        assertEquals("novaSenha", medico.getSenha());
    }
}
