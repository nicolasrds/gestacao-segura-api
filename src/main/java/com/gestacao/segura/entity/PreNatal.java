package com.gestacao.segura.entity;

import com.gestacao.segura.dto.PreNatalRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "prenatal")
@Entity
@Builder
public class PreNatal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "gestante_id", nullable = false)
    private Gestante gestante;
    @Column(nullable = false)
    private Integer numeroGestacao;
    @Column()
    private LocalDate dataUltimaMenstruacao;
    @Column()
    private LocalDate dataProvavelParto;
    @Column()
    private Double pesoInicioGestacao;
    @Column()
    private Double alturaGestante;

    public PreNatal(Long id) {
        this.id = id;
    }

    public void update(PreNatalRequestDTO dto, Gestante gestante) {
        this.gestante = gestante;
        this.numeroGestacao = dto.numeroGestacao();
        this.dataUltimaMenstruacao = dto.dataUltimaMenstruacao();
        this.dataProvavelParto = dto.dataProvavelParto();
        this.pesoInicioGestacao = dto.pesoInicioGestacao();
        this.alturaGestante = dto.alturaGestante();
    }

}
