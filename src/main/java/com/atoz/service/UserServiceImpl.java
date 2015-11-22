package com.atoz.service;

import com.atoz.dao.UserDAO;
import com.atoz.dao.UserDAOImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

  private UserDAO userDAO;

  public void setUserDAO(UserDAO userDAO) {
    this.userDAO = userDAO;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    List<GrantedAuthority> authorities = new ArrayList<>();
    // fetch user from e.g. DB
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    UserDAO dao = context.getBean("userDAO", UserDAOImpl.class);

    com.atoz.model.User modelUser = dao.getUser(username);

    User user = null;
    if (modelUser != null) {
      authorities.add(new SimpleGrantedAuthority(modelUser.getRole()));
      user = new User(username, modelUser.getPassword(), true, true, false, false, authorities);
    }

    return user;
  }
}
