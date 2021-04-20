package com.alonso.callers.services;

import com.alonso.callers.model.Call;
import com.alonso.callers.repository.CallRepository;

import java.util.List;
import java.util.stream.Collectors;

public class CallBS implements ICallBS {

	private final CallRepository repo;

	public CallBS(CallRepository repo) {
		this.repo = repo;
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
		}
	}

}
