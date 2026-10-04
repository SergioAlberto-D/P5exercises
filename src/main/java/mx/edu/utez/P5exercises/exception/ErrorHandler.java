package mx.edu.utez.P5exercises.exception;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import mx.edu.utez.P5exercises.exception.custom.CustomBadRequest;

@RestControllerAdvice 
public class ErrorHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> validar(MethodArgumentNotValidException ex){
        Map<String, String> errores = new LinkedHashMap<>();
        for (FieldError err : ex.getBindingResult().getFieldErrors()) {
            errores.put(err.getField(), err.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(errores);
    }@ExceptionHandler(CustomBadRequest.class)
    public ResponseEntity<Map<String, String>> handleCustomBadRequest(CustomBadRequest ex) {
        Map<String, String> errores = new LinkedHashMap<>();
        errores.put("error", ex.getMessage());
        return ResponseEntity.badRequest().body(errores);
    }

}
