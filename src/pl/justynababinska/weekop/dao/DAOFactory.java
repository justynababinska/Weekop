package pl.justynababinska.weekop.dao;

import pl.justynababinska.weekop.exception.NoSuchDbTypeException;

public abstract class DAOFactory {
	public static final int MY_SQL_DAO_FACTORY = 1;

	public abstract UserDAO getUserDAO();

	public abstract DiscoveryDAO getDiscoveryDAO();

	public abstract VoteDAO getVoteDAO();

	public static DAOFactory getDAOFactory() {
		DAOFactory factory = null;
		try {
			factory = getDAOFactory(MY_SQL_DAO_FACTORY);
		} catch (NoSuchDbTypeException ex) {
			ex.printStackTrace();
		}
		return factory;
	}

	private static DAOFactory getDAOFactory(int factoryType) throws NoSuchDbTypeException {

		switch (factoryType) {
		case MY_SQL_DAO_FACTORY:
			return new MySQLDAOFactory();
		default:
			throw new NoSuchDbTypeException();
		}
	}
}
