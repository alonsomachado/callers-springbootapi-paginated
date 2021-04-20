package com.alonso.callers.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Statistic {

	@Id
	String methodName;
	long numberOfTimeIsInvoked;

	public Statistic() {
	}

	public Statistic(String methodName, long numberOfTimeIsInvoked) {
		this.methodName = methodName;
		this.numberOfTimeIsInvoked = numberOfTimeIsInvoked;
	}

	public String getMethodName() {
		return this.methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public long getNumberOfTimeIsInvoked() {
		return this.numberOfTimeIsInvoked;
	}

	public void setNumberOfTimeIsInvoked(long numberOfTimeIsInvoked) {
		this.numberOfTimeIsInvoked = numberOfTimeIsInvoked;
	}

}
