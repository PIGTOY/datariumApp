package com.hanadocu.meeting.domain;

import java.util.List;

import lombok.Data;

@Data
public class MeetingDomain {
	
	/** 회의 정보 */ 	private Meeting meeting;
	/** 내부참석자 */ 	private List<Attendee> attendee; // 알림용
	
	// ----
	/** 검색 년도 */	private String srchYear;
	/** 검색 달(월) */	private String srchMonth;
	
}
