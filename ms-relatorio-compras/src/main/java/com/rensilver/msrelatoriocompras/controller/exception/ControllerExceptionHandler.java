package com.rensilver.msrelatoriocompras.controller.exception;

import com.rensilver.msrelatoriocompras.service.exception.ExternalAPIConnectionException;
import com.rensilver.msrelatoriocompras.service.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError("Recurso não encontrado");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ExternalAPIConnectionException.class)
    public ResponseEntity<StandardError> externalServiceUnavailable(ExternalAPIConnectionException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.SERVICE_UNAVAILABLE;
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError("Serviço externo indisponível");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
