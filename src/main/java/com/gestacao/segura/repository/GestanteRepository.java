package com.gestacao.segura.repository;

import com.gestacao.segura.entity.Gestante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GestanteRepository extends JpaRepository<Gestante, Long> {
}
