package com.gestacao.segura.service;

import com.gestacao.segura.dto.ConsultaRequestDTO;
import com.gestacao.segura.dto.ConsultaResponseDTO;
import com.gestacao.segura.entity.Consulta;
import com.gestacao.segura.mapper.ConsultaMapper;
import com.gestacao.segura.repository.ConsultaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;

    private final ConsultaMapper consultaMapper;

    public ConsultaService(ConsultaRepository consultaRepository, ConsultaMapper consultaMapper) {
        this.consultaRepository = consultaRepository;
        this.consultaMapper = consultaMapper;
    }

    public Page<ConsultaResponseDTO> findAll(Pageable pageable){
        Page<Consulta> consultas = consultaRepository.findAll(pageable);

        return consultas.map(consultaMapper::toDto);
    }

    public ConsultaResponseDTO findById(Long id){
        Consulta consulta = consultaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não foi possivel encontrar o id"));

        return consultaMapper.toDto(consulta);
    }

    public ConsultaResponseDTO save(ConsultaRequestDTO dto){
        Consulta consulta = consultaMapper.toEntity(dto);

        consulta.setImc(calcularImc(consulta.getPesoGestante(), consulta.getPreNatal().getAlturaGestante()));

        consulta = consultaRepository.save(consulta);

        return consultaMapper.toDto(consulta);
    }

    public ConsultaResponseDTO update(Long id, ConsultaRequestDTO dto){
        Consulta consulta = consultaRepository.getReferenceById(id);
        consulta.update(dto);

        return consultaMapper.toDto(consulta);
    }

    public void delete(Long id) {
        if (!consultaRepository.existsById(id)) {
            throw new EntityNotFoundException("Não foi possivel encontrar o id");
        } else {
            consultaRepository.deleteById(id);
        }
    }

    private Double calcularImc(double peso, double altura) {
        return peso / (altura*altura);
    }
}
