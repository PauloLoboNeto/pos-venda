package org.testeposvenda.application.web.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.testeposvenda.domain.enums.TipoCalculo;
import org.testeposvenda.domain.exceptions.BusinessException;
import org.testeposvenda.domain.model.condicoescontrato.CondicoesContrato;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CondicoesContratoRequest {
    @Valid
    @NotNull(message = "Campo contrato não informado")
    private Contrato contrato;
    @Valid
    @NotNull(message = "Campo financeiro não informado")
    private List<Financeiro> financeiro;
    @Valid
    @NotNull(message = "Campo aditamento não informado")
    private Aditamento aditamento;

    public CondicoesContrato toCondicoesContrato(boolean alterarQuantidadeParcelas) {
        var contratoDomain = org.testeposvenda.domain.model.condicoescontrato.Contrato.builder()
                .idContrato(getContrato().getIdContrato())
                .numeroCpfCnpjCliente(getContrato().getNumeroCpfCnpjCliente())
                .dataContratacao(getContrato().getDataContratacao())
                .ativo(getContrato().isAtivo())
                .parcelasEmAtraso(getContrato().isParcelasEmAtraso())
                .build();

        List<org.testeposvenda.domain.model.condicoescontrato.Financeiro> financeiroDomain = new ArrayList<>();

        financeiro.forEach(f -> {
            financeiroDomain.add(
                    org.testeposvenda.domain.model.condicoescontrato.Financeiro.builder()
                            .dataCalculo(f.getDataCalculo())
                            .tipoCalculo(TipoCalculo.valueOf(f.getTipoCalculo()))
                            .quantidadeParcelas(f.getQuantidadeParcelas())
                            .valorTotal(f.getValorTotal())
                            .valorParcelas(f.getValorParcelas())
                            .diaPagamento(f.getDiaPagamento())
                            .percentualTaxaJuro(f.getPercentualTaxaJuro()).build()
            );
        });


            if (alterarQuantidadeParcelas && 0 == aditamento.getNovaQuantidadeParcelas())
                throw new BusinessException("Nova quantidade de parcelas não informada");

            if (!alterarQuantidadeParcelas && 0 == aditamento.getNovaDataPagamento())
                throw new BusinessException("Nova data de pagamento não informada");


        var aditamento = org.testeposvenda.domain.model.condicoescontrato.Aditamento.builder()
                .novaQuantidadeParcelas(getAditamento().getNovaQuantidadeParcelas())
                .novaDataPagamento(getAditamento().getNovaDataPagamento())
                .build();


        return CondicoesContrato.builder()
                .contrato(contratoDomain)
                .financeiro(financeiroDomain)
                .aditamento(aditamento)
                .build();
    }
}
