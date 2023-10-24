package org.testeposvenda.infraestructure;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.testeposvenda.domain.model.calculojuros.CalculaJuros;
import org.testeposvenda.domain.model.calculojuros.JurosCalculado;

import java.util.UUID;

@FeignClient(name = "calcula-juros", url = "http://localhost:4000")
public interface CalcularJurosFeign  {
    @PostMapping("/calcula-juros")
    JurosCalculado obterCalculo(@RequestHeader("itau-pos-venda-teste") UUID id, CalculaJuros calculaJuros);
}
