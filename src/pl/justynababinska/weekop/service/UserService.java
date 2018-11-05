package pl.justynababinska.weekop.service;

import pl.justynababinska.weekop.dao.DAOFactory;
import pl.justynababinska.weekop.dao.UserDAO;
import pl.justynababinska.weekop.model.User;

public class UserService {
	public void addUser(String username, String email, String password) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setActive(true);
		DAOFactory factory = DAOFactory.getDAOFactory();
		UserDAO userDAO = factory.getUserDAO();
		userDAO.create(user);
	}
	
	public User getUserById(long userId) {
		User user = new User();
		DAOFactory factory = DAOFactory.getDAOFactory();
		UserDAO userDAO = factory.getUserDAO();
		user = userDAO.read(userId);
		return user;
	}
	
	public User getUserByUsername(String username) {
		User user = new User();
		DAOFactory factory = DAOFactory.getDAOFactory();
		UserDAO userDAO = factory.getUserDAO();
		user = userDAO.getUserByUsername(username);
		return user;
	}
}
