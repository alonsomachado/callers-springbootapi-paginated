package com.alonso.callers.controllers;

import com.alonso.callers.model.Statistic;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@RequestMapping("/statsapi")
public interface IStatisticsController {

	@GetMapping("/")
	ResponseEntity<Map<String, Object>> generateCallStatistics();

	@GetMapping("/controllerstatistic/{methodName}")
	ResponseEntity<Statistic> getControllerStatistic(@PathVariable String methodName);

	@GetMapping("/controllerstatistics/all")
	List<Statistic> getAllStatistics();

}

