package com.hanadocu.meeting.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanadocu.common.code.ResponseMessage;
import com.hanadocu.common.code.StatusCode;
import com.hanadocu.common.config.CommonResponse;
import com.hanadocu.meeting.domain.Attendee;
import com.hanadocu.meeting.domain.Meeting;
import com.hanadocu.meeting.domain.MeetingDomain;
import com.hanadocu.meeting.mapper.MeetingMapper;
import com.hanadocu.user.domain.User;
import com.hanadocu.user.domain.UserDomain;
import com.hanadocu.user.mapper.UserMapper;
import com.hanadocu.util.DateUtil;
import com.hanadocu.util.StringUtil;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MeetingServiceImpl implements MeetingService{

	@Autowired
	private MeetingMapper meetingMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	/** 해당 년 월에 대한 모든 회의 리스트 */
	@Override
	public List<Meeting> getAllMeeting(MeetingDomain meetingDomain) {
		/* 요청 json
		  {
		  	 "srchYear" : "2021",
		  	 "srchMonth" : "11"
		  }
		 */
		
		if(StringUtil.checkEmptyString(meetingDomain.getSrchYear())) {
			
			meetingDomain.setSrchYear(DateUtil.getYear());
		}
		if(StringUtil.checkEmptyString(meetingDomain.getSrchMonth())) {
			meetingDomain.setSrchMonth (DateUtil.getMonth());
		}
		
		return meetingMapper.getAllMeeting(meetingDomain);
	}
	
	@Override
	public MeetingDomain getMeetingDetail(int meetingId) {
		
		MeetingDomain meetingDomain = new MeetingDomain(); 
				
		Meeting meeting = meetingMapper.getMeetingDetail(meetingId); // 회의정보
		
		if(meeting != null) {
			
			meetingDomain.setMeeting(meeting); // 회의정보 세팅
			
			int attendeeId = Integer.parseInt(String.valueOf(meeting.getAttendeeId()));
			List<Attendee> attendeeInfo = meetingMapper.getAttendeeForMeeting(attendeeId); // 참석자 리스트
			
			if(!attendeeInfo.isEmpty()) {
				meetingDomain.setAttendee(attendeeInfo); // 회의 참석자 리스트 세팅
			}
		}
		
		return meetingDomain;
	}

	/** 회의 등록 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insertMeeting(MeetingDomain meetingDomain) {
		/* 요청 json
		  {
		  	 "meeting": {
		  	 	"meetingRoom": "",
		  	 	"meetingDate": "YYYYMMD",
		    	"meetingStartTime": "HH:SS",
		    	"meetingEndTime": "HH:SS",
		    	"meetingSubject": "",
		    	"outsideAttendee": "",
		    	"meetingBigo": ""
		    },
		    "attendee":[ 
		    	{"userNo": "2230"},
		    	{"userNo": "2512"}
		    ]	
		  }   
		 */
		
		List<Attendee> attendeeList = null;
		Meeting meeting = null;
		int attendeeId = 0;
		//
		
		attendeeList = meetingDomain.getAttendee();
		attendeeId = meetingMapper.getAttendeeId();
		for(Attendee attendee:attendeeList) {
			attendee.setAttendeeId(attendeeId);
			meetingMapper.insertAttendee(attendee);
		}
		
		meeting = meetingDomain.getMeeting();
		meeting.setAttendeeId(attendeeId);
		meeting.setRegUser("2230");
		meetingMapper.insertMeeting(meeting);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateMeeting(MeetingDomain meetingDomain) {
		/* 요청 json
		  {
		  	 "meeting": {
		  	 	"meetingRoom": "",
		  	 	"meetingDate": "YYYYMMD",
		    	"meetingStartTime": "HH:SS",
		    	"meetingEndTime": "HH:SS",
		    	"meetingSubject": "",
		    	"attendeeId":,
		    	"outsideAttendee": "",
		    	"meetingBigo": ""
		    },
		    "attendee":[ 
		    	{"userNo": "2230"},
		    	{"userNo": "2512"}
		    ]	
		  }   
		 */
		List<Attendee> attendeeList = null;
		Meeting meeting = null;
		int attendeeId = 0;
		//
		
		meeting = meetingDomain.getMeeting(); // 회의 정보
		attendeeList = meetingDomain.getAttendee(); // 참석자 정보
		attendeeId = meeting.getAttendeeId(); // 기존 등록된 참석자 ID
		
		// 참석자 삭제 후 재등록
		meetingMapper.deleteAttendee(attendeeId);
		for(Attendee attendee:attendeeList) {
			attendee.setAttendeeId(attendeeId);
			meetingMapper.insertAttendee(attendee);
		}
		
		// 회의 정보 수정
		meeting.setUpdateUser("2230");
		meetingMapper.updateMeeting(meeting);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteMeeting(int meetingId) {
		Meeting meeting = null;
		int attendeeId = 0;
		
		// 회의 정보
		meeting = meetingMapper.getMeetingDetail(meetingId);
		attendeeId = meeting.getAttendeeId();
		
		// 참석자 삭제
		meetingMapper.deleteAttendee(attendeeId);
		// 회의 정보 삭제
		meetingMapper.deleteMeeting(meetingId);
		
	}

	/** 회의실 예약 중복 체크 */
	@Override
	public Meeting getMeetingRoomDupCheck(Meeting meeting) {
		/* 요청 json
		{
	  	 	"meetingRoom": "",
	  	 	"meetingDate": "YYYYMMD",
	    	"meetingStartTime": "HH:SS",
	    	"meetingEndTime": "HH:SS",
	    	"meetingSubject": "",
	    	"outsideAttendee": "",
	    	"meetingBigo": ""
	    }
		*/
		return meetingMapper.getMeetingRoomDupCheck(meeting);
	}
	
	/** 모든 참석자 리스트 */
	@Override
	public List<UserDomain> getAllUserForAttendee() {
		List<UserDomain> list = new ArrayList<>();
		UserDomain userDomain = null;
		User userInfo = null;
		
		List<Map<String,Object>> users = null; // 
		for(Map<String, Object> user:users) {
			userDomain = new UserDomain();
			userInfo = userMapper.getUserInfo((String) user.get("userNo"));
			userDomain.setUserName((String) user.get("userName"));
			userDomain.setUserInfo(userInfo);
			
			list.add(userDomain);
		}
		
		return list;
	}
	
}
