package com.atoz.model;

/**
 * Created by Sergiu on 14.11.2015.
 */
public class User {
  private String userName;
  private String password;
  private String role;

  public User(String userName, String password, String role) {
    this.userName = userName;
    this.password = password;
    this.role = role;
  }

  public String getUserName() {
    return userName;
  }

  public String getPassword() {
    return password;
  }

  public String getRole() {
    return role;
  }
}
