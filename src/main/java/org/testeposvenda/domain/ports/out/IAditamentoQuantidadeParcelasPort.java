package org.testeposvenda.domain.ports.out;

import org.testeposvenda.domain.model.calculojuros.CalculaJuros;
import org.testeposvenda.domain.model.calculojuros.JurosCalculado;
import org.testeposvenda.domain.model.condicoescontrato.CondicoesContrato;

import java.util.UUID;

public interface IAditamentoQuantidadeParcelasPort {
    JurosCalculado calcularJuros(UUID id, CalculaJuros calculaJuros);
}
