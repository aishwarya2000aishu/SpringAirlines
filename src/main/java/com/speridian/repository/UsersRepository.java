package com.speridian.repository;

import java.util.List;

import javax.transaction.Transactional;

import com.speridian.entity.Users;

public interface UsersRepository {

	void addUsers(Users users);

	Users fetchUserById(int id);

	List<Users> fetchAllUsers();

	boolean isUserPresent(String email);

	int findByUsernamePassword(String email, String password);

}