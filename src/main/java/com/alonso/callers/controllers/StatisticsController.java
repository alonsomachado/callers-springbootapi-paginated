package com.alonso.callers.controllers;

import com.alonso.callers.model.Statistic;
import com.alonso.callers.services.IStatisticsBS;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class StatisticsController implements IStatisticsController {

	private final IStatisticsBS statisticsBS;

	public StatisticsController(IStatisticsBS statisticsBS) {
		this.statisticsBS = statisticsBS;
	}

	@Override
	public ResponseEntity<Map<String, Object>> generateCallStatistics() {
		return statisticsBS.generateCallStatistics();
	}

	@Override
	public ResponseEntity<Statistic> getControllerStatistic(@PathVariable String methodName) {
		return statisticsBS.getControllerStatistic(methodName);
	}

	@Override
	public List<Statistic> getAllStatistics() {
		return statisticsBS.getAllStatistics();
	}
}
