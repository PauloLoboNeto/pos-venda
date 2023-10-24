package org.testeposvenda.application.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.testeposvenda.application.web.requests.CondicoesContratoRequest;
import org.testeposvenda.application.web.responses.CondicoesContratoResponse;
import org.testeposvenda.domain.exceptions.BusinessException;
import org.testeposvenda.domain.ports.in.IAditamentoDiaPagamentoUseCase;
import org.testeposvenda.domain.ports.in.IAditamentoQuantidadeParcelasUseCase;

import javax.validation.Valid;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/aditamento")
@RequiredArgsConstructor
public class AditamentoControllerAdapter {

    private final IAditamentoDiaPagamentoUseCase aditamentoDiaPagamentoUseCase;
    private final IAditamentoQuantidadeParcelasUseCase aditamentoQuantidadeParcelasUseCase;


    @PostMapping(value = "/altera-quantidade-parcelas", produces = "application/json")
    public ResponseEntity<CondicoesContratoResponse> alterarQuantidadeParcelas(@RequestHeader("itau-pos-venda-teste") UUID id, @Valid @RequestBody CondicoesContratoRequest request) {

        var novasCondicoes = this.aditamentoQuantidadeParcelasUseCase
                .alterarQuantidadeParcela(id, request.toCondicoesContrato(true));

        return ResponseEntity.ok(new CondicoesContratoResponse().toCondicoesContratoResponse(novasCondicoes));
    }

    @PostMapping(value = "/altera-dia-pagamento", produces = "application/json")
    public ResponseEntity<CondicoesContratoResponse> alterarDiaPagamento(@RequestHeader("itau-pos-venda-teste") UUID id, @RequestBody @Valid CondicoesContratoRequest request) {

        if (Objects.isNull(id))
            throw new BusinessException("Header itau-pos-venda-teste não informado");

            var novasCondicoes = this.aditamentoDiaPagamentoUseCase
                    .alterarDiaPagamento(request.toCondicoesContrato(false));

            var response = new CondicoesContratoResponse();

            return ResponseEntity.ok(response.toCondicoesContratoResponse(novasCondicoes));
    }
}
