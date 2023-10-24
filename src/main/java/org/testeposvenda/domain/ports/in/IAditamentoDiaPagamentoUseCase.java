package org.testeposvenda.domain.ports.in;

import org.testeposvenda.domain.model.condicoescontrato.CondicoesContrato;

public interface IAditamentoDiaPagamentoUseCase {
    CondicoesContrato alterarDiaPagamento(CondicoesContrato condicoesContrato);
}
