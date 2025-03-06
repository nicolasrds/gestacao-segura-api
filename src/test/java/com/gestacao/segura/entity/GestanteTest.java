package com.gestacao.segura.entity;

import com.gestacao.segura.dto.GestanteRequestDTO;
import com.gestacao.segura.dto.MedicoRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GestanteTest {
    private Gestante gestante;

    @BeforeEach
    void setUp() {
        gestante = Gestante.builder()
                .id(1L)
                .cpf("12345678901234")
                .nomeGestante("Maria Silva")
                .dataNascimento(LocalDate.of(1990, 5, 20))
                .numeroCartaoSus(123456789)
                .telefone("11999999999")
                .email("maria@email.com")
                .senha("senha123")
                .tipagemSanguinea("O+")
                .fatorRh("+")
                .preNatais(List.of())
                .build();
    }

    @Test
    void testGestanteAttributes() {
        assertEquals("Maria Silva", gestante.getNomeGestante());
        assertEquals("12345678901234", gestante.getCpf());
    }

    @Test
    void testGestanteUpdate() {
        GestanteRequestDTO dto = new GestanteRequestDTO("98765432109876", "Ana Souza", LocalDate.of(1992, 8, 15),
                987654321, "11988888888", "ana@email.com", "senha321", new Endereco(), "A-", "-");
        gestante.update(dto);
        assertEquals("Ana Souza", gestante.getNomeGestante());
        assertEquals("98765432109876", gestante.getCpf());
        assertEquals(LocalDate.of(1992, 8, 15), gestante.getDataNascimento());
        assertEquals(987654321, gestante.getNumeroCartaoSus());
        assertEquals("11988888888", gestante.getTelefone());
        assertEquals("ana@email.com", gestante.getEmail());
        assertEquals("senha321", gestante.getSenha());
        assertEquals("A-", gestante.getTipagemSanguinea());
        assertEquals("-", gestante.getFatorRh());
    }
}

