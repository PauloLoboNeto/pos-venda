package org.testeposvenda.domain.model.calculojuros;

import lombok.*;
import org.testeposvenda.domain.enums.CriterioCalculo;

import java.math.BigDecimal;
import java.util.Date;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public final class Contrato {
    private String definirDataContratacao;
    private CriterioCalculo definirCriterioCalculo;
    private int definirQuantidadeParcelas;
    private BigDecimal definirValorContratacao;
}
