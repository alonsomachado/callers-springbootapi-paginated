package com.alonso.callers.services;

import com.alonso.callers.model.Statistic;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

public interface IStatisticsBS {

	ResponseEntity<Map<String, Object>> generateCallStatistics();

	ResponseEntity<Statistic> getControllerStatistic(String methodName);

	List<Statistic> getAllStatistics();

}
