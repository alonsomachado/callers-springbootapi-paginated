package com.alonso.callers.controllers;

import com.alonso.callers.model.Call;
import com.alonso.callers.services.ICallBS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class CallController implements ICallController {

	@Autowired
	ICallBS callService;

	@Override
	public ResponseEntity<Map<String, Object>> getAll(
			@RequestParam(required = false) String filter,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size
	) {
		return callService.getAllPaginated(filter, page, size);
	}

	@Override
	public ResponseEntity<Call> addCall(Call call) {
		return new ResponseEntity<>(callService.addCall(call), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Call>> addCalls(List<Call> calls) {
		return new ResponseEntity<>(callService.addCalls(calls), HttpStatus.OK);
	}

	@Override
	public ResponseEntity deleteCall(String id) {
		Long idLong = Long.parseLong(id);
		callService.deleteCall(idLong);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/* Extra methods just to search by the Caller and the receiver (Callee) */
	@Override
	public ResponseEntity<Map<String, Object>> getCaller(
			@RequestParam(required = false) Integer filter,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size
	) {
		return callService.getCallerPaginated(filter, page, size);
	}

	@Override
	public ResponseEntity<Map<String, Object>> getCallee(
			@RequestParam(required = false) Integer filter,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size
	) {
		return callService.getCalleePaginated(filter, page, size);
	}


}
