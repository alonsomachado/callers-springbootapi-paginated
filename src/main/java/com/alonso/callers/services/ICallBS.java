package com.alonso.callers.services;

import com.alonso.callers.model.Call;

import java.util.List;

public interface ICallBS {

	Call addCall(Call toSave);

	List<Call> addCalls(List<Call> callList);

	void deleteCall(Long id);
}
