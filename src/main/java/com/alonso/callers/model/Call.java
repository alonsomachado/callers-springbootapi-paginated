package com.alonso.callers.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

/***
 Caller Number: the phone number of the caller.
 Callee Number: the phone number of the callee.
 Start Timestamp: start timestamp of the call.
 End Timestamp: end timestamp of the call.
 Type: Inbound or Outbound
 ***/

@Data
@Entity
public class Call {

	@Id
	@GeneratedValue
	Long id;
	Integer caller;
	Integer callee;
	Timestamp start;
	Timestamp end;
	TypeCall type;

	public TypeCall getType() {
		return type;
	}

	public void setType(TypeCall type) {
		this.type = type;
	}

	public Timestamp getStart() {
		return start;
	}

	public void setStart(Timestamp start) {
		this.start = start;
	}

	public Timestamp getEnd() {
		return end;
	}

	public void setEnd(Timestamp end) {
		this.end = end;
	}

	public Integer getCaller() {
		return caller;
	}

	public void setCaller(Integer caller) {
		this.caller = caller;
	}

	public Integer getCallee() {
		return callee;
	}

	public void setCallee(Integer callee) {
		this.callee = callee;
	}
}
