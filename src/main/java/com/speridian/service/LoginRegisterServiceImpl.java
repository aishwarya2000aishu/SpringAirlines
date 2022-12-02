package com.speridian.service;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.speridian.entity.Users;
import com.speridian.exception.UsersServiceException;
import com.speridian.repository.UsersRepository;

@Service
public class LoginRegisterServiceImpl implements LoginRegisterService   {

	@Autowired
	private UsersRepository usersRepo;
	
	
	@Override
	public void register(Users user) {
		if(!usersRepo.isUserPresent(user.getEmail())) {
			String tempPassword=user.getPassword();
			user.setPassword(tempPassword);
			usersRepo.addUsers(user);
		}
		else {
			throw new UsersServiceException("User Exists");
		}
	}
	
	@Override
	public  Users login(String email,String password) {
			int id=usersRepo.findByUsernamePassword(email, password);
			Users user=usersRepo.fetchUserById(id);
			return user;
		
	}
	
}
