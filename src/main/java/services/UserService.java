package services;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


import dao.UserDao;
//import dao.UserDaoMock;
import exceptions.InvalidCredentialsException;
import exceptions.UserDoesNotExistException;
import exceptions.UserNameAlreadyExistsException;
import logging.Loggin;
import models.User;

public class UserService {
	
	private UserDao uDao;
	private Loggin logger;
	
	public UserService(UserDao u) {
		this.uDao = u;
	}
	
	public User signUp(String first, String last, String email, String password) throws UserNameAlreadyExistsException{
		User u = new User(first, last, email, password);
		
		try {
			uDao.createUser(u);
			//logger.info("New user has registered");
		} catch(SQLException e) {
			//logger.warn("Username created that already exists in the database");
			throw new UserNameAlreadyExistsException();
		}
		
		return u;
	}
	
	public User signIn(String username, String password) throws UserDoesNotExistException, InvalidCredentialsException{
		
		User u = uDao.getUserByUsername(username);
		
		if(u.getId() == 0) {
			System.out.println("for some reason username is does not exists!");
			//Loggin.logger.warn("User tried loggging in that does not exist");
			throw new UserDoesNotExistException();
		}
		else if(!u.getPassword().equals(password)) {
			System.out.println("for some reason username is does not exists!");
			//Loggin.logger.warn("User tried to login with invalid credentials");
			throw new InvalidCredentialsException();
		}
		else {
			//Loggin.logger.info("User was logged in");
			return u;
		}
		
	}
	public User getUserByUsername(String username) {
		User u = uDao.getUserByUsername(username);
		return u;
	}
	public User getUserById(int id) {
		return uDao.getUserById(id);
	}
}
