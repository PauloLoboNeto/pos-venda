package org.testeposvenda.domain.ports.in;

import org.testeposvenda.domain.model.condicoescontrato.CondicoesContrato;

import java.util.UUID;

public interface IAditamentoQuantidadeParcelasUseCase {
    CondicoesContrato alterarQuantidadeParcela(UUID id, CondicoesContrato condicoesContrato);
}
