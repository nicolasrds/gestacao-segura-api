package com.gestacao.segura.dto;

import com.gestacao.segura.entity.Endereco;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record GestanteRequestDTO(
        @NotBlank
        @Size(min = 11, max = 14)
        String cpf,

        @NotBlank
        @Size(max = 100)
        String nomeGestante,

        @NotNull
        LocalDate dataNascimento,

        @NotNull
        Integer numeroCartaoSus,

        @Size(max = 15)
        String telefone,

        @NotBlank
        @Email
        @Size(max = 100)
        String email,

        @NotNull
        String senha,

        @NotNull
        Endereco endereco,

        @NotBlank
        @Size(max = 3)
        String tipagemSanguinea,

        @NotBlank
        @Size(max = 3)
        String fatorRh


) {}