package com.speridian.service;

import com.speridian.entity.Admin;

public interface AdminLoginService {

	Admin login(String email, String password);

}