package com.gestacao.segura.mapper;

import com.gestacao.segura.dto.ConsultaRequestDTO;
import com.gestacao.segura.dto.ConsultaResponseDTO;
import com.gestacao.segura.entity.Consulta;
import com.gestacao.segura.entity.Medico;
import org.springframework.stereotype.Component;

@Component
public class ConsultaMapper {
    public static Consulta toEntity(ConsultaRequestDTO dto) {
        Consulta consulta = new Consulta();
        consulta.setIdAgendamento(dto.idAgendamento());
        consulta.setNumeroConsulta(dto.numeroConsulta());
        consulta.setDataConsulta(dto.dataConsulta());
        consulta.setSemanasDeAmenorreiaInt(dto.semanasDeAmenorreiaInt());
        consulta.setSemanasDeAmenorreiaFrc(dto.semanasDeAmenorreiaFrc());
        consulta.setPesoGestante(dto.pesoGestante());
        consulta.setPressaoArterial(dto.pressaoArterial());
        consulta.setAlturaUterina(dto.alturaUterina());
        consulta.setFcf(dto.fcf());
        consulta.setMovimentosFetais(dto.movimentosFetais());
        consulta.setObservacoes(dto.observacoes());
        consulta.setMedico(dto.medicoId());
        return consulta;
    }

    public static ConsultaResponseDTO toDto(Consulta consulta) {
        return new ConsultaResponseDTO(
                consulta.getIdConsulta(),
                consulta.getIdAgendamento(),
                consulta.getNumeroConsulta(),
                consulta.getDataConsulta(),
                consulta.getSemanasDeAmenorreiaInt(),
                consulta.getSemanasDeAmenorreiaFrc(),
                consulta.getPesoGestante(),
                consulta.getPressaoArterial(),
                consulta.getAlturaUterina(),
                consulta.getFcf(),
                consulta.getMovimentosFetais(),
                consulta.getObservacoes(),
                consulta.getMedico(),
                consulta.getImc()
        );
    }
}