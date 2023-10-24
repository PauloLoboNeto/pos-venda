package org.testeposvenda.application.web.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class Contrato {
    @NotNull(message = "Campo id_contrato não informado")
    @JsonProperty("id_contrato")
    private Long idContrato;
    @NotNull(message = "Campo numero_cpf_cnpj_cliente não informado")
    @JsonProperty("numero_cpf_cnpj_cliente")
    private String numeroCpfCnpjCliente;
    @NotNull(message = "Campo data_contratacao não informado")
    @JsonProperty("data_contratacao")
    private String dataContratacao;
    @NotNull(message = "Campo ativo não informado")
    private boolean ativo;
    @NotNull(message = "Campo parcelas_em_atraso não informado")
    @JsonProperty("parcelas_em_atraso")
    private boolean parcelasEmAtraso;
}
