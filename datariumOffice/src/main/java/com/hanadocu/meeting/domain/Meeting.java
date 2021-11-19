package com.hanadocu.meeting.domain;

import lombok.Data;

@Data
public class Meeting {
/* Entity */	
	
	/** id */		private int meetingId;
	/** 회의실 */ 	private String meetingRoom;
	/** 날짜 */		private String meetingDate;
	/** 시작시간 */	private String meetingStartTime;
	/** 종료시간 */	private String meetingEndTime;
	/** 회의시간 */	private String meetingTime;
	/** 알림 시간 */ 	private String meetingAlarmTime;
	/** 주제 */		private String meetingSubject;
	/** 참석자 ID */ 	private int attendeeId;
	/** 외부 참석자 */ private String outsideAttendee;
	/** 비고 */		private String meetingBigo;
	/** 등록한 사람 */	private String regUser;
	/** 등록 시간 */ 	private String regDatetime;
	/** 수정한 사람 */	private String updateUser;
	/** 수정 시간 */	private String updateDatetime;
	
}
