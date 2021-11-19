package com.hanadocu.meeting.domain;

import lombok.Data;

@Data
public class Attendee {

	/** 참석자 id */	private int attendeeId;
	/** 참석자 번호 */ 	private String userNo;
}
