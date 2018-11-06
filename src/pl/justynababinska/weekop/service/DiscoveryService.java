package pl.justynababinska.weekop.service;

import java.sql.Timestamp;
import java.util.Date;

import pl.justynababinska.weekop.dao.DAOFactory;
import pl.justynababinska.weekop.dao.DiscoveryDAO;
import pl.justynababinska.weekop.dao.UserDAO;
import pl.justynababinska.weekop.dao.VoteDAO;
import pl.justynababinska.weekop.model.Discovery;
import pl.justynababinska.weekop.model.User;

public class DiscoveryService {

	public void addDiscovery(String title, String description, String url, User user) {
		Discovery discovery = createDiscovery(title, description, url, user);
		DAOFactory factory = DAOFactory.getDAOFactory();
		DiscoveryDAO discoveryDAO = factory.getDiscoveryDAO();
		discoveryDAO.create(discovery);
	}

	private static Discovery createDiscovery(String title, String description, String url, User user) {
		Discovery discovery = new Discovery();
		discovery.setTitle(title);
		discovery.setDescription(description);
		discovery.setUrl(url);
		User userCopy = new User(user);
		discovery.setUser(userCopy);
		discovery.setTimestamp(new Timestamp(new Date().getTime()));
		return discovery;
	}

	@Override
	public VoteDAO getVoteDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDAO getUserDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DiscoveryDAO getDiscoveryDAO() {
		// TODO Auto-generated method stub
		return null;
	}
}
