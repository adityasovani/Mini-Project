package com.cg.user;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.cg.bean.User;
import com.cg.exception.UserException;
import com.cg.service.UserService;
import com.cg.service.UserServiceImpl;

public class UserTest {

	private UserService userService;

	@Before
	// Initialize
	public void initialize() {
		userService = new UserServiceImpl();
	}

	@Test
	// Test login functionality
	public void testLoginAdmin() throws UserException {

		User user;
		user = userService.login("aditya", "123");

		assertEquals(user.getUserType(), "admin");
	}

	@Test
	// Test user type admin
	public void testUserTypeAdmin() throws UserException {

		assertEquals(userService.login("aditya", "123").getUserType(), "admin");
	}

	@Test
	// Test user type manager
	public void testuserTypeManager() throws UserException {
		assertEquals(userService.login("maya", "123").getUserType(), "manager");
	}

	@Test(expected = UserException.class)
	// Test exception for wrong credential
	public void testUserException() throws UserException {
		userService.login("aditya", "55");
	}

}
