package com.gestacao.segura.mapper;

import com.gestacao.segura.dto.MedicoRequestDTO;
import com.gestacao.segura.dto.MedicoResponseDTO;
import com.gestacao.segura.entity.Medico;
import org.springframework.stereotype.Component;

@Component
public class MedicoMapper {

    public static Medico toEntity(MedicoRequestDTO dto) {
        Medico medico = new Medico();
        medico.setCpf(dto.cpf());
        medico.setNomeMedico(dto.nomeMedico());
        medico.setEmail(dto.email());
        medico.setRegistro(dto.registro());
        medico.setSenha(dto.senha());
        return medico;
    }

    public static MedicoResponseDTO toDto(Medico medico) {
        return new MedicoResponseDTO(
                medico.getIdMedico(),
                medico.getCpf(),
                medico.getNomeMedico(),
                medico.getEmail(),
                medico.getRegistro()
        );
    }
}