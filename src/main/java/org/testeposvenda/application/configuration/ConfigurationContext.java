package org.testeposvenda.application.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testeposvenda.domain.ports.in.IAditamentoDiaPagamentoUseCase;
import org.testeposvenda.domain.ports.in.IAditamentoQuantidadeParcelasUseCase;
import org.testeposvenda.domain.ports.out.IAditamentoQuantidadeParcelasPort;
import org.testeposvenda.domain.usecases.AditamentoDiaPagamentoUseCaseImpl;
import org.testeposvenda.domain.usecases.AditamentoQuantidadeParcelasUseCaseImpl;
import org.testeposvenda.infraestructure.CalculaJurosAdapter;
import org.testeposvenda.infraestructure.CalcularJurosFeign;

@Configuration
public class ConfigurationContext {

    @Bean
    public IAditamentoQuantidadeParcelasPort iAditamentoQuantidadeParcelasPort(CalcularJurosFeign calcularJurosFeign) {
        return new CalculaJurosAdapter(calcularJurosFeign);
    }

    @Bean
    public IAditamentoQuantidadeParcelasUseCase aditamentoQuantidadeParcelasUseCase(IAditamentoQuantidadeParcelasPort iAditamentoQuantidadeParcelasPort) {
        return new AditamentoQuantidadeParcelasUseCaseImpl(iAditamentoQuantidadeParcelasPort);
    }

    @Bean
    public IAditamentoDiaPagamentoUseCase aditamentoDiaPagamentoUseCase() {
        return new AditamentoDiaPagamentoUseCaseImpl();
    }
}
