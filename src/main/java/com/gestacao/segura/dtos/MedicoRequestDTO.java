package com.gestacao.segura.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record MedicoRequestDTO(
        @NotBlank
        @Size(min = 11, max = 14)
        String cpf,

        @NotBlank
        @Size(max = 100)
        String nomeMedico,

        @NotBlank
        @Email
        @Size(max = 100) String email,

        @NotBlank
        @Pattern(regexp = "\\d{5}-[A-Z]{2}")
        String registro,

        @NotBlank
        String senha
) {



}
