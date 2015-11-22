package com.atoz.dao;

import com.atoz.model.User;

import javax.sql.DataSource;

/**
 * Created by Sergiu on 22.11.2015.
 */
public interface UserDAO {
  void setDataSource(DataSource dataSource);

  User getUser(String login);
}
