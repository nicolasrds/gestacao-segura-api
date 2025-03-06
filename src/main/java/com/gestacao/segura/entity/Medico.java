package com.gestacao.segura.entity;

import com.gestacao.segura.dto.MedicoRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "medicos")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 14)
    private String cpf;

    @Column(nullable = false, length = 100)
    private String nomeMedico;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, unique = true, length = 12)
    private String registro;

    @Column(nullable = false)
    private String senha;

    @OneToMany(mappedBy = "medico", fetch = FetchType.LAZY)
    private List<Consulta> consultas;

    public Medico(Long id) {
        this.id = id;
    }

    public void update(MedicoRequestDTO dto) {
        this.cpf = dto.cpf();
        this.nomeMedico = dto.nomeMedico();
        this.email = dto.email();
        this.registro = dto.registro();
        this.senha = dto.senha();
    }
}