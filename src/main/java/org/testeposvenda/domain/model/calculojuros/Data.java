package org.testeposvenda.domain.model.calculojuros;

import lombok.*;

import java.math.BigDecimal;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public final class Data {
        private BigDecimal percentual_juros;
        private BigDecimal valor_total;
}
