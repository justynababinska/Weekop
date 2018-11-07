package pl.justynababinska.weekop.service;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import pl.justynababinska.weekop.dao.DAOFactory;
import pl.justynababinska.weekop.dao.DiscoveryDAO;
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

	public List<Discovery> getAllDiscoveries() {
		return getAllDiscoveries(null);
	}

	public List<Discovery> getAllDiscoveries(Comparator<Discovery> comparator) {
		DAOFactory factory = DAOFactory.getDAOFactory();
		DiscoveryDAO discoveryDao = factory.getDiscoveryDAO();
		List<Discovery> discoveries = discoveryDao.getAll();
		if (comparator != null && discoveries != null) {
			discoveries.sort(comparator);
		}
		return discoveries;
	}
}
