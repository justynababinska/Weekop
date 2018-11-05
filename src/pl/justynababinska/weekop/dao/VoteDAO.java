package pl.justynababinska.weekop.dao;

import pl.justynababinska.weekop.model.Vote;

public interface VoteDAO extends GenericDAO<Vote, Long> {
	
	public Vote getVoteByUserIdDiscoveryId(long userId, long discoveryId);
	
}
