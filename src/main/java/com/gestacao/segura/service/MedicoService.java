package com.gestacao.segura.service;

import com.gestacao.segura.dto.MedicoRequestDTO;
import com.gestacao.segura.dto.MedicoResponseDTO;
import com.gestacao.segura.entity.Medico;
import com.gestacao.segura.mapper.MedicoMapper;
import com.gestacao.segura.repository.MedicoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;

    private final MedicoMapper medicoMapper;

    public MedicoService(MedicoRepository medicoRepository, MedicoMapper medicoMapper) {
        this.medicoRepository = medicoRepository;
        this.medicoMapper = medicoMapper;
    }

    public Page<MedicoResponseDTO> findAll(Pageable pageable){
        Page<Medico> medicos = medicoRepository.findAll(pageable);

        return medicos.map(medicoMapper::toDto);
    }

    public MedicoResponseDTO findById(Long id){
        Medico medico = medicoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não foi possivel encontrar o id"));

        return medicoMapper.toDto(medico);
    }

    public MedicoResponseDTO save(MedicoRequestDTO dto){
        Medico medico = medicoMapper.toEntity(dto);
        medico = medicoRepository.save(medico);

        return medicoMapper.toDto(medico);
    }

    public MedicoResponseDTO update(Long id, MedicoRequestDTO dto){
        Medico medico = medicoRepository.getReferenceById(id);
        medico.update(dto);

        return medicoMapper.toDto(medico);
    }

    public void delete(Long id) {
        if (!medicoRepository.existsById(id)) {
            throw new EntityNotFoundException("Não foi possivel encontrar o id");
        } else {
            medicoRepository.deleteById(id);
        }
    }
}
