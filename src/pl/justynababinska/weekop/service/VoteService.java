package pl.justynababinska.weekop.service;

import java.sql.Timestamp;
import java.util.Date;

import pl.justynababinska.weekop.dao.DAOFactory;
import pl.justynababinska.weekop.dao.VoteDAO;
import pl.justynababinska.weekop.model.Vote;
import pl.justynababinska.weekop.model.VoteType;

public class VoteService{
	
	public Vote addVote(long discoveryId, long userId, VoteType voteType) {
		Vote vote = new Vote();
		vote.setDiscoveryId(discoveryId);
		vote.setUserId(userId);
		vote.setDate(new Timestamp(new Date().getTime()));
		vote.setVoteType(voteType);
		DAOFactory daoFactory = DAOFactory.getDAOFactory();
		VoteDAO voteDao = daoFactory.getVoteDAO();
		vote  = voteDao.create(vote);
		return vote;
	}

	public Vote updateVote(long discoveryId, long userId, VoteType voteType) {
		DAOFactory daoFactory = DAOFactory.getDAOFactory();
		VoteDAO voteDao = daoFactory.getVoteDAO();
		Vote vote = voteDao.getVoteByUserIdDiscoveryId(userId, discoveryId);
		if(vote != null) {
			vote.setVoteType(voteType);
			voteDao.update(vote);
		}
		return vote;
	}

	public Vote addOrUpdateVote(long discoveryId, long userId, VoteType voteType) {
		DAOFactory factory = DAOFactory.getDAOFactory();
		VoteDAO voteDao = factory.getVoteDAO();
		Vote vote = voteDao.getVoteByUserIdDiscoveryId(userId, discoveryId);
		Vote resultVote = null;
		if(vote == null) {
			resultVote = addVote(discoveryId, userId, voteType);
		} else {
			resultVote = updateVote(discoveryId, userId, voteType);
		}
		return resultVote;
	}


	public Vote getVoteByUserIdDiscoveryId(long userId, long discoveryId) {
		DAOFactory factory = DAOFactory.getDAOFactory();
		VoteDAO voteDao = factory.getVoteDAO();
		Vote vote = voteDao.getVoteByUserIdDiscoveryId(userId, discoveryId);
		return vote;
	}

}