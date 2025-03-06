package com.gestacao.segura.entity;

import com.gestacao.segura.dto.GestanteRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "gestantes")
@Entity
public class Gestante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 14)
    private String cpf;

    @Column(nullable = false, length = 100)
    private String nomeGestante;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Column(nullable = false, unique = true)
    private Integer numeroCartaoSus;

    @Column(length = 15)
    private String telefone;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Embedded
    private Endereco endereco;

    @Column(nullable = false)
    private String tipagemSanguinea;

    @Column(nullable = false)
    private String fatorRh;

    @OneToMany(mappedBy = "gestante", fetch = FetchType.LAZY)
    private List<PreNatal> preNatais;

    public Gestante(Long id) {
        this.id = id;
    }

    public void update(GestanteRequestDTO dto) {
        this.cpf = dto.cpf();
        this.nomeGestante = dto.nomeGestante();
        this.dataNascimento = dto.dataNascimento();
        this.numeroCartaoSus = dto.numeroCartaoSus();
        this.telefone = dto.telefone();this.senha = dto.senha();
        this.email = dto.email();
        this.senha = dto.senha();
        this.endereco = dto.endereco();
        this.tipagemSanguinea = dto.tipagemSanguinea();
        this.fatorRh = dto.fatorRh();
    }
}
