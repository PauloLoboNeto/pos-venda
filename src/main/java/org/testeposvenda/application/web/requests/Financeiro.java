package org.testeposvenda.application.web.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class Financeiro {
    @NotNull(message = "Campo data_calculo não informado")
    @JsonProperty("data_calculo")
    private String dataCalculo;
    @NotNull(message = "Campo tipo_calculo não informado")
    @JsonProperty("tipo_calculo")
    private String tipoCalculo;
    @NotNull(message = "Campo valor_total não informado")
    @JsonProperty("valor_total")
    private BigDecimal valorTotal;
    @NotNull(message = "Campo quantidade_parcelas não informado")
    @JsonProperty("quantidade_parcelas")
    private int quantidadeParcelas;
    @NotNull(message = "Campo valor_parcelas não informado")
    @JsonProperty("valor_parcelas")
    private BigDecimal valorParcelas;
    @NotNull(message = "Campo dia_pagamento não informado")
    @JsonProperty("dia_pagamento")
    private int diaPagamento;
    @NotNull(message = "Campo percentual_taxa_juro não informado")
    @JsonProperty("percentual_taxa_juro")
    private BigDecimal percentualTaxaJuro;
}
