package com.gestacao.segura.dto;

import com.gestacao.segura.entity.Medico;

import java.time.LocalDateTime;

public record ConsultaResponseDTO(
        Long idConsulta,
        Long idAgendamento,
        Integer numeroConsulta,
        LocalDateTime dataConsulta,
        Integer semanasDeAmenorreiaInt,
        Integer semanasDeAmenorreiaFrc,
        double pesoGestante,
        String pressaoArterial,
        Integer alturaUterina,
        String fcf,
        String movimentosFetais,
        String observacoes,
        Medico medico,
        Double imc
) {}