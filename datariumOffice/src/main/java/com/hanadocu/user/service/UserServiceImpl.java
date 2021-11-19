package com.hanadocu.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanadocu.user.domain.User;
import com.hanadocu.user.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;

	/** 사용자 등록 */
	@Override
	public void insertUser(User user) {
		userMapper.insertUser(user);
	}
}
