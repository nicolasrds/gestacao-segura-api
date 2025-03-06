package com.gestacao.segura.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record MedicoRequestDTO(
        @NotBlank(message = "O CPF é obrigatório")
        @Size(min = 11, max = 14)
        String cpf,

        @NotBlank(message = "O Nome do Medico é obrigatório")
        @Size(max = 100, message = "O Nome do Medico não pode ser maior que 100 caracteres" )
        String nomeMedico,

        @NotBlank(message = "O Email é obrigatório")
        @Email(message = "O Email deve ser valido")
        @Size(max = 100) String email,

        @NotBlank(message = "O registro é obrigatório")
        @Pattern(regexp = "/^\\d{6}-\\d{2}\\/[A-Z]{2}$/", message = "O Registro deve estar no padrao: XXXXXX-XX/UF")
        String registro,

        @NotBlank(message = "A Senha é obrigatória")
        String senha
) {



}
