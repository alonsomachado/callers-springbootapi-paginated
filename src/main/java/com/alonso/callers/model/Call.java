package com.alonso.callers.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

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
	Integer calle;
	LocalDateTime start;
	LocalDateTime end;
	String type;
}
