package pl.justynababinska.weekop.dao;

import java.util.List;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import pl.justynababinska.weekop.model.User;
import pl.justynababinska.weekop.util.ConnectionProvider;

public class MySQLUserDAO implements UserDAO {
	private static final String CREATE = "INSERT INTO user(username, email, password, is_active) VALUES(:username, :email, :password, :active)";

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
		if (i > 1) {
			resultUser.setId((Long) keyHolder.getKey());
			setRole(user);

		}
		return resultUser;
	}

	private void setRole(User user) {
		final String SET_ROLE = "INSER INTO user_role(username) VALUES(:username)";
		BeanPropertySqlParameterSource beanParamSource = new BeanPropertySqlParameterSource(user);
		template.update(SET_ROLE, beanParamSource);
	}

	@Override
	public User read(Long primaryKey) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

}
