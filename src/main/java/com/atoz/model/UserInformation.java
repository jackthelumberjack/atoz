package com.atoz.model;

/**
 * Created by Sergiu on 29.11.2015.
 */
public class UserInformation {

  private int userID;
  private String firstName;
  private String lastName;
  private String serialNumber;
  private String email;

  public UserInformation(int userID, String firstName, String lastName, String serialNumber, String email) {
    this.userID = userID;
    this.firstName = firstName;
    this.lastName = lastName;
    this.serialNumber = serialNumber;
    this.email = email;
  }

  public int getUserID() {
    return userID;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getSerialNumber() {
    return serialNumber;
  }

  public String getEmail() {
    return email;
  }
}
