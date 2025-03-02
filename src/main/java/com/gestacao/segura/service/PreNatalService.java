package com.gestacao.segura.service;

import com.gestacao.segura.dto.PreNatalRequestDTO;
import com.gestacao.segura.dto.PreNatalResponseDTO;
import com.gestacao.segura.entity.PreNatal;
import com.gestacao.segura.mapper.PreNatalMapper;
import com.gestacao.segura.repository.PreNatalRepository;
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
