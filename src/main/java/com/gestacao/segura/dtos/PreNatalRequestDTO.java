package com.gestacao.segura.dtos;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record PreNatalRequestDTO(
        @NotNull(message = "O ID da gestante é obrigatório")
        Long idGestante,
        @NotNull(message = "O número da gestação é obrigatório")
        @Min(value = 1, message = "O número da gestação deve ser pelo menos 1")
        Integer numeroGestacao,
        @PastOrPresent(message = "A data da última menstruação não pode ser no futuro")
        LocalDate dataUltimaMenstruacao,
        @Future(message = "A data provável do parto deve ser uma data futura")
        LocalDate dataProvavelParto,
        @DecimalMin(value = "30.0", message = "O peso deve ser no mínimo 30kg")
        @DecimalMax(value = "200.0", message = "O peso não pode ser maior que 200kg")
        Double pesoInicioGestacao,
        @DecimalMin(value = "0.5", message = "A altura deve ser no mínimo 0,5m")
        @DecimalMax(value = "2.5", message = "A altura não pode ser maior que 2,5m")
        Double alturaGestante
) {}
