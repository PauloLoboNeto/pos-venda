package org.testeposvenda.domain.usecases;

import lombok.RequiredArgsConstructor;
import org.testeposvenda.domain.Utils;
import org.testeposvenda.domain.enums.CriterioCalculo;
import org.testeposvenda.domain.enums.TipoCalculo;
import org.testeposvenda.domain.exceptions.BusinessException;
import org.testeposvenda.domain.model.calculojuros.CalculaJuros;
import org.testeposvenda.domain.model.calculojuros.Contrato;
import org.testeposvenda.domain.model.calculojuros.JurosCalculado;
import org.testeposvenda.domain.model.condicoescontrato.CondicoesContrato;
import org.testeposvenda.domain.model.condicoescontrato.Financeiro;
import org.testeposvenda.domain.ports.in.IAditamentoQuantidadeParcelasUseCase;
import org.testeposvenda.domain.ports.out.IAditamentoQuantidadeParcelasPort;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class AditamentoQuantidadeParcelasUseCaseImpl implements IAditamentoQuantidadeParcelasUseCase {

    private final IAditamentoQuantidadeParcelasPort port;

    @Override
    public CondicoesContrato alterarQuantidadeParcela(UUID id, CondicoesContrato condicoesContrato) {
        var financeiro = this.obterFinanceiro(condicoesContrato.getFinanceiro());
        var novaQuantidaParcelaSolicitada = condicoesContrato.getAditamento().getNovaQuantidadeParcelas();

        if(!condicoesContrato.getContrato().isAtivo() ||
                this.quantidadeParcelaInferiorAAtual(novaQuantidaParcelaSolicitada, financeiro.getQuantidadeParcelas())
        )
            throw new BusinessException("Não apto à alteração do dia do pagamento");


        JurosCalculado resposta = this.calcularJuros(id, condicoesContrato, financeiro);

        var novoValorParcelas = resposta.getData().getValor_total()
                .divide(new BigDecimal(novaQuantidaParcelaSolicitada),  2, BigDecimal.ROUND_HALF_UP);

        Financeiro financeiroAditado = Financeiro.builder()
                .quantidadeParcelas(novaQuantidaParcelaSolicitada)
                .valorParcelas(novoValorParcelas)
                .dataCalculo(Utils.gerarDataAtual())
                .tipoCalculo(TipoCalculo.ADITAMENTO)
                .valorTotal(resposta.getData().getValor_total())
                .diaPagamento(financeiro.getDiaPagamento())
                .percentualTaxaJuro(resposta.getData().getPercentual_juros())
                .build();

        condicoesContrato.getFinanceiro().add(financeiroAditado);

        return condicoesContrato;
    }

    private JurosCalculado calcularJuros(UUID id, CondicoesContrato condicoesContrato, Financeiro financeiro){
        var contratoJuros = Contrato.builder()
                .definirQuantidadeParcelas(condicoesContrato.getAditamento().getNovaQuantidadeParcelas())
                .definirCriterioCalculo(CriterioCalculo.JUROS_SIMPLES)
                .definirDataContratacao(condicoesContrato.getContrato().getDataContratacao())
                .definirValorContratacao(financeiro.getValorTotal())
                .build();

        CalculaJuros calculaJuros = CalculaJuros.builder()
                .contrato(contratoJuros)
                .build();

        return port.calcularJuros(id, calculaJuros);
    }


    private Financeiro obterFinanceiro(List<Financeiro> financeiro) {
        financeiro
                .stream()
                .filter(res -> res.getTipoCalculo() == TipoCalculo.CONTRATACAO)
                .findFirst();

        if(financeiro.isEmpty())
            throw new BusinessException("Objeto financeiro tipo calculo contratacao não encontrado");

        return financeiro.get(0);
    }

    private boolean quantidadeParcelaInferiorAAtual(int novaQuantidade, int atual){
        return novaQuantidade < atual;
    }
}
