package com.atoz.model;

/**
 * Created by Sergiu on 01.12.2015.
 */
public class Department {

  private int id;
  private String name;

  public Department(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }
}
