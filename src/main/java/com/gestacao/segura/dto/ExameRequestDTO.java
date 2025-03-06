package com.gestacao.segura.dto;

import com.gestacao.segura.entity.enumeration.TipoDeExameEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ExameRequestDTO(
        @NotNull(message = "O ID do pre-natal é obrigatório")
        Long idPreNatal,
        @NotNull(message = "O tipo do exame é obrigatório")
        TipoDeExameEnum tipoDeExame,
        @NotNull(message = "O nome do exame é obrigatório")
        @Size(max = 200, message = "O nome do exame deve ter no máximo 200 caracteres")
        String nomeExame,
        @Size(max = 500, message = "A observação deve ter no máximo 500 caracteres")
        String observacaoExame
) {}
