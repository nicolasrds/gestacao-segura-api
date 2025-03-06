package com.gestacao.segura.entity;

import com.gestacao.segura.dto.ExameRequestDTO;
import com.gestacao.segura.entity.enumeration.TipoDeExameEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "exame")
@Entity
@Builder
public class Exame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "prenatal_id", nullable = false)
    private PreNatal preNatal;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoDeExameEnum tipoDeExame;
    @Column(nullable = false, length = 200)
    private String nomeExame;
    @Column()
    private String observacaoExame;

    public void update(ExameRequestDTO dto, PreNatal preNatal) {
        this.preNatal = preNatal;
        this.tipoDeExame = dto.tipoDeExame();
        this.nomeExame = dto.nomeExame();
        this.observacaoExame = dto.observacaoExame();
    }

}
