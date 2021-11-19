package com.hanadocu.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hanadocu.meeting.domain.Meeting;
import com.hanadocu.user.domain.User;
import com.hanadocu.user.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.GET, path="/users")
	public void getAllUser() {
		
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/users/{userId}")
	public void getUserDetail(@PathVariable int userId) {
		
	}
	
	/** 사용자 등록 */
	@RequestMapping(method = RequestMethod.POST, path="/users")
	public void insertUser(@RequestBody User user) {
		
		userService.insertUser(user);
	}
	
	@RequestMapping(method = RequestMethod.PUT, path="/users/{userId}")
	public void updateUser(@RequestBody User user) {
		
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path="/users/{userId}")
	public void deleteUser(@PathVariable int userId) {
		
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
