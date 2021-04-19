package com.alonso.callers.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CallController {

	public ResponseEntity<Map<String, Object>> getAll(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size
	) {
		Map<String, Object> response = new HashMap<>();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
