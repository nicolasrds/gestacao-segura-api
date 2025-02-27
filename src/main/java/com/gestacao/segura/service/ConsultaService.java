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

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;

    public ConsultaService(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    public Page<ConsultaResponseDTO> findAll(Pageable pageable){
        Page<Consulta> consultas = consultaRepository.findAll(pageable);

        return consultas.map(ConsultaMapper::toDto);
    }

    public ConsultaResponseDTO findById(Long id){
        Consulta consulta = consultaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não foi possivel encontrar o id"));

        return ConsultaMapper.toDto(consulta);
    }

    public ConsultaResponseDTO save(ConsultaRequestDTO dto){
        Consulta consulta = ConsultaMapper.toEntity(dto);
        consulta = consultaRepository.save(consulta);

        return ConsultaMapper.toDto(consulta);
    }

    public ConsultaResponseDTO update(Long id, ConsultaRequestDTO dto){
        Consulta consulta = consultaRepository.getReferenceById(id);
        consulta.update(dto);

        return ConsultaMapper.toDto(consulta);
    }

    public void delete(Long id) {
        if (!consultaRepository.existsById(id)) {
            throw new EntityNotFoundException("Não foi possivel encontrar o id");
        } else {
            consultaRepository.deleteById(id);
        }
    }
}
