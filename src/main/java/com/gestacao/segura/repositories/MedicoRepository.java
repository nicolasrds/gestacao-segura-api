package com.gestacao.segura.repositories;

import com.gestacao.segura.entities.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
