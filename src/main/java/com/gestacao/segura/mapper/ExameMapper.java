package com.gestacao.segura.mapper;

import com.gestacao.segura.dto.ExameRequestDTO;
import com.gestacao.segura.dto.ExameResponseDTO;
import com.gestacao.segura.entity.Exame;
import com.gestacao.segura.repository.PreNatalRepository;
import org.springframework.stereotype.Component;

@Component
public class ExameMapper {

    private final PreNatalRepository preNatalRepository;
    private final PreNatalMapper preNatalMapper;

    public ExameMapper(PreNatalRepository preNatalRepository, PreNatalMapper preNatalMapper) {
        this.preNatalRepository = preNatalRepository;
        this.preNatalMapper = preNatalMapper;
    }

    public Exame toEntity(ExameRequestDTO dto) {
        return Exame.builder()
                .preNatal(preNatalRepository.getReferenceById(dto.idPreNatal()))
                .tipoDeExame(dto.tipoDeExame())
                .nomeExame(dto.nomeExame())
                .observacaoExame(dto.observacaoExame())
                .build();
    }

    public ExameResponseDTO toDto(Exame exame) {
        return new ExameResponseDTO(
                exame.getId(),
                preNatalMapper.toDto(exame.getPreNatal()),
                exame.getTipoDeExame(),
                exame.getNomeExame(),
                exame.getObservacaoExame()
        );
    }

}
