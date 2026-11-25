package com.nexa.acceso.config;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
 @ExceptionHandler(IllegalArgumentException.class)
 ResponseEntity<Map<String,String>> badRequest(IllegalArgumentException ex){
  return ResponseEntity.badRequest().body(Map.of("error",ex.getMessage()));
 }
 @ExceptionHandler(MethodArgumentNotValidException.class)
 ResponseEntity<Map<String,String>> validation(MethodArgumentNotValidException ex){
  String message=ex.getBindingResult().getFieldErrors().stream().findFirst()
   .map(e->e.getField()+": "+e.getDefaultMessage()).orElse("Datos inválidos.");
  return ResponseEntity.badRequest().body(Map.of("error",message));
 }
}
