package org.testeposvenda.domain.usecases;

import lombok.RequiredArgsConstructor;
import org.testeposvenda.domain.Utils;
import org.testeposvenda.domain.enums.TipoCalculo;
import org.testeposvenda.domain.exceptions.BusinessException;
import org.testeposvenda.domain.model.condicoescontrato.CondicoesContrato;
import org.testeposvenda.domain.model.condicoescontrato.Contrato;
import org.testeposvenda.domain.model.condicoescontrato.Financeiro;
import org.testeposvenda.domain.ports.in.IAditamentoDiaPagamentoUseCase;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class AditamentoDiaPagamentoUseCaseImpl implements IAditamentoDiaPagamentoUseCase {
    private int UM = 1;
    @Override
    public CondicoesContrato alterarDiaPagamento(CondicoesContrato condicoesContrato) {
        var financeiro = this.obterFinanceiro(condicoesContrato.getFinanceiro());
        var dataAtual = financeiro.getDiaPagamento();
        var novaData = condicoesContrato.getAditamento().getNovaDataPagamento();

        if(novaData < UM ||
                !diaExisteNoMes(novaData) ||
                !contratoElegivelAlteracao(condicoesContrato.getContrato()) ||
                diferencaDataMaiorQue10Dias(condicoesContrato.getAditamento().getNovaDataPagamento(), dataAtual))
            throw new BusinessException("Não apto à alteração do dia do pagamento");

        List<Financeiro> financeiros = new ArrayList<>();
        financeiros.add(this.alterarDiaPagamento(condicoesContrato.getAditamento().getNovaDataPagamento(), financeiro));
        condicoesContrato.setFinanceiro(financeiros);
        return condicoesContrato;
    }

    private Financeiro alterarDiaPagamento(int novaData, Financeiro financeiro) {
        financeiro
                .setDiaPagamento(novaData);
        return financeiro;
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

    private boolean contratoElegivelAlteracao(Contrato contrato){
        return contrato.isAtivo() && !contrato.isParcelasEmAtraso();
    }

    private boolean diaExisteNoMes(int novaData) {
        return Utils.diasNoMes() > novaData;
    }

    private boolean diferencaDataMaiorQue10Dias(int novaData, int dataAtual){
        return Math.abs(dataAtual - novaData) > 10;
    }
}
