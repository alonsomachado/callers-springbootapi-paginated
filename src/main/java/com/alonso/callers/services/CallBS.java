package com.alonso.callers.services;

import com.alonso.callers.model.Call;
import com.alonso.callers.model.TypeCall;
import com.alonso.callers.repository.CallRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CallBS implements ICallBS {

	private final CallRepository repo;

	public CallBS(CallRepository repo) {
		this.repo = repo;
	}

	@Override
	public ResponseEntity<Map<String, Object>> getAllPaginated(String filterType, int page, int size) {
		try {
			List<Call> callLogArray = new ArrayList<Call>();
			Pageable paging = PageRequest.of(page, size);

			Page<Call> pageCall;
			if (filterType == null) {
				pageCall = repo.findAll(paging);
			} else {
				TypeCall myType = TypeCall.valueOf(filterType.toUpperCase());
				pageCall = repo.findByTypeContaining(myType, paging);
			}

			callLogArray = pageCall.getContent();

			Map<String, Object> response = new HashMap<>();
			response.put("callLogArray", callLogArray);
			response.put("currentPage", pageCall.getNumber());
			response.put("totalItems", pageCall.getTotalElements());
			response.put("totalPages", pageCall.getTotalPages());

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public Call addCall(Call toSave) {
		return this.repo.save(toSave);
	}

	@Override
	public List<Call> addCalls(List<Call> callList) {
		return callList.stream().map(call -> this.repo.save(call)).collect(Collectors.toList());
	}

	@Override
	public void deleteCall(Long id) {
		if (this.repo.findById(id).isPresent()) {
			this.repo.deleteById(id);
		} else {
			throw new NoSuchElementException();
		}
	}

	/* Extra methods just to search by the Caller and the receiver (Callee) */

	@Override
	public ResponseEntity<Map<String, Object>> getCallerPaginated(Integer callerNumber, int page, int size) {
		try {
			List<Call> callLogArray = new ArrayList<Call>();
			Pageable paging = PageRequest.of(page, size);

			Page<Call> pageCall;
			if (callerNumber == null)
				pageCall = repo.findAll(paging);
			else
				pageCall = repo.findByCaller(callerNumber, paging);

			callLogArray = pageCall.getContent();

			Map<String, Object> response = new HashMap<>();
			response.put("callLogArray", callLogArray);
			response.put("currentPage", pageCall.getNumber());
			response.put("totalItems", pageCall.getTotalElements());
			response.put("totalPages", pageCall.getTotalPages());

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<Map<String, Object>> getCalleePaginated(Integer calleeNumber, int page, int size) {
		try {
			List<Call> callLogArray = new ArrayList<Call>();
			Pageable paging = PageRequest.of(page, size);

			Page<Call> pageCall;
			if (calleeNumber == null)
				pageCall = repo.findAll(paging);
			else
				pageCall = repo.findByCallee(calleeNumber, paging);

			callLogArray = pageCall.getContent();

			Map<String, Object> response = new HashMap<>();
			response.put("callLogArray", callLogArray);
			response.put("currentPage", pageCall.getNumber());
			response.put("totalItems", pageCall.getTotalElements());
			response.put("totalPages", pageCall.getTotalPages());

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
