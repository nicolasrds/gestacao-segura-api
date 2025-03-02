package com.gestacao.segura.services;

import com.gestacao.segura.dtos.GestanteRequestDTO;
import com.gestacao.segura.dtos.GestanteResponseDTO;
import com.gestacao.segura.entities.Gestante;
import com.gestacao.segura.mappers.GestanteMapper;
import com.gestacao.segura.repositories.GestanteRepository;
import com.gestacao.segura.repositories.GestanteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GestanteService {

    private final GestanteRepository gestanteRepository;

    public GestanteService(GestanteRepository gestanteRepository) {
        this.gestanteRepository = gestanteRepository;
    }

    public Page<GestanteResponseDTO> findAll(Pageable pageable){
        Page<Gestante> gestantes = gestanteRepository.findAll(pageable);

        return gestantes.map(GestanteMapper::toDto);
    }

    public GestanteResponseDTO findById(Long id){
        Gestante gestante = gestanteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não foi possivel encontrar o id"));

        return GestanteMapper.toDto(gestante);
    }

    public GestanteResponseDTO save(GestanteRequestDTO dto){
        Gestante gestante = GestanteMapper.toEntity(dto);
        gestante = gestanteRepository.save(gestante);

        return GestanteMapper.toDto(gestante);
    }

    public GestanteResponseDTO update(Long id, GestanteRequestDTO dto){
        Gestante gestante = gestanteRepository.getReferenceById(id);
        gestante.update(dto);

        return GestanteMapper.toDto(gestante);
    }

    public void delete(Long id) {
        if (!gestanteRepository.existsById(id)) {
            throw new EntityNotFoundException("Não foi possivel encontrar o id");
        } else {
            gestanteRepository.deleteById(id);
        }
    }
}
