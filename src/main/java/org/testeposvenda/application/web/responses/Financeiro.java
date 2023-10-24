package org.testeposvenda.application.web.responses;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Financeiro {

    private String dataCalculo;

    private String tipoCalculo;

    private BigDecimal valorTotal;

    private int quantidadeParcelas;

    private BigDecimal valorParcelas;

    private int diaPagamento;

    private BigDecimal percentualTaxaJuro;
}
