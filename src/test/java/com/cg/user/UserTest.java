package com.cg.user;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cg.bean.User;
import com.cg.dao.UserDAO;
import com.cg.dao.UserDAOImpl;
import com.cg.exception.UserException;

public class UserTest {

	@Test
	public void testLoginAdmin() throws UserException {
		UserDAO userDAO = new UserDAOImpl();
		User user;

		user = userDAO.login("aditya", "123");

		assertEquals(user.getUserType(), "admin");
	}
	
	@Test
	public void testLoginManager() throws UserException {
		UserDAO userDAO = new UserDAOImpl();
		User user;

		user = userDAO.login("maya", "123");

		assertEquals(user.getUserType(), "manager");
	}
	
	@Test(expected = UserException.class)
	public void testUserException() throws UserException {
		UserDAO userDAO = new UserDAOImpl();
		User user;

		user = userDAO.login("aditya", "2");

		assertEquals(user.getUserType(), "admin");
	}

}
