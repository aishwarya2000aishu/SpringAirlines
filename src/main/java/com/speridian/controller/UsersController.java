package com.speridian.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.speridian.dto.LoginDto;
import com.speridian.entity.Admin;
import com.speridian.entity.Users;
import com.speridian.exception.UsersServiceException;
import com.speridian.service.AdminLoginService;
import com.speridian.service.LoginRegisterServiceImpl;

@RestController
@CrossOrigin
public class UsersController {
	@Autowired
	private AdminLoginService adminLoginService;

	@Autowired
	private  LoginRegisterServiceImpl loginRegisterService;
		
	@PostMapping("/register")
	public Status register(@RequestBody Users users) {
		try {
			loginRegisterService.register(users);
			
			
			Status status = new Status();
			status.setStatus(Status.StatusType.SUCCESS);
			status.setMessage("Registration Successful!");
			return status;
		}
		catch(UsersServiceException e) {
			Status status = new Status();
			status.setStatus(Status.StatusType.FAILURE);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	@PostMapping(path="/login",consumes = "application/json")
	public LoginStatus login(@RequestBody LoginDto loginDto  ) {
		try {

			int flag=0;

			try {
				String tempPassword=loginDto.getPassword();
				loginDto.setPassword(tempPassword);
				Users user = loginRegisterService.login(loginDto.getEmail(),loginDto.getPassword());
				LoginStatus loginStatus = new LoginStatus();
				loginStatus.setUserType(LoginStatus.UserType.USER);
				loginStatus.setStatus(Status.StatusType.SUCCESS);
				loginStatus.setMessage("Login Successful!");
				loginStatus.setUserId(user.getId());
				loginStatus.setName(user.getFullName());
				return loginStatus;			

			}
			catch (Exception e) {
				flag=1;
			}
			if(flag==1) {
				Admin admin= adminLoginService.login(loginDto.getEmail(),loginDto.getPassword());
				LoginStatus loginStatus = new LoginStatus();
				loginStatus.setUserType(LoginStatus.UserType.ADMIN);
				loginStatus.setStatus(Status.StatusType.SUCCESS);
				loginStatus.setMessage("Login Successful!");
				loginStatus.setUserId(admin.getId());
				loginStatus.setName(admin.getFullName());
				return loginStatus;			
			
			}
			return null;
		}
		catch(Exception e) { 
			LoginStatus status = new LoginStatus();

			if (e instanceof UsersServiceException) {
				status.setMessage(e.getMessage());
			} else {
				status.setMessage(e.getMessage());
			}

			status.setStatus(Status.StatusType.FAILURE);

			return status;
		}
	}
	
	public static class LoginStatus extends Status{
		private int userId;
		private String name;
		private  UserType userType;
		
		public static enum UserType{
			ADMIN,USER;
		}

		public UserType getUserType() {
			return userType;
		}
		public void setUserType(UserType userType) {
			this.userType = userType;
		}
		public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId =userId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		
		
		
	}
	
	
	public static class Status {
		private StatusType status;
		private String message;
		
		
		public static enum StatusType{
			SUCCESS, FAILURE;
		}

		public StatusType getStatus() {
			return status;
		}

		public void setStatus(StatusType status) {
			this.status = status;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
		
	}

}
	


