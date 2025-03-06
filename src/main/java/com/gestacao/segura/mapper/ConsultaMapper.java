package com.gestacao.segura.mapper;

import com.gestacao.segura.dto.ConsultaRequestDTO;
import com.gestacao.segura.dto.ConsultaResponseDTO;
import com.gestacao.segura.entity.Consulta;
import com.gestacao.segura.repository.MedicoRepository;
import com.gestacao.segura.repository.PreNatalRepository;
import org.springframework.stereotype.Component;

@Component
public class ConsultaMapper {

    private final PreNatalRepository preNatalRepository;

    private final MedicoRepository medicoRepository;

    private final PreNatalMapper preNatalMapper;

    private final MedicoMapper medicoMapper;

    public ConsultaMapper(PreNatalRepository preNatalRepository, MedicoRepository medicoRepository, PreNatalMapper preNatalMapper, MedicoMapper medicoMapper) {
        this.preNatalRepository = preNatalRepository;
        this.medicoRepository = medicoRepository;
        this.preNatalMapper = preNatalMapper;
        this.medicoMapper = medicoMapper;
    }

    public Consulta toEntity(ConsultaRequestDTO dto) {
        return Consulta.builder()
                .preNatal(preNatalRepository.getReferenceById(dto.idPreNatal()))
                .dataConsulta(dto.dataConsulta())
                .semanasDeAmenorreiaInt(dto.semanasDeAmenorreiaInt())
                .semanasDeAmenorreiaFrc(dto.semanasDeAmenorreiaFrc())
                .pesoGestante(dto.pesoGestante())
                .pressaoArterial(dto.pressaoArterial())
                .alturaUterina(dto.alturaUterina())
                .fcf(dto.fcf())
                .movimentosFetais(dto.movimentosFetais())
                .observacoes(dto.observacoes())
                .medico(medicoRepository.getReferenceById(dto.idMedico()))
                .build();
    }

    public ConsultaResponseDTO toDto(Consulta consulta) {
        return new ConsultaResponseDTO(
                consulta.getIdConsulta(),
                preNatalMapper.toDto(consulta.getPreNatal()),
                consulta.getDataConsulta(),
                consulta.getSemanasDeAmenorreiaInt(),
                consulta.getSemanasDeAmenorreiaFrc(),
                consulta.getPesoGestante(),
                consulta.getPressaoArterial(),
                consulta.getAlturaUterina(),
                consulta.getFcf(),
                consulta.getMovimentosFetais(),
                consulta.getObservacoes(),
                medicoMapper.toDto(consulta.getMedico()),
                consulta.getImc()
        );
    }

}