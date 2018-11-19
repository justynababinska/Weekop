package pl.justynababinska.weekop.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import pl.justynababinska.weekop.dao.DAOFactory;
import pl.justynababinska.weekop.dao.UserDAO;
import pl.justynababinska.weekop.model.User;

public class UserService {
	public void addUser(String username, String email, String password) {
		User user = new User();
		user.setUsername(username);
		String md5Pass = encryptPassword(password);
		user.setPassword(md5Pass);
		user.setEmail(email);
		user.setActive(true);
		DAOFactory factory = DAOFactory.getDAOFactory();
		UserDAO userDAO = factory.getUserDAO();
		userDAO.create(user);
	}
	
	private String encryptPassword(String password) {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		digest.update(password.getBytes());
		String md5Password = new BigInteger(1, digest.digest()).toString(16);
		return md5Password;
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
