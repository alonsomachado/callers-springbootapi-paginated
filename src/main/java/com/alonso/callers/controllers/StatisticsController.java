package com.alonso.callers.controllers;

import com.alonso.callers.model.Statistic;
import com.alonso.callers.repository.StatisticRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StatisticsController implements IStatisticsController {

	private final StatisticRepository repo;

	public StatisticsController(StatisticRepository repo) {
		this.repo = repo;
	}

	@Override
	public ResponseEntity<Statistic> getStatistic(@PathVariable String methodName) {

		Statistic stat = this.repo.findById(methodName).get();

		return new ResponseEntity(stat, HttpStatus.OK);
	}

	@Override
	public List<Statistic> getAllStatistics() {
		return this.repo.findAll();
	}
}
