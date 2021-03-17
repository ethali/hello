package com.cooperl.hello.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.core.codec.DecodingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ServerWebExchange;

import static java.lang.System.currentTimeMillis;

@RestControllerAdvice
@RequiredArgsConstructor
class DomainExceptionAdvice {

    private final ReqTracer tracer;

    @ExceptionHandler(DecodingException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(DecodingException exception, ServerWebExchange exchange) {
        return handleRuntimeException(exception, exchange, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(WebExchangeBindException exception, ServerWebExchange exchange) {
        return handleRuntimeException(exception, exchange, exception.getStatus());
    }

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(WebClientResponseException exception, ServerWebExchange exchange) {
        return handleRuntimeException(exception, exchange, exception.getStatusCode());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(Exception exception, ServerWebExchange exchange) {
        return handleRuntimeException(exception, exchange, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ErrorResponse> handleRuntimeException(Exception exception, ServerWebExchange exchange, HttpStatus status) {
        final var request = exchange.getRequest();

        return new ResponseEntity<>(new ErrorResponse(
            currentTimeMillis(),
            request.getPath().value(),
            status.value(),
            status.getReasonPhrase(),
            exception.getMessage(),
            tracer.traceId()
        ), status);
    }
}
