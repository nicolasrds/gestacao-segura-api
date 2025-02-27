package com.gestacao.segura.dto;

public record MedicoResponseDTO(
        Long idMedico,
        String cpf,
        String nomeMedico,
        String email,
        String registro
) {}