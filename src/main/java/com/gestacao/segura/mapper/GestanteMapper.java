package com.gestacao.segura.mapper;

import com.gestacao.segura.dto.GestanteRequestDTO;
import com.gestacao.segura.dto.GestanteResponseDTO;
import com.gestacao.segura.entity.Gestante;
import org.springframework.stereotype.Component;

@Component
public class GestanteMapper {
    public static Gestante toEntity(GestanteRequestDTO dto) {
        Gestante gestante = new Gestante();
        gestante.setCpf(dto.cpf());
        gestante.setNomeGestante(dto.nomeGestante());
        gestante.setDataNascimento(dto.dataNascimento());
        gestante.setSenha(dto.senha());
        gestante.setTelefone(dto.telefone());
        gestante.setEmail(dto.email());
        gestante.setEndereco(dto.endereco());
        gestante.setTipagemSanguinea(dto.tipagemSanguinea());
        gestante.setFatorRh(dto.fatorRh());
        gestante.setNumeroCartaoSus(dto.numeroCartaoSus());
        return gestante;
    }

    public static GestanteResponseDTO toDto(Gestante gestante) {
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