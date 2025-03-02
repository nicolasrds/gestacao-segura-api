package com.gestacao.segura.entities;

import com.gestacao.segura.dtos.MedicoRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "medicos")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMedico;

    @Column(nullable = false, unique = true, length = 14)
    private String cpf;

    @Column(nullable = false, length = 100)
    private String nomeMedico;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, unique = true, length = 10)
    private String registro;

    @Column(nullable = false)
    private String senha;

    public void update(MedicoRequestDTO dto) {
        this.cpf = dto.cpf();
        this.nomeMedico = dto.nomeMedico();
        this.email = dto.email();
        this.registro = dto.registro();
        this.senha = dto.senha();
    }
}