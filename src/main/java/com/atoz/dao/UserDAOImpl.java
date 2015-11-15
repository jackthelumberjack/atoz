package com.atoz.dao;

import com.atoz.model.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Sergiu on 09.11.2015.
 */
@Repository
public class UserDAOImpl {

  private Log log = LogFactory.getLog(UserDAOImpl.class);

  String selectUser = "select u.login as user_name, u.password as password, r.role as role from users u " +
      "inner join user_roles ur on ur.user_id = u.id " +
      "inner join roles r on ur.role_id = r.id " +
      "where u.login=:login";

  NamedParameterJdbcTemplate template;

  public void setDataSource(DataSource dataSource) {
    template = new NamedParameterJdbcTemplate(dataSource);
  }

  public User getUser(String login) {
    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("login", login);
    List<User> users = null;
    try {
      users = template.query(selectUser, params, new UserRowMapper());
    } catch (DataAccessException ex) {
      log.error("Failed to load user data");
    }
    if (users != null && users.size() > 0) {
      return users.get(0);
    }
    return null;
  }

  private static final class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
      return new User(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
    }
  }

}
