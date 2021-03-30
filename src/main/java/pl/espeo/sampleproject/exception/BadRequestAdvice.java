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

//@RestControllerAdvice
public class BadRequestAdvice extends ResponseEntityExceptionHandler {

//    @ExceptionHandler(EntityNotFoundException.class)
//    public ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex, WebRequest webRequest) {
//        ApiError apiError = ApiError.builder()
//                .status(HttpStatus.NOT_FOUND.value())
//                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
//                .timestamp(LocalDateTime.now())
//                .path(((ServletWebRequest)webRequest).getRequest().getRequestURI())
//                .message(ex.getMessage())
//                .debugMessage(ex.getLocalizedMessage())
//                .build();
//        System.err.println(Arrays.toString(ex.getStackTrace()));
//        return handleExceptionInternal(ex, apiError,
//                new HttpHeaders(), HttpStatus.NOT_FOUND, webRequest);
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
//        ApiError apiError = ApiError.builder()
//                .status(status.value())
//                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
//                .timestamp(LocalDateTime.now())
//                .path(((ServletWebRequest)webRequest).getRequest().getRequestURI())
//                .message("Malformed JSON or path request")
//                .debugMessage(ex.getLocalizedMessage())
//                .build();
//        System.err.println(Arrays.toString(ex.getStackTrace()));
//        return handleExceptionInternal(ex, apiError,
//                new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
//    }
}
