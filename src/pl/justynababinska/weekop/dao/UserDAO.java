package pl.justynababinska.weekop.dao;

import pl.justynababinska.weekop.model.User;

public interface UserDAO extends GenericDAO<User, Long> {

	User getUserByUsername(String username);

}
