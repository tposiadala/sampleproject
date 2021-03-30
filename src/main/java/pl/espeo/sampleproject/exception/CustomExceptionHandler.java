package pl.espeo.sampleproject.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.espeo.sampleproject.controller.dto.ApiError;

import java.time.LocalDateTime;
import java.util.Arrays;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest webRequest) {
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .timestamp(LocalDateTime.now())
                .path(((ServletWebRequest)webRequest).getRequest().getRequestURI())
                .message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .debugMessage(ex.getLocalizedMessage())
                .build();
        System.err.println(Arrays.toString(ex.getStackTrace()));
        return handleExceptionInternal(ex, apiError,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, webRequest);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex, WebRequest webRequest) {
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .timestamp(LocalDateTime.now())
                .path(((ServletWebRequest)webRequest).getRequest().getRequestURI())
                .message(ex.getMessage())
                .debugMessage(ex.getLocalizedMessage())
                .build();
        System.err.println(Arrays.toString(ex.getStackTrace()));
        return handleExceptionInternal(ex, apiError,
                new HttpHeaders(), HttpStatus.NOT_FOUND, webRequest);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
        ApiError apiError = ApiError.builder()
                .status(status.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .timestamp(LocalDateTime.now())
                .path(((ServletWebRequest)webRequest).getRequest().getRequestURI())
                .message("Malformed JSON or path request")
                .debugMessage(ex.getMessage())
                .build();
        System.err.println(Arrays.toString(ex.getStackTrace()));
        return handleExceptionInternal(ex, apiError,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
    }
}