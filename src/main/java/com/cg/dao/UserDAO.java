package com.cg.dao;

import com.cg.bean.User;
import com.cg.exception.UserException;

public interface UserDAO {
	public User login(String userName, String passWord) throws UserException;
}
