package pl.justynababinska.weekop.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import pl.justynababinska.weekop.model.Vote;
import pl.justynababinska.weekop.model.VoteType;
import pl.justynababinska.weekop.util.ConnectionProvider;

public class MySQLVoteDAO implements VoteDAO {
	public static final String CREATE = "INSERT INTO vote(discovery_id, user_id, date, type) "
			+ "VALUE(:discovery_id, :user_id, :date, :type);";
	public static final String READ = "SELECT vote_id, discovery_id, user_id, date, type "
			+ "FROM vote WHERE vote_id=:vote_id;";
	public static final String READ_BY_DISCOVERY_ID_AND_USER_ID = "SELECT vote_id, discovery_id, user_id, date, type "
			+ "FROM vote WHERE discovery_id=:discovery_id AND user_id=:user_id;";
	private static final String UPDATE_VOTE = "UPDATE vote SET date=:date, type=:type WHERE vote_id=:vote_id;";

	NamedParameterJdbcTemplate template;

	public MySQLVoteDAO() {
		template = new NamedParameterJdbcTemplate(ConnectionProvider.getDSInstance());
	}

	@Override
	public Vote create(Vote vote) {
		Vote copyVote = new Vote(vote);
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("discovery_id", copyVote.getDiscoveryId());
		System.out.println(copyVote.getDiscoveryId());
		paramMap.put("user_id", copyVote.getUserId());
		System.out.println(copyVote.getId());
		paramMap.put("date", new Timestamp(new Date().getTime()));
		System.out.println(new Timestamp(new Date().getTime()));
		paramMap.put("type", copyVote.getVoteType().toString());
		System.out.println(copyVote.getVoteType().toString());
		KeyHolder keyHolder = new GeneratedKeyHolder();
		SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
		int update = template.update(CREATE, paramSource, keyHolder);
		if (update > 0) {
			copyVote.setId(keyHolder.getKey().longValue());
		}
		return copyVote;
	}

	@Override
	public Vote read(Long primaryKey) {
		SqlParameterSource paramSource = new MapSqlParameterSource("vote_id", primaryKey);
		Vote vote = template.queryForObject(READ, paramSource, new VoteRowMapper());
		return vote;
	}

	@Override
	public boolean update(Vote vote) {
		boolean result = false;
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("date", vote.getDate());
		paramMap.put("type", vote.getVoteType().toString());
		paramMap.put("vote_id", vote.getId());
		SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
		int update = template.update(UPDATE_VOTE, paramSource);
		if(update > 0) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean delete(Long key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Vote> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vote getVoteByUserIdDiscoveryId(long userId, long discoveryId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("user_id", userId);
		paramMap.put("discovery_id", discoveryId);
		SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
		Vote vote = null;
		try {
			vote = template.queryForObject(READ_BY_DISCOVERY_ID_AND_USER_ID, paramSource, new VoteRowMapper());
		} catch (EmptyResultDataAccessException ex) {
		}
		return vote;
	}

	private class VoteRowMapper implements RowMapper<Vote> {

		@Override
		public Vote mapRow(ResultSet resultSet, int row) throws SQLException {
			Vote vote = new Vote();
			vote.setId(resultSet.getLong("vote_id"));
			vote.setDiscoveryId(resultSet.getLong("discovery_id"));
			vote.setUserId(resultSet.getLong("user_id"));
			vote.setDate(resultSet.getTimestamp("date"));
			vote.setVoteType(VoteType.valueOf(resultSet.getString("type")));
			return vote;
		}
	}
}
