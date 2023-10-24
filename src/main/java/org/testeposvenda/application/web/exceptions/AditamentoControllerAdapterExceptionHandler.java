package org.testeposvenda.application.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.testeposvenda.application.web.AditamentoControllerAdapter;
import org.testeposvenda.domain.exceptions.BusinessException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice(assignableTypes = {AditamentoControllerAdapter.class})
public class AditamentoControllerAdapterExceptionHandler {

    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<StandardError> businessEx(BusinessException e, HttpServletRequest request){
        StandardError err = new StandardError(
                System.currentTimeMillis(),
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                e.getMessage(),
                "",
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
    }

    @ExceptionHandler({MissingRequestHeaderException.class})
    public ResponseEntity<StandardError> missingHeader(MissingRequestHeaderException e, HttpServletRequest request){
        StandardError err = new StandardError(
                System.currentTimeMillis(),
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                e.getMessage(),
                "Erro ao obter parâmetros do Header",
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<StandardError> missingArgument(MethodArgumentNotValidException e, HttpServletRequest request){

        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            if (error instanceof FieldError) {
                FieldError fieldError = (FieldError) error;
                String fieldName = fieldError.getField();
                String errorMessage = fieldError.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            } else {
                errors.put("message", error.getDefaultMessage());
            }
        });

        StandardError err = new StandardError(
                System.currentTimeMillis(),
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                e.getLocalizedMessage(),
                errors.values().toString(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
    }

}
