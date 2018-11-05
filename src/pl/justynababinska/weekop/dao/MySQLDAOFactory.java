package pl.justynababinska.weekop.dao;

public class MySQLDAOFactory extends DAOFactory{

	@Override
	public UserDAO getUserDAO() {
		return new MySQLUserDAO();
	}

	@Override
	public DiscoverDAO getDiscoveryDAO() {
		return new MySQLDiscoverDAO();
	}

	@Override
	public VoteDAO getVoteDAO() {
		return  new MySQLVoteDAO();
	}

}
