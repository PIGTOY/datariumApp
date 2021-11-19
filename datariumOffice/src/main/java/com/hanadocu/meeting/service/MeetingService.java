package com.hanadocu.meeting.service;

import java.util.List;

import com.hanadocu.common.config.CommonResponse;
import com.hanadocu.meeting.domain.Meeting;
import com.hanadocu.meeting.domain.MeetingDomain;
import com.hanadocu.user.domain.UserDomain;

public interface MeetingService {

	public List<Meeting> getAllMeeting(MeetingDomain meetingDomain);
	public MeetingDomain getMeetingDetail(int meetingId);
	public void insertMeeting(MeetingDomain meeting) throws Exception;
	public void updateMeeting(MeetingDomain meetingDomain);
	public void deleteMeeting(int meetingId);
	public Meeting getMeetingRoomDupCheck(Meeting meeting);
	public List<UserDomain> getAllUserForAttendee();
	
}
