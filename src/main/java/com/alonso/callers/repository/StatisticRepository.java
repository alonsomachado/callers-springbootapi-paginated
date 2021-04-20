package com.alonso.callers.repository;

import com.alonso.callers.model.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticRepository extends JpaRepository<Statistic, String> {
}
