package com.gestacao.segura.dto;

import com.gestacao.segura.entity.Medico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ConsultaRequestDTO(
        @NotNull Long idAgendamento,
        @NotNull Integer numeroConsulta,
        @NotNull LocalDateTime dataConsulta,
        @NotNull Integer semanasDeAmenorreiaInt,
        @NotNull Integer semanasDeAmenorreiaFrc,
        @NotNull double pesoGestante,
        @NotBlank String pressaoArterial,
        @NotNull Integer alturaUterina,
        @NotBlank String fcf,
        @NotBlank String movimentosFetais,
        String observacoes,
        @NotNull Medico medicoId
) {}