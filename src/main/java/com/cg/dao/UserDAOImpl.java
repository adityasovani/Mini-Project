package com.cg.dao;

import java.util.HashMap;
import java.util.Map;

import com.cg.bean.User;
import com.cg.exception.UserException;

public class UserDAOImpl implements UserDAO {

	public Map<Integer, User> users = new HashMap<Integer, User>();

	public UserDAOImpl() {
		User user = new User();

		user.setUserId(556);
		user.setUserName("aditya");
		user.setUserPassword("123");
		user.setUserType("admin");

		users.put(user.getUserId(), user);

		User user1 = new User();
		user1.setUserId(745);
		user1.setUserName("maya");
		user1.setUserPassword("123");
		user1.setUserType("manager");

		users.put(user1.getUserId(), user1);
	}

	@Override
	public User login(String userName, String userPassWord) throws UserException {
		boolean found = false;

		User user = new User();

		for (int key : users.keySet()) {
			user = users.get(key);
			if (userName.equals(user.getUserName()) && userPassWord.equals(user.getUserPassword())) {
				found = true;
				break;
			}
		}
		if (!found)
			throw new UserException();

		else
			return user;
	}

}
