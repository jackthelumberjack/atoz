package com.atoz.model;

/**
 * Created by Sergiu on 02.12.2015.
 */
public class CourseDTO {

  private int id;
  private String name;

  public CourseDTO(int id, String name) {
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
