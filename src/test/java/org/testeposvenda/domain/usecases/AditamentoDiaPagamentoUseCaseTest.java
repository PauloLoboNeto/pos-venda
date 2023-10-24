package org.testeposvenda.domain.usecases;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testeposvenda.domain.exceptions.BusinessException;
import org.testeposvenda.domain.model.condicoescontrato.Aditamento;
import org.testeposvenda.domain.model.condicoescontrato.CondicoesContrato;
import org.testeposvenda.domain.model.condicoescontrato.Contrato;
import org.testeposvenda.domain.model.condicoescontrato.Financeiro;
import org.testeposvenda.domain.ports.in.IAditamentoDiaPagamentoUseCase;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AditamentoDiaPagamentoUseCaseTest {
    @Mock
    private IAditamentoDiaPagamentoUseCase useCase;

    private AditamentoDiaPagamentoUseCaseImpl aditamentoUseCase;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        aditamentoUseCase = new AditamentoDiaPagamentoUseCaseImpl();
    }

    @Test
    public void testAlterarDiaPagamentoComSucesso() {
        CondicoesContrato condicoesContrato = criarCondicoesContratoExemplo();
        CondicoesContrato resultado = aditamentoUseCase.alterarDiaPagamento(condicoesContrato);
        assertEquals(15, resultado.getFinanceiro().get(0).getDiaPagamento());
    }

    @Test(expected = BusinessException.class)
    public void testAlterarDiaPagamentoComErroQuandoNovaDataTemDiferencaDe10dias() {
        CondicoesContrato condicoesContrato = criarCondicoesContratoExemplo();
        condicoesContrato.getAditamento().setNovaDataPagamento(0);
        aditamentoUseCase.alterarDiaPagamento(condicoesContrato);
    }


    private CondicoesContrato criarCondicoesContratoExemplo() {
        Aditamento aditamento = new Aditamento();
        aditamento.setNovaDataPagamento(15);

        List<Financeiro> financeiros = new ArrayList<>();
        Financeiro financeiro = new Financeiro();
        financeiro.setDiaPagamento(10);
        financeiros.add(financeiro);

        Contrato contrato = new Contrato();
        contrato.setAtivo(true);
        contrato.setParcelasEmAtraso(false);

        CondicoesContrato condicoesContrato = new CondicoesContrato(contrato, financeiros, aditamento);

        return condicoesContrato;
    }
}
