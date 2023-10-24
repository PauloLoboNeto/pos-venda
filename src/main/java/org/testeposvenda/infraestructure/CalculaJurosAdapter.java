package org.testeposvenda.infraestructure;

import lombok.RequiredArgsConstructor;
import org.testeposvenda.domain.model.calculojuros.CalculaJuros;
import org.testeposvenda.domain.model.calculojuros.Data;
import org.testeposvenda.domain.model.calculojuros.JurosCalculado;
import org.testeposvenda.domain.ports.out.IAditamentoQuantidadeParcelasPort;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

@RequiredArgsConstructor
public class CalculaJurosAdapter implements IAditamentoQuantidadeParcelasPort {
    private final CalcularJurosFeign servicoExterno;
    @Override
    public JurosCalculado calcularJuros(UUID id, CalculaJuros calculaJuros) {

//        return servicoExterno.obterCalculo(id, calculaJuros);

        var data = Data.builder()
                .percentual_juros(new BigDecimal(1.93).setScale(2, RoundingMode.HALF_UP))
                .valor_total(new BigDecimal(52000.00))
                .build();

        return  JurosCalculado.builder()
                .data(data)
                .build();
    }
}
