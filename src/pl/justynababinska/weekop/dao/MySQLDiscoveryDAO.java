package pl.justynababinska.weekop.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import pl.justynababinska.weekop.model.Discovery;
import pl.justynababinska.weekop.model.User;
import pl.justynababinska.weekop.util.ConnectionProvider;

public class MySQLDiscoveryDAO implements DiscoveryDAO {
	public static final String CREATE = "INSERT INTO discovery(title, description, url, user_id, date, up_vote, down_vote) "
			+ "VALUES(:title, :description, :url, :user_id, :date, :up_vote, :down_vote);";
	public static final String READ = "SELECT user.user_id, username, email, is_active, password, discovery_id, title, "
			+ "description, url, date, up_vote, down_vote "
			+ "FROM discovery LEFT JOIN user ON discovery.user_id=user.user_id WHERE discovery_id=:discovery_id;";
	public static final String UPDATE = "UPDATE discovery SET title = :title, description = :description, url = :url, user_id = :user_id, "
			+ "date = :date, up_vote = :up_vote, down_vote = :down_vote WHERE discovery_id = :discovery_id;";
	public static final String READ_ALL = "SELECT user.user_id, username, email, password, is_active, "
			+ "discovery_id, title, description, url, date, up_vote, down_vote "
			+ "FROM discovery LEFT JOIN user ON discovery.user_id = user.user_id;";
	

	private NamedParameterJdbcTemplate template;

	public MySQLDiscoveryDAO() {
		template = new NamedParameterJdbcTemplate(ConnectionProvider.getDSInstance());
	}

	@Override
	public Discovery create(Discovery discovery) {
		Discovery resultDiscovery = new Discovery(discovery);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("title", discovery.getTitle());
		paramMap.put("url", discovery.getUrl());
		paramMap.put("description", discovery.getDescription());
		paramMap.put("user_id", discovery.getUser().getId());
		paramMap.put("date", new Timestamp(new Date().getTime()));
		paramMap.put("up_vote", discovery.getUpVote());
		paramMap.put("down_vote", discovery.getDownVote());
		SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
		int update = template.update(CREATE, paramSource, keyHolder);
		if (update > 0) {
			resultDiscovery.setId(keyHolder.getKey().longValue());
		}
		return resultDiscovery;

	}

	@Override
	public Discovery read(Long primaryKey) {
		SqlParameterSource paramSource = new MapSqlParameterSource("discovery_id", primaryKey);
		Discovery discovery = template.queryForObject(READ, paramSource, new DiscoveryRowMapper());
		return discovery;
	}

	@Override
	public boolean update(Discovery discovery) {
		boolean result = false;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("discovery_id", discovery.getId());
		paramMap.put("title", discovery.getTitle());
		paramMap.put("description", discovery.getDescription());
		paramMap.put("url", discovery.getUrl());
		paramMap.put("user_id", discovery.getUser().getId());
		paramMap.put("date", discovery.getTimestamp());
		paramMap.put("up_vote", discovery.getUpVote());
		paramMap.put("down_vote", discovery.getDownVote());
		SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
		int update = template.update(UPDATE, paramSource);
		if (update > 0) {
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
	public List<Discovery> getAll() {
		List<Discovery> list = template.query(READ_ALL, new DiscoveryRowMapper());
		return list;
	}

	private class DiscoveryRowMapper implements RowMapper<Discovery> {

		@Override
		public Discovery mapRow(ResultSet resultSet, int row) throws SQLException {
			Discovery resultDiscovery = new Discovery();
			resultDiscovery.setId(resultSet.getLong("discovery_id"));
			resultDiscovery.setTitle(resultSet.getString("title"));
			resultDiscovery.setDescription(resultSet.getString("description"));
			resultDiscovery.setUrl(resultSet.getString("url"));
			User user = new User();
			user.setId(resultSet.getLong("user_id"));
			user.setUsername(resultSet.getString("username"));
			user.setEmail(resultSet.getString("email"));
			user.setPassword(resultSet.getString("password"));
			user.setActive(resultSet.getBoolean("is_active"));
			resultDiscovery.setUser(user);
			resultDiscovery.setTimestamp(resultSet.getTimestamp("date"));
			resultDiscovery.setUpVote(resultSet.getInt("up_vote"));
			resultDiscovery.setDownVote(resultSet.getInt("down_vote"));
			return resultDiscovery;
		}

	}
}
