package org.testeposvenda.application.web.responses;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contrato {

    private Long idContrato;

    private int ultimoDigitoContrato;

    private String numeroCpfCnpjCliente;

    private String dataContratacao;

    private boolean ativo;

    private boolean parcelasEmAtraso;
}
