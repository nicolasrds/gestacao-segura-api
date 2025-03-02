package com.gestacao.segura.repositories;

import com.gestacao.segura.entities.Gestante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GestanteRepository extends JpaRepository<Gestante, Long> {
}
