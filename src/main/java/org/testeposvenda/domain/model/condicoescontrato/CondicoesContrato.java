package org.testeposvenda.domain.model.condicoescontrato;

import lombok.*;

import java.util.List;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public final class CondicoesContrato {
    private Contrato contrato;
    private List<Financeiro> financeiro;
    private Aditamento aditamento;
}
