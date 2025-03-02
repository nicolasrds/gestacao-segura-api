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

    public void update(PreNatalRequestDTO dto) {
        this.gestante = new Gestante(dto.idGestante());
        this.numeroGestacao = dto.numeroGestacao();
        this.dataUltimaMenstruacao = dto.dataUltimaMenstruacao();
        this.dataProvavelParto = dto.dataProvavelParto();
        this.pesoInicioGestacao = dto.pesoInicioGestacao();
        this.alturaGestante = dto.alturaGestante();
    }

}
