package com.alonso.callers.services;

import com.alonso.callers.model.Call;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ICallBS {

	ResponseEntity<Map<String, Object>> getAllPaginated(String filter, int page, int size);

	ResponseEntity<Map<String, Object>> getCallerPaginated(Integer number, int page, int size);

	ResponseEntity<Map<String, Object>> getCalleePaginated(Integer number, int page, int size);

	Call addCall(Call toSave);

	List<Call> addCalls(List<Call> callList);

	void deleteCall(Long id);
}
