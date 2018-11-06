package pl.justynababinska.weekop.dao;

public class MySQLDAOFactory extends DAOFactory{

	@Override
	public UserDAO getUserDAO() {
		return new MySQLUserDAO();
	}

	@Override
	public DiscoveryDAO getDiscoveryDAO() {
		return new MySQLDiscoveryDAO();
	}

	@Override
	public VoteDAO getVoteDAO() {
		return  new MySQLVoteDAO();
	}

}
