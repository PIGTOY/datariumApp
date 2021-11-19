package com.hanadocu.meeting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hanadocu.common.code.ResponseMessage;
import com.hanadocu.common.code.StatusCode;
import com.hanadocu.common.config.CommonResponse;
import com.hanadocu.common.domain.ResultDomain;
import com.hanadocu.meeting.domain.Meeting;
import com.hanadocu.meeting.domain.MeetingDomain;
import com.hanadocu.meeting.service.MeetingService;

import lombok.extern.slf4j.Slf4j;

@RestController
public class MeetingController {
	
	@Autowired
	MeetingService meetingService;
	
	@RequestMapping(method = RequestMethod.GET, path="/meetings")
	public List<Meeting> getAllMeeting(@RequestBody MeetingDomain meetingDomain) {
		// 모든 회의 리스트
		/* 응답 json
		   [
		   	{
				"meetingId":2,
				"meetingRoom":"ROOM3",
				"meetingDate":"20211118",
				"meetingStartTime":"11:00",
				"meetingEndTime":"12:00",
				"meetingTime":"11:00~12:00",
				"meetingAlarmTime":"10:50",
				"meetingSubject":"팀주간회의",
				"attendeeId":3,"outsideAttendee":"",
				"meetingBigo":"",
				"regUser":"2230",
				"regDatetime":"2021-11-18 16:40:23.0",
				"updateUser":null,
				"updateDatetime":"0000-00-00 00:00:00"
				}
		   ]
		 */
		return meetingService.getAllMeeting(meetingDomain);
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/meetings/{meetingId}")
	public MeetingDomain getMeetingDetail(@PathVariable int meetingId) {
		// 회의 정보
		return meetingService.getMeetingDetail(meetingId);
	}
	
	@RequestMapping(method = RequestMethod.POST, path="/meetings")
	public CommonResponse insertMeeting(@RequestBody MeetingDomain meetingDomain) {
		// 회의 등록
		int statusCode = 0;
		String message = null;
		
		try {
			meetingService.insertMeeting(meetingDomain);
			statusCode = StatusCode.OK;
			message = ResponseMessage.INSERT_SUCCESS;
		} catch (Exception e) {
			
			e.printStackTrace();
			
			statusCode = StatusCode.INTERNAL_SERVER_ERROR;
			message = ResponseMessage.INSERT_FAIL;
		}
		
		return CommonResponse.res(statusCode, message);
		
	}
	
	@RequestMapping(method = RequestMethod.PUT, path="/meetings/{meetingId}")
	public CommonResponse updateMeeting(@RequestBody MeetingDomain meetingDomain) {
		// 회의 수정
		int statusCode = 0;
		String message = null;
		
		try {
			meetingService.updateMeeting(meetingDomain);
			statusCode = StatusCode.OK;
			message = ResponseMessage.UPDATE_SUCCESS;
		} catch (Exception e) {
			
			e.printStackTrace();
			
			statusCode = StatusCode.INTERNAL_SERVER_ERROR;
			message = ResponseMessage.UPDATE_FAIL;
		}
		
		return CommonResponse.res(statusCode, message);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path="/meetings/{meetingId}")
	public CommonResponse deleteMeeting(@PathVariable int meetingId) {
		// 회의 삭제
		int statusCode = 0;
		String message = null;
		
		try {
			meetingService.deleteMeeting(meetingId);
			statusCode = StatusCode.OK;
			message = ResponseMessage.DELETE_SUCCESS;
		} catch (Exception e) {
			
			e.printStackTrace();
			
			statusCode = StatusCode.INTERNAL_SERVER_ERROR;
			message = ResponseMessage.DELETE_FAIL;
		}
		
		return CommonResponse.res(statusCode, message);
	}
	
	/**
	 * 회의실 중복 확인
	 * @param meeting
	 * @return meeting(meeting_subject)
	 */
	@RequestMapping(method = RequestMethod.POST, path="/meetings/duplicate")
	public ResultDomain getMeetingRoomDupCheck(@RequestBody Meeting meeting) {
		/* 응답 json
		{
			"result": "true",
			"message": ""
		}
		*/
		
		ResultDomain rd = new ResultDomain();
		
		Meeting dupMeeting = meetingService.getMeetingRoomDupCheck(meeting);
		if(dupMeeting != null) { // 중복되는 회의실 예약이 있을 경우
			rd.setResult("true");
			rd.setMessage("해당 시간에 ["+dupMeeting.getMeetingSubject()+"] 회의가 예약되어 있습니다."); 
		}else {
			rd.setResult("false");
		}
		
		return rd;
	}
}


/**

GET
/api/user : Get the list of all users
/api/user/{id} : Get the detail of a user
/api/user/{id}/task : Get the list of all the task assigned

POST
/api/user : Greate a new user
 
PUT
/api/user/{id} : Update a user

DELETE
/api/user/{id} : Delete a user
 * 
 */
