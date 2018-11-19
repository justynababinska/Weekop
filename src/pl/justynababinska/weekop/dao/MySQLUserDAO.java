package pl.justynababinska.weekop.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import pl.justynababinska.weekop.model.User;
import pl.justynababinska.weekop.util.ConnectionProvider;

public class MySQLUserDAO implements UserDAO {
	private static final String CREATE = "INSERT INTO user(username, email, password, is_active) VALUES(:username, :email, :password, :active)";
	private static final String READ = "SELECT user_id, username, email, password, is_active FROM user WHERE user_id=:id";
	private static final String READ_BY_USERNAME = "SELECT user_id, username, email, password, is_active FROM user WHERE username = :username";;

	private NamedParameterJdbcTemplate template;

	public MySQLUserDAO() {
		template = new NamedParameterJdbcTemplate(ConnectionProvider.getDSInstance());
	}

	@Override
	public User create(User user) {
		User resultUser = new User(user);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		BeanPropertySqlParameterSource beanParamSource = new BeanPropertySqlParameterSource(user);
		int i = template.update(CREATE, beanParamSource, keyHolder);
		if (i > 0) {
			resultUser.setId(keyHolder.getKey().longValue());
			setRole(resultUser);

		}
		return resultUser;
	}

	private void setRole(User user) {
		final String SET_ROLE = "INSERT INTO user_role(username) VALUES(:username)";
		BeanPropertySqlParameterSource beanParamSource = new BeanPropertySqlParameterSource(user);
		template.update(SET_ROLE, beanParamSource);
	}

	@Override
	public User read(Long primaryKey) {
		User resultUser = null;
		SqlParameterSource paramSource = new MapSqlParameterSource("id", primaryKey);
		resultUser = template.queryForObject(READ, paramSource, new UserRowMapper());
		return resultUser;
	}

	@Override
	public boolean update(User updateObject) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Long key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByUsername(String username) {
		User resultUser = null;
		SqlParameterSource paramSource = new MapSqlParameterSource("username", username);
		resultUser = template.queryForObject(READ_BY_USERNAME, paramSource, new UserRowMapper());
		return resultUser;
	}

	private class UserRowMapper implements RowMapper<User> {

		@Override
		public User mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
			User user = new User();
			user.setId(resultSet.getLong("user_id"));
			user.setUsername(resultSet.getString("username"));
			user.setEmail(resultSet.getString("email"));
			user.setPassword(resultSet.getString("password"));
			user.setActive(resultSet.getBoolean("is_active"));
			return user;
		}

	}

}
