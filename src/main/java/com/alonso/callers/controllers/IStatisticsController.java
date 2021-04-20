package com.alonso.callers.controllers;

import com.alonso.callers.model.Statistic;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/stats/")
public interface IStatisticsController {

	@GetMapping("/controllerstatistic/{methodName}")
	ResponseEntity<Statistic> getStatistic(@PathVariable String methodName);

	@GetMapping("/controllerstatistics")
	List<Statistic> getAllStatistics();

}

