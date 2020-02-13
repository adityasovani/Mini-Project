package com.cg.service;

import com.cg.bean.User;
import com.cg.exception.UserException;

public interface UserService {
	public User login(String userName, String passWord) throws UserException;
}
