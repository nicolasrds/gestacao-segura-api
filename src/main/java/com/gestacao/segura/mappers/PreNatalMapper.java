package com.gestacao.segura.mappers;

import com.gestacao.segura.dtos.PreNatalRequestDTO;
import com.gestacao.segura.dtos.PreNatalResponseDTO;
import com.gestacao.segura.entities.PreNatal;
import com.gestacao.segura.repositories.GestanteRepository;
import com.gestacao.segura.repositories.PreNatalRepository;
import org.springframework.stereotype.Component;

@Component
public class PreNatalMapper {

    private final GestanteRepository gestanteRepository;

    public PreNatalMapper(GestanteRepository gestanteRepository) {
        this.gestanteRepository = gestanteRepository;
    }

    public PreNatal toEntity(PreNatalRequestDTO dto) {
        return PreNatal.builder()
                .gestante(gestanteRepository.getReferenceById(dto.idGestante()))
                .numeroGestacao(dto.numeroGestacao())
                .dataUltimaMenstruacao(dto.dataUltimaMenstruacao())
                .dataProvavelParto(dto.dataProvavelParto())
                .pesoInicioGestacao(dto.pesoInicioGestacao())
                .alturaGestante(dto.alturaGestante())
                .build();
    }

    public PreNatalResponseDTO toDto(PreNatal preNatal) {
        return new PreNatalResponseDTO(
                preNatal.getId(),
                GestanteMapper.toDto(preNatal.getGestante()),
                preNatal.getNumeroGestacao(),
                preNatal.getDataUltimaMenstruacao(),
                preNatal.getDataProvavelParto(),
                preNatal.getPesoInicioGestacao(),
                preNatal.getAlturaGestante()
        );
    }

}
