package com.gestacao.segura.service;

import com.gestacao.segura.dto.ExameRequestDTO;
import com.gestacao.segura.dto.ExameResponseDTO;
import com.gestacao.segura.entity.Exame;
import com.gestacao.segura.entity.PreNatal;
import com.gestacao.segura.mapper.ExameMapper;
import com.gestacao.segura.repository.ExameRepository;
import com.gestacao.segura.repository.PreNatalRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ExameService {

    private final ExameRepository exameRepository;
    private final PreNatalRepository preNatalRepository;
    private final ExameMapper exameMapper;

    public ExameService(ExameRepository exameRepository,
                        PreNatalRepository preNatalRepository,
                        ExameMapper exameMapper) {
        this.exameRepository = exameRepository;
        this.preNatalRepository = preNatalRepository;
        this.exameMapper = exameMapper;
    }

    public Page<ExameResponseDTO> findAll(Pageable pageable) {
        Page<Exame> examePage = exameRepository.findAll(pageable);

        return examePage.map(exameMapper::toDto);
    }

    public ExameResponseDTO findById(Long id) {
        Exame exame = exameRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não foi possivel encontrar o id"));

        return exameMapper.toDto(exame);
    }

    public ExameResponseDTO save(ExameRequestDTO dto) {
        Exame exame = exameMapper.toEntity(dto);
        exame = exameRepository.save(exame);

        return exameMapper.toDto(exame);
    }

    public ExameResponseDTO update(Long id, ExameRequestDTO dto) {
        Exame exame = exameRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não foi possivel encontrar o id"));
        PreNatal preNatal = preNatalRepository.findById(dto.idPreNatal()).orElseThrow(() -> new EntityNotFoundException("Não foi possivel encontrar o id"));
        exame.update(dto, preNatal);

        return exameMapper.toDto(exame);
    }

    public void delete(Long id) {
        if (!exameRepository.existsById(id)) {
            throw new EntityNotFoundException("Não foi possivel encontrar o id");
        } else {
            exameRepository.deleteById(id);
        }
    }

}
