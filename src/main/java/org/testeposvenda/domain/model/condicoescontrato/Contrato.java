package org.testeposvenda.domain.model.condicoescontrato;
import lombok.*;

import java.util.Date;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public final class Contrato {
    private Long idContrato;
    private String numeroCpfCnpjCliente;
    private String dataContratacao;
    private boolean ativo;
    private boolean parcelasEmAtraso;
}
