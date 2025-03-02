package com.gestacao.segura.dtos;

import com.gestacao.segura.entities.Endereco;

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
