package com.alonso.callers.controllers;

import com.alonso.callers.model.Call;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/*** Não é complexo o suficiente para usar DTOs e Mappers para a comunicação externa ***/

@RequestMapping("/api")
public interface ICallController {

	@GetMapping("/all")
	ResponseEntity<Map<String, Object>> getAll(
			@RequestParam(required = false) String filter,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size);

	@PostMapping
	ResponseEntity<Call> addCall(@RequestBody Call call);

	@PostMapping
	ResponseEntity<List<Call>> addCalls(@RequestBody List<Call> calls);

	@DeleteMapping
	ResponseEntity deleteCall(@RequestParam @NonNull Long id);

	//TODO Statistics - Criar um controller separado e Model para a estatisticas com o AOP
}
