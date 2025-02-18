package com.gestacao.segura.services;

import com.gestacao.segura.dtos.MedicoRequestDTO;
import com.gestacao.segura.dtos.MedicoResponseDTO;
import com.gestacao.segura.entities.Medico;
import com.gestacao.segura.mappers.MedicoMapper;
import com.gestacao.segura.repositories.MedicoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;

    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    public Page<MedicoResponseDTO> findAll(Pageable pageable){
        Page<Medico> medicos = medicoRepository.findAll(pageable);

        return medicos.map(MedicoMapper::toDto);
    }

    public MedicoResponseDTO findById(Long id){
        Medico medico = medicoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não foi possivel encontrar o id"));

        return MedicoMapper.toDto(medico);
    }

    public MedicoResponseDTO save(MedicoRequestDTO dto){
        Medico medico = MedicoMapper.toEntity(dto);
        medico = medicoRepository.save(medico);

        return MedicoMapper.toDto(medico);
    }

    public MedicoResponseDTO update(Long id, MedicoRequestDTO dto){
        Medico medico = medicoRepository.getReferenceById(id);
        medico.update(dto);

        return MedicoMapper.toDto(medico);
    }

    public void delete(Long id) {
        if (!medicoRepository.existsById(id)) {
            throw new EntityNotFoundException("Não foi possivel encontrar o id");
        } else {
            medicoRepository.deleteById(id);
        }
    }
}
