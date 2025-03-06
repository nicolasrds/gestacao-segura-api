package com.gestacao.segura.entity;


import com.gestacao.segura.dto.ConsultaRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "consultas")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConsulta;

    @ManyToOne
    @JoinColumn(name = "pre_natal_id", nullable = false)
    private PreNatal preNatal;

    @Column(nullable = false)
    private LocalDateTime dataConsulta;

    @Column(nullable = false)
    private Integer semanasDeAmenorreiaInt;

    @Column(nullable = false)
    private Integer semanasDeAmenorreiaFrc;

    @Column(nullable = false)
    private double pesoGestante;

    @Column(nullable = false, length = 7)
    private String pressaoArterial;

    @Column(nullable = false)
    private Integer alturaUterina;

    @Column(nullable = false)
    private String fcf;

    @Column(nullable = false)
    private String movimentosFetais;

    @Column(length = 500)
    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "medico_id", nullable = false)
    private Medico medico;

    @Column(nullable = false)
    private Double imc;

    public void update(ConsultaRequestDTO dto) {
        this.dataConsulta = dto.dataConsulta();
        this.semanasDeAmenorreiaInt = dto.semanasDeAmenorreiaInt();
        this.semanasDeAmenorreiaFrc = dto.semanasDeAmenorreiaFrc();
        this.pesoGestante = dto.pesoGestante();
        this.pressaoArterial = dto.pressaoArterial();
        this.alturaUterina = dto.alturaUterina();
        this.fcf = dto.fcf();
        this.movimentosFetais = dto.movimentosFetais();
        this.observacoes = dto.observacoes();
    }
}