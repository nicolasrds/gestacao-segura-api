package com.gestacao.segura.dtos;

public record MedicoResponseDTO(
        Long idMedico,
        String cpf,
        String nomeMedico,
        String email,
        String registro
) {}