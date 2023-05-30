package br.com.gubee.interview.core.configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity generateNotFoundException(ResponseStatusException e) {
        Map<String, Object> response = new HashMap<>();

        response.put("message", e.getReason());

        return ResponseEntity.status(e.getStatus()).body(response);
    }
}