package com.starter.registration.excetion;
import com.starter.registration.dto.ErrorMessageDTO;
import javassist.tools.web.BadHttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;


@ControllerAdvice
@Slf4j
public class RestErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ErrorMessageDTO> handleResourceNotFound(final ResourceNotFoundException ex) {
        final ErrorMessageDTO apiError = new ErrorMessageDTO(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({BadHttpRequest.class})
    public ResponseEntity<ErrorMessageDTO> handelBadRequestException(final ResourceNotFoundException ex) {
        final ErrorMessageDTO apiError = new ErrorMessageDTO(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
        logException(ex);
        return new ResponseEntity<>(apiError, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<ErrorMessageDTO> handleNotFound(final EntityNotFoundException ex) {
        final ErrorMessageDTO apiError = new ErrorMessageDTO(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({IllegalArgumentException.class, ConstraintViolationException.class})
    public ResponseEntity<ErrorMessageDTO> handleIllegalArgumentError(final RuntimeException ex) {
        final ErrorMessageDTO apiError = new ErrorMessageDTO(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
        logException(ex);
        return new ResponseEntity<>(apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<ErrorMessageDTO> handleAuthenticationError(final RuntimeException ex) {
        final ErrorMessageDTO apiError = new ErrorMessageDTO(HttpStatus.UNAUTHORIZED, ex.getLocalizedMessage());
        logException(ex);
        return new ResponseEntity<>(apiError, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ErrorMessageDTO> handleInternalError(final RuntimeException ex) {
        final ErrorMessageDTO apiError = new ErrorMessageDTO(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage());
        logException(ex);
        return new ResponseEntity<>(apiError, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<ErrorMessageDTO> handleForbiddenRequest(final AccessDeniedException ex, final WebRequest request) {
        final ErrorMessageDTO apiError = new ErrorMessageDTO(HttpStatus.FORBIDDEN, ex.getLocalizedMessage());
        logException(ex);
        return new ResponseEntity<>(apiError, new HttpHeaders(), HttpStatus.FORBIDDEN);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
                                                                  final HttpHeaders headers,
                                                                  final HttpStatus status,
                                                                  final WebRequest request) {
        final List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(field -> field.getField().concat(" ").concat(field.getDefaultMessage()))
                .collect(Collectors.toList());
        final ErrorMessageDTO apiError = new ErrorMessageDTO(HttpStatus.BAD_REQUEST, errors);
        logException(ex);
        return new ResponseEntity<>(apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }


    private void logException(final Throwable ex) {
        final Throwable cause = ex.getCause() == null ? ex : ex.getCause();
        log.error(" request error: response: ({}), cause: ({}), stack: {}",
                cause.getMessage(), cause.getClass().getSimpleName(), cause.getStackTrace());
    }
}
