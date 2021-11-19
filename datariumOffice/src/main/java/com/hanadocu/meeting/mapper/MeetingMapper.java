package com.hanadocu.meeting.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hanadocu.meeting.domain.Attendee;
import com.hanadocu.meeting.domain.Meeting;
import com.hanadocu.meeting.domain.MeetingDomain;

@Repository
@Mapper
public interface MeetingMapper {

	/** 해당 달의 모든 회의 예약 상황 */
	public List<Meeting> getAllMeeting(@Param("meetingDomain") MeetingDomain meetingDomain);
	
	/** 해당 회의 상세 정보 */
	public Meeting getMeetingDetail(@Param("meetingId") int meetingId);
	
	/** 해당 회의 참석자 리스트 */
	public List<Attendee> getAttendeeForMeeting(@Param("meetingId") int meetingId);
	
	/** 회의 등록 */
	public int insertMeeting(@Param("meeting") Meeting meeting);

	/** 회의실 중복 체크 */
	public Meeting getMeetingRoomDupCheck(@Param("meeting") Meeting meeting);

	/** 참석자 등록 
	 * @param attendee */
	public int insertAttendee(@Param("attendee") Attendee attendee);

	/** 참석자 ID 가져오기 */
	public int getAttendeeId();

	/** 참석자 삭제 */
	public void deleteAttendee(@Param("attendeeId") int attendeeId);

	/** 회의 수정 */
	public void updateMeeting(@Param("meeting") Meeting meeting);
	
	/** 회의 삭제 */
	public void deleteMeeting(@Param("meetingId") int meetingId);
	
}
