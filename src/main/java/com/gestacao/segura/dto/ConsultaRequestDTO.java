package com.gestacao.segura.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ConsultaRequestDTO(
        @NotNull(message = "O Pre-Natal é obrigatório")
        Long idPreNatal,

        @NotNull(message = "O Data da Consulta é obrigatória")
        LocalDateTime dataConsulta,

        @NotNull(message = "As Semanas de Amenorreia são obrigatórias")
        Integer semanasDeAmenorreiaInt,

        @NotNull(message = "As Semanas de Amenorreia são obrigatórias")
        Integer semanasDeAmenorreiaFrc,

        @NotNull(message = "O Peso da Gestante é obrigatório")
        double pesoGestante,

        @NotBlank(message = "A Pressao Arterial é obrigatória")
        String pressaoArterial,

        @NotNull(message = "A Altura Uterina é obrigatória")
        Integer alturaUterina,

        @NotBlank(message = "a FCF é obrigatória")
        String fcf,

        @NotBlank(message = "Os movimentos fetais são obrigatória")
        String movimentosFetais,

        String observacoes,

        @NotNull(message = "o Medico é obrigatório")
        Long idMedico
) {}