package com.gestacao.segura.dto;

import com.gestacao.segura.entity.Endereco;

import java.time.LocalDate;

public record GestanteResponseDTO(

        Long id,
        String cpf,
        String nomeGestante,
        LocalDate dataNascimento,
        Integer numeroCartaoSus,
        String telefone,
        String email,
        Endereco endereco,
        String tipagemSanguinea,
        String fatorRh
) {}
