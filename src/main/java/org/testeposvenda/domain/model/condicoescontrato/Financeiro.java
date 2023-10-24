package org.testeposvenda.domain.model.condicoescontrato;

import lombok.*;
import org.testeposvenda.domain.enums.TipoCalculo;

import java.math.BigDecimal;
import java.util.Date;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public final class Financeiro {
    private String dataCalculo;
    private TipoCalculo tipoCalculo;
    private BigDecimal valorTotal;
    private int quantidadeParcelas;
    private BigDecimal valorParcelas;
    private int diaPagamento;
    private BigDecimal percentualTaxaJuro;
}
