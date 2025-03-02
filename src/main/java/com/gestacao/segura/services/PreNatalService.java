package com.gestacao.segura.services;

import com.gestacao.segura.dtos.PreNatalRequestDTO;
import com.gestacao.segura.dtos.PreNatalResponseDTO;
import com.gestacao.segura.entities.PreNatal;
import com.gestacao.segura.mappers.PreNatalMapper;
import com.gestacao.segura.repositories.PreNatalRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PreNatalService {

    private final PreNatalRepository preNatalRepository;
    private final PreNatalMapper preNatalMapper;

    public PreNatalService(PreNatalRepository preNatalRepository,
                           PreNatalMapper preNatalMapper) {
        this.preNatalRepository = preNatalRepository;
        this.preNatalMapper = preNatalMapper;
    }

    public Page<PreNatalResponseDTO> findAll(Pageable pageable) {
        Page<PreNatal> preNatalPage = preNatalRepository.findAll(pageable);

        return preNatalPage.map(preNatalMapper::toDto);
    }

    public PreNatalResponseDTO findById(Long id) {
        PreNatal preNatal = preNatalRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não foi possivel encontrar o id"));

        return preNatalMapper.toDto(preNatal);
    }

    public PreNatalResponseDTO save(PreNatalRequestDTO dto) {
        PreNatal preNatal = preNatalMapper.toEntity(dto);
        preNatal = preNatalRepository.save(preNatal);

        return preNatalMapper.toDto(preNatal);
    }

    public PreNatalResponseDTO update(Long id, PreNatalRequestDTO dto) {
        PreNatal preNatal = preNatalRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não foi possivel encontrar o id"));
        preNatal.update(dto);

        return preNatalMapper.toDto(preNatal);
    }

    public void delete(Long id) {
        if (!preNatalRepository.existsById(id)) {
            throw new EntityNotFoundException("Não foi possivel encontrar o id");
        } else {
            preNatalRepository.deleteById(id);
        }
    }

}
