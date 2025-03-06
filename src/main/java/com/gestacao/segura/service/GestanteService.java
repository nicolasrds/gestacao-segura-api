package com.gestacao.segura.service;

import com.gestacao.segura.dto.GestanteRequestDTO;
import com.gestacao.segura.dto.GestanteResponseDTO;
import com.gestacao.segura.entity.Gestante;
import com.gestacao.segura.mapper.GestanteMapper;
import com.gestacao.segura.repository.GestanteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GestanteService {

    private final GestanteRepository gestanteRepository;

    private final GestanteMapper gestanteMapper;

    public GestanteService(GestanteRepository gestanteRepository, GestanteMapper gestanteMapper) {
        this.gestanteRepository = gestanteRepository;
        this.gestanteMapper = gestanteMapper;
    }

    public Page<GestanteResponseDTO> findAll(Pageable pageable){
        Page<Gestante> gestantes = gestanteRepository.findAll(pageable);

        return gestantes.map(gestanteMapper::toDto);
    }

    public GestanteResponseDTO findById(Long id){
        Gestante gestante = gestanteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não foi possivel encontrar o id"));

        return gestanteMapper.toDto(gestante);
    }

    public GestanteResponseDTO save(GestanteRequestDTO dto){
        Gestante gestante = gestanteMapper.toEntity(dto);
        gestante = gestanteRepository.save(gestante);

        return gestanteMapper.toDto(gestante);
    }

    public GestanteResponseDTO update(Long id, GestanteRequestDTO dto){
        Gestante gestante = gestanteRepository.getReferenceById(id);
        gestante.update(dto);

        return gestanteMapper.toDto(gestante);
    }

    public void delete(Long id) {
        if (!gestanteRepository.existsById(id)) {
            throw new EntityNotFoundException("Não foi possivel encontrar o id");
        } else {
            gestanteRepository.deleteById(id);
        }
    }
}
