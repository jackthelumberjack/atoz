package com.atoz.dao;

import com.atoz.model.User;

/**
 * Created by Sergiu on 09.11.2015.
 */
public interface UserDAO {

    User getUser(String login);

}
