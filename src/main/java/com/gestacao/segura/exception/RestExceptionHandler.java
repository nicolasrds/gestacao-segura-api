package com.gestacao.segura.exception;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.UnexpectedTypeException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hibernate.LazyInitializationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<DadosErro> handleEntityNotFoundException(EntityNotFoundException ex) {
        return buildResponseEntity(HttpStatus.NOT_FOUND, "Recurso não encontrado", ex);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<DadosErro> handleNoResourceFoundException(NoResourceFoundException ex) {
        return buildResponseEntity(HttpStatus.NOT_FOUND, "Recurso não encontrado", ex);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<DadosErro> handleNoSuchElementException(NoSuchElementException ex) {
        return buildResponseEntity(HttpStatus.NOT_FOUND, "Elemento não encontrado", ex);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DadosErroValidacao>> handleValidationException(MethodArgumentNotValidException ex) {
        List<DadosErroValidacao> errors = ex.getFieldErrors().stream()
                .map(DadosErroValidacao::new)
                .toList();
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<DadosErro> handleConstraintViolationException(ConstraintViolationException ex) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST, "Violação de restrição de dados", ex);
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    public ResponseEntity<DadosErro> handleUnexpectedTypeException(UnexpectedTypeException ex) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST, "Tipo inesperado encontrado", ex);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<DadosErro> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST, "Erro de integridade dos dados", ex);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<DadosErro> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST, "Erro na leitura da mensagem HTTP", ex);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<DadosErro> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST, "Método HTTP não suportado", ex);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<DadosErro> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST, "Nenhum resultado encontrado para a consulta", ex);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<DadosErro> handleNullPointerException(NullPointerException ex) {
        return buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno no servidor", ex);
    }

    @ExceptionHandler(LazyInitializationException.class)
    public ResponseEntity<DadosErro> handleLazyInitializationException(LazyInitializationException ex) {
        return buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao carregar dados relacionados", ex);
    }

    private ResponseEntity<DadosErro> buildResponseEntity(HttpStatus status, String mensagem, Exception ex) {
        return ResponseEntity.status(status).body(new DadosErro(mensagem, ExceptionUtils.getRootCauseMessage(ex)));
    }

    private record DadosErroValidacao(String campo, String mensagemUsuario, String mensagemDesenvolvedor) {
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(), "Campo inválido", erro.toString());
        }
    }

    private record DadosErro(String mensagemUsuario, String mensagemDesenvolvedor) {
    }
}
