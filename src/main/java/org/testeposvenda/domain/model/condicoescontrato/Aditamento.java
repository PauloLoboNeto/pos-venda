package org.testeposvenda.domain.model.condicoescontrato;

import lombok.*;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public final class Aditamento {
    private int novaQuantidadeParcelas;

    private int novaDataPagamento;
}
