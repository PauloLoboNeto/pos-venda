package org.testeposvenda.application.web.responses;

import lombok.*;
import org.testeposvenda.domain.model.condicoescontrato.CondicoesContrato;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CondicoesContratoResponse {

    private Contrato contrato;
    private List<Financeiro> financeiro;


    public CondicoesContratoResponse toCondicoesContratoResponse(CondicoesContrato condicoesContrato) {
        var idContratoString = condicoesContrato.getContrato().getIdContrato().toString();
        var ultimoDigitoContrato = Character.getNumericValue(idContratoString.charAt(idContratoString.length() - 1));
        var contratoResponse = Contrato.builder()
                .idContrato(condicoesContrato.getContrato().getIdContrato())
                .ultimoDigitoContrato(ultimoDigitoContrato)
                .numeroCpfCnpjCliente(condicoesContrato.getContrato().getNumeroCpfCnpjCliente())
                .dataContratacao(condicoesContrato.getContrato().getDataContratacao())
                .ativo(condicoesContrato.getContrato().isAtivo())
                .parcelasEmAtraso(condicoesContrato.getContrato().isParcelasEmAtraso())
                .build();

        List<Financeiro> financeiro = new ArrayList<>();

        condicoesContrato.getFinanceiro().forEach(f -> {
            financeiro.add(
                    Financeiro.builder()
                            .dataCalculo(f.getDataCalculo())
                            .quantidadeParcelas(f.getQuantidadeParcelas())
                            .tipoCalculo(f.getTipoCalculo().toString())
                            .valorTotal(f.getValorTotal())
                            .valorParcelas(f.getValorParcelas())
                            .diaPagamento(f.getDiaPagamento())
                            .percentualTaxaJuro(f.getPercentualTaxaJuro()).build()
            );
        });

        return CondicoesContratoResponse.builder()
                .contrato(contratoResponse)
                .financeiro(financeiro)
                .build();
    }

}
