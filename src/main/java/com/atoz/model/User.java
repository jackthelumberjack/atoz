package com.atoz.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergiu on 14.11.2015.
 */
public class User {
  private String userName;
  private String password;
  private List<String> roles;

  public User(String userName, String password) {
    this.userName = userName;
    this.password = password;
  }

  public void addRole(String role) {
    if (roles == null) {
      roles = new ArrayList<>();
    }
    roles.add(role);
  }

  public String getUserName() {
    return userName;
  }

  public String getPassword() {
    return password;
  }

  public List<String> getRoles() {
    return roles;
  }

  public  String ToString()
  {
    return getUserName();
  }
}
