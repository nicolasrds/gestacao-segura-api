package com.gestacao.segura.mapper;

import com.gestacao.segura.dto.GestanteRequestDTO;
import com.gestacao.segura.dto.GestanteResponseDTO;
import com.gestacao.segura.entity.Gestante;
import org.springframework.stereotype.Component;

@Component
public class GestanteMapper {

    public Gestante toEntity(GestanteRequestDTO dto) {
        return Gestante.builder()
        .cpf(dto.cpf())
        .nomeGestante(dto.nomeGestante())
        .dataNascimento(dto.dataNascimento())
        .senha(dto.senha())
        .telefone(dto.telefone())
        .email(dto.email())
        .endereco(dto.endereco())
        .tipagemSanguinea(dto.tipagemSanguinea())
        .fatorRh(dto.fatorRh())
        .numeroCartaoSus(dto.numeroCartaoSus())
        .build();
    }

    public GestanteResponseDTO toDto(Gestante gestante) {
        return new GestanteResponseDTO(
                gestante.getId(),
                gestante.getCpf(),
                gestante.getNomeGestante(),
                gestante.getDataNascimento(),
                gestante.getNumeroCartaoSus(),
                gestante.getTelefone(),
                gestante.getEmail(),
                gestante.getEndereco(),
                gestante.getTipagemSanguinea(),
                gestante.getFatorRh()
        );
    }
}