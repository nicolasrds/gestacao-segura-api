package com.gestacao.segura.repository;

import com.gestacao.segura.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
