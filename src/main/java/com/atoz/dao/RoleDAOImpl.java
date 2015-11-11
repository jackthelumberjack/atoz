package com.atoz.dao;

import com.atoz.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Sergiu on 09.11.2015.
 */
@Repository
public class RoleDAOImpl implements RoleDAO {

  @Autowired
  private SessionFactory sessionFactory;

  private Session getCurrentSession() {
    return sessionFactory.getCurrentSession();
  }

  public Role getRole(int id) {
    Role role = (Role) getCurrentSession().load(Role.class, id);
    return role;
  }

}
