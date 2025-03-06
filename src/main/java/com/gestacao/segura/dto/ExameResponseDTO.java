package com.gestacao.segura.dto;

import com.gestacao.segura.entity.enumeration.TipoDeExameEnum;

public record ExameResponseDTO(
        Long id,
        PreNatalResponseDTO preNatal,
        TipoDeExameEnum tipoDeExame,
        String nomeExame,
        String observacaoExame
) {}
