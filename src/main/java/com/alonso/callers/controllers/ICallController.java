package com.alonso.callers.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public interface ICallController {

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAll(
            @RequestParam(required = false) String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size);

    @PostMapping //Decidir no POST se usar Request Body ou Request Param
    public ResponseEntity<Map<String, Object>> addCall();

    //TODO Statistics - Criar um controller separado e Model para a estatisticas com o AOP

    @DeleteMapping
    public ResponseEntity<Map<String, Object>> deleteCall(@RequestParam @NonNull Long id);

}
