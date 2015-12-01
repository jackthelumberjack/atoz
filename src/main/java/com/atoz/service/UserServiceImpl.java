package com.atoz.service;

import com.atoz.security.SecurityRole;
import com.atoz.dao.UserDAO;
import com.atoz.model.Department;
import com.atoz.model.UserInformation;
import org.springframework.security.core.GrantedAuthority;
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

    com.atoz.model.User modelUser = userDAO.getUser(username);

    User user = null;
    if (modelUser != null) {
      for (String role : modelUser.getRoles()) {
        if (role.equals(SecurityRole.ADMIN.getAuthority())) {
          authorities.add(SecurityRole.ADMIN);
        } else if (role.equals(SecurityRole.USER.getAuthority())) {
          authorities.add(SecurityRole.USER);
        } else if (role.equals(SecurityRole.INSTRUCTOR.getAuthority())) {
          authorities.add(SecurityRole.INSTRUCTOR);
        }
      }
      user = new User(username, modelUser.getPassword(), true, true, false, false, authorities);
    }

    return user;
  }

  @Override
  public UserInformation loadUserInformation(String userName) {
    return userDAO.getUserInformation(userName);
  }

  @Override
  public List<Department> getUserDepartments(String userName) {
    return userDAO.getUserDepartments(userName);
  }
}
