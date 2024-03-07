package com.spring_project.pre_blog_api.Exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDate;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    //for all generic exception
    public ResponseEntity<ErrorDetails> handleAllException(Exception ex, WebRequest req){
        ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(),ex.getMessage(),req.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //user not found
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDetails> userNotFoundException(Exception ex, WebRequest req){
        ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(),ex.getMessage(),req.getDescription(false));
        return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
    }

    //error related to validation
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status,WebRequest request){
        BindingResult bindingResult = ex.getBindingResult();
        String errorMessage = bindingResult.getFieldErrors()
                                .stream()
                                .map(this::mapFieldErrorToMessage)
                                .collect(Collectors.joining(" || "));
        ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(),errorMessage,request.getDescription(false));
        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }

    private String mapFieldErrorToMessage(FieldError fieldError){
        return fieldError.getField() + ":" + fieldError.getDefaultMessage();
    }
}
