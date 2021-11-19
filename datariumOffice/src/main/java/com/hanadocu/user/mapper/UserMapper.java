package com.hanadocu.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.hanadocu.user.domain.User;

@Mapper
public interface UserMapper {

	/** 해당 사용자 정보 */
	public User getUserInfo(String userNo);

	/** 사용자 등록 */
	public void insertUser(User user);
}
