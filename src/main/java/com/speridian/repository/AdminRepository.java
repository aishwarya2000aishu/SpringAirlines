package com.speridian.repository;

import com.speridian.entity.Admin;

public interface AdminRepository {

	Admin fetchAdminById(int id);

	int findByAdminUsernamePassword(String email, String password);

}