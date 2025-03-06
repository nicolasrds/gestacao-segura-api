package com.gestacao.segura.mapper;

import com.gestacao.segura.dto.MedicoRequestDTO;
import com.gestacao.segura.dto.MedicoResponseDTO;
import com.gestacao.segura.entity.Medico;
import org.springframework.stereotype.Component;

@Component
public class MedicoMapper {

    public Medico toEntity(MedicoRequestDTO dto) {
        return Medico.builder()
                .cpf(dto.cpf())
                .nomeMedico(dto.nomeMedico())
                .email(dto.email())
                .registro(dto.registro())
                .senha(dto.senha())
                .build();
    }

    public MedicoResponseDTO toDto(Medico medico) {
        return new MedicoResponseDTO(
                medico.getId(),
                medico.getCpf(),
                medico.getNomeMedico(),
                medico.getEmail(),
                medico.getRegistro()
        );
    }
}