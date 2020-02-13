package com.cg.service;

import com.cg.bean.User;
import com.cg.dao.UserDAO;
import com.cg.dao.UserDAOImpl;
import com.cg.exception.UserException;

public class UserServiceImpl implements UserService{

	UserDAO userDAO = new UserDAOImpl();
	
	@Override
	public User login(String userName, String passWord) throws UserException {
		return userDAO.login(userName, passWord);
	}
	
}
