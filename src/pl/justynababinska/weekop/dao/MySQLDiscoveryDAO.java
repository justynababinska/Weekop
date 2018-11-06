package pl.justynababinska.weekop.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import pl.justynababinska.weekop.model.Discovery;
import pl.justynababinska.weekop.util.ConnectionProvider;

public class MySQLDiscoveryDAO implements DiscoveryDAO{
	public static final String CREATE =   "INSERT INTO discovery(title, description, url, user_id, date, up_vote, down_vote) "
			  + "VALUES(:title, :description, :url, :user_id, :date, :up_vote, :down_vote);";
	
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
		paramMap.put("date", discovery.getTimestamp());
		paramMap.put("up_vote", discovery.getUpVote());
		paramMap.put("down_vote", discovery.getDownVote());
		SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
		int update = template.update(CREATE, paramSource, keyHolder);
		if (update >0) {
			resultDiscovery.setId((Long)keyHolder.getKey());
		}
		return resultDiscovery;
		
	}

	@Override
	public Discovery read(Long primaryKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Discovery updateObject) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Long key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Discovery> getAll() {
		// TODO Auto-generated method stub
		return null;
	}



}
