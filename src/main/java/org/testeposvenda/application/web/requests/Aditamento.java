package org.testeposvenda.application.web.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
public class Aditamento {

    @JsonProperty("nova_quantidade_parcelas")
    private int novaQuantidadeParcelas;

    @JsonProperty("nova_data_pagamento")
    private int novaDataPagamento;
}
