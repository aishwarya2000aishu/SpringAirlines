package com.speridian.service;

import com.speridian.entity.Users;

public interface LoginRegisterService {

	void register(Users user);

	Users login(String email, String password);

}