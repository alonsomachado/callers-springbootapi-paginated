package com.alonso.callers.services;

import com.alonso.callers.model.Call;
import com.alonso.callers.model.Statistic;
import com.alonso.callers.model.TypeCall;
import com.alonso.callers.repository.CallRepository;
import com.alonso.callers.repository.StatisticRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

@Service
public class StatisticsBS implements IStatisticsBS{

	private final StatisticRepository statisticRepository;
	private final CallRepository callRepository;

	public StatisticsBS(StatisticRepository repo, CallRepository callRepository) {
		this.statisticRepository = repo;
		this.callRepository = callRepository;
	}

	@Override
	public ResponseEntity<Map<String, Object>> generateCallStatistics(){
		try {
			List<Call> callLogArray = new ArrayList<Call>();
			callLogArray = callRepository.findAll();
			Set<Integer> callers = new HashSet<Integer>();
			Set<Integer> callees = new HashSet<Integer>();
			Map<Integer,Object> callersObjectMap = new HashMap<>();
			Map<Integer,Object> calleesObjectMap = new HashMap<>();
			List<Call> finalCallLogArray = callLogArray;

			callers = callLogArray.stream().map(Call::getCaller).collect(Collectors.toSet());
			callees = callLogArray.stream().map(Call::getCallee).collect(Collectors.toSet());

			Map<String, Object> response = new HashMap<>();
			response.put("Total number of calls ", callLogArray.size());
			response.put("Total call cost ", callLogArray.stream().mapToDouble(a -> {
				if (a.getType().equals(TypeCall.INBOUND)) {
					long milliseconds = a.getEnd().getTime() - a.getStart().getTime();
					int seconds = (int) milliseconds / 1000;
					int minutes = (seconds % 3600) / 60;
					double value = 0.10d;
					if (minutes > 5) {
						value += (minutes - 5) * 0.05;
					}
					return value;
				}
				return 0;
			}).sum());
			callers.stream().forEach(caller -> {
				callersObjectMap.put(caller, finalCallLogArray.stream().filter(a -> a.getCaller().equals(caller)).count());
			});
			callees.stream().forEach(callee -> {
				calleesObjectMap.put(callee, finalCallLogArray.stream().filter(a -> a.getCallee().equals(callee)).count());
			});

			response.put("Callers", callersObjectMap);
			response.put("Callees", calleesObjectMap);

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<Statistic> getControllerStatistic(String methodName) {

		Statistic stat = this.statisticRepository.findById(methodName).get();

		return new ResponseEntity(stat, HttpStatus.OK);
	}

	@Override
	public List<Statistic> getAllStatistics() {
		return this.statisticRepository.findAll();
	}
}
