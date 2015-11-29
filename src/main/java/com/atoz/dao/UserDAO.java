package com.atoz.dao;

import com.atoz.model.User;
import com.atoz.model.UserInformation;

import javax.sql.DataSource;

/**
 * Created by Sergiu on 22.11.2015.
 */
public interface UserDAO {
  User getUser(String login);
  UserInformation getUserInformation(String login);
}
