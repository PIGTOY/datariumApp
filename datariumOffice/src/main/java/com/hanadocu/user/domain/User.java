package com.hanadocu.user.domain;

import lombok.Data;

@Data
public class User {
/* Entity*/	

	/** 로그인 회원 번호 */	private int userId;
	/** 사용자 이름 */		private String userName;
	/** 영문명 */			private String englishName;
	/** 승인 여부 */		private String acceptYn;
	/** 사용 여부 */		private String userYn;
	/** 등록 일시 */		private String regDatetime;
	/** 수정 일시 */		private String updateDatetime;
	
}
