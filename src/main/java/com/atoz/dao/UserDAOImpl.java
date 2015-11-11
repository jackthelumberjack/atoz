package com.atoz.dao;

import com.atoz.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Sergiu on 09.11.2015.
 */
@Repository
public class UserDAOImpl implements UserDAO {

  @Autowired
  private SessionFactory sessionFactory;

  private Session openSession() {
    return sessionFactory.getCurrentSession();
  }

  public User getUser(String login) {
    List<User> userList;
    Query query = openSession().createQuery("from User u where u.login = :login");
    query.setString("login", login);
    userList = query.list();
    if (userList.size() > 0)
      return userList.get(0);
    else
      return new User();
  }

}
