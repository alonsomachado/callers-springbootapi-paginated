package com.alonso.callers.aspects;

import com.alonso.callers.model.Statistic;
import com.alonso.callers.repository.StatisticRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.logging.Logger;

@Aspect
public class StatisticAspect {

	private final StatisticRepository repo;

	private Logger logger = Logger.getLogger(StatisticAspect.class.getName());

	public StatisticAspect(StatisticRepository repo) {
		this.repo = repo;
	}

	@Pointcut("target(com.alonso.callers.controllers.CallController)")
	public void callControllerMethods() {
	}

	@Before("callControllerMethods()")
	public void logMethodCall(JoinPoint jp) {

		String methodName = jp.getSignature().getName();

		if (this.repo.existsById(methodName)) {
			Statistic stat = this.repo.findById(methodName).get();
			stat.setNumberOfTimeIsInvoked(stat.getNumberOfTimeIsInvoked() + 1);
		} else {
			Statistic statistic = new Statistic(methodName, 1); //Primeira invocacao do metodo numberOfTimeIsInvoked vai para 1
			this.repo.save(statistic);
		}

		logger.info(methodName + ": " +
				this.repo.findById(methodName).get().getNumberOfTimeIsInvoked() +
				" time(s) is Invoked!");

	}

}
